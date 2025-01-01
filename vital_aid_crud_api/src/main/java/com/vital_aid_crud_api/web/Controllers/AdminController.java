package com.vital_aid_crud_api.web.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vital_aid_crud_api.Payloads.AdminDTO;
import com.vital_aid_crud_api.Payloads.ChangePasswordDTO;
import com.vital_aid_crud_api.Util.ApiResponse;
import com.vital_aid_crud_api.service.Interfaces.AdminService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/vital_aid/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

                                                    // FETCHING ALL ADMINS

    @GetMapping("/allAdmins")
    public ResponseEntity<List<AdminDTO>> viewAllAdminList(){
        List<AdminDTO> adminDTOs = adminService.getAllAdmins();
        return new ResponseEntity<>(adminDTOs, HttpStatus.OK);
    }

                                                    // FETCHING ADMIN PROFILE

    @GetMapping("/profile")
    public ResponseEntity<AdminDTO> viewAdminProfile(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch admin details based on the email
        AdminDTO adminDTO = adminService.getAdminByEmail(email);

        return new ResponseEntity<>(adminDTO, HttpStatus.OK);
    }

                                                    // UPDATING ADMIN PROFILE

    @PutMapping("/updateAdminProfile")
    public ResponseEntity<AdminDTO> updateAdminProfile(@Valid @RequestBody AdminDTO adminDTO){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        AdminDTO updatedAdminDTO = adminService.updateAdminProfile(email, adminDTO);

        return new ResponseEntity<>(updatedAdminDTO, HttpStatus.OK);
    }
    

                                                    // REMOVING ADMIN ACCOUNT

    @DeleteMapping("/deleteAdmin")
    public ResponseEntity<String> deleteAdmin(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        adminService.deleteAdmin(email);
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>("Admin deleted Successfully",HttpStatus.NO_CONTENT);
    }

                                                    // CHANGING ADMIN PASSWORD
                                                    
    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        adminService.changeAdminPassword(email, changePasswordDTO);
        ApiResponse response = new ApiResponse("Password changed successfully", true);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.OK);
    }
    
}
