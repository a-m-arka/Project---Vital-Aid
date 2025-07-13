package com.vital_aid_crud_api.web.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vital_aid_crud_api.Payloads.ChangePasswordDTO;
import com.vital_aid_crud_api.Payloads.UserDTO;
import com.vital_aid_crud_api.Util.ApiResponse;
import com.vital_aid_crud_api.service.Interfaces.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/vital_aid")
public class UserController {


    @Autowired
    private UserService userService;


// ====================================================================================================================
                                                // FETCHING ALL USERS
                                                
    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOs = userService.getAllUsers();
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }
// ====================================================================================================================

// ====================================================================================================================
                                                // FETCHING USER BY ID

    @GetMapping("/viewUser/{Id}")
    public ResponseEntity<UserDTO> viewUserbyId(@PathVariable Long Id) {
        UserDTO userDTO = userService.viewUserDetailsbyId(Id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
// ====================================================================================================================

// ====================================================================================================================
                                                // FETCHING USER PROFILE

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> viewUserProfile() {
        // Get the currently logged-in user's email from Spring Security
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch user details based on the email
        UserDTO userDTO = userService.getUserByEmail(email);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
// ====================================================================================================================

// ====================================================================================================================
                                                // UPDATING USER PROFILE

    @PutMapping("/updateUserProfile")
    public ResponseEntity<UserDTO> updateUserProfile(@Valid @RequestBody UserDTO userDTO) {
        // Get the currently logged-in user's email from Spring Security
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Update the user profile
        UserDTO updatedUserDTO = userService.updateUserProfile(email, userDTO);

        return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
    }
// ====================================================================================================================

// ====================================================================================================================
                                                // DELETING USER ACCOUNT

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.deleteUser(email);
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>("User Deleted Sucessfully", HttpStatus.NO_CONTENT);
    }
//  ====================================================================================================================

// ====================================================================================================================
                                                // CHANGING PASSWORD
                                    
    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        userService.changePassword(email, changePasswordDTO);
        ApiResponse response = new ApiResponse("Password changed successfully", true);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.OK);
    }
// ====================================================================================================================

}
