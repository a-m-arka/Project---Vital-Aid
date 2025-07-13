package com.vital_aid_crud_api.web.AuthAndReg;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vital_aid_crud_api.Payloads.DoctorDTO;
import com.vital_aid_crud_api.Payloads.RegisterDTO;
import com.vital_aid_crud_api.Util.ApiResponse;
import com.vital_aid_crud_api.service.Interfaces.AdminService;
import com.vital_aid_crud_api.service.Interfaces.DoctorService;
import com.vital_aid_crud_api.service.Interfaces.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vital_aid")
public class RegistrationController {

    private final UserService userService;
    private final AdminService adminService;
    private final DoctorService doctorService;

    public RegistrationController(UserService userService, AdminService adminService, DoctorService doctorService) {
        this.userService = userService;
        this.adminService = adminService;
        this.doctorService = doctorService;
    }

//  ====================================================================================================================
                            // USER REGISTRATION ENDPOINT
                            
    @PostMapping("/user/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterDTO userDTO) {
        userDTO = userService.registerUser(userDTO);
        ApiResponse response = new ApiResponse("User registered successfully", true);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.CREATED);
    }
//   ====================================================================================================================
   
//   ======================================================================================================================
                            // DOCTOR REGISTRATION ENDPOINT
    @PostMapping("/doctor/register")
    public ResponseEntity<String> registerDoctor(@Valid @RequestBody DoctorDTO doctorDTO){
        doctorDTO = doctorService.registerDoctor(doctorDTO);
        ApiResponse response = new ApiResponse("Doctor registered successfully", true);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.CREATED);
    }
//  ====================================================================================================================

//  ====================================================================================================================
                            // ADMIN REGISTRATION ENDPOINT

    @PostMapping("/admin/register")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody RegisterDTO adminDTO) {
        adminDTO = adminService.registerAdmin(adminDTO);
        ApiResponse response = new ApiResponse("Admin registered successfully", true);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.CREATED);
    }
//  ====================================================================================================================
}