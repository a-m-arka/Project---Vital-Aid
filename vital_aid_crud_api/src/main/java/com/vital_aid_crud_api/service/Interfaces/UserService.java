package com.vital_aid_crud_api.service.Interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.vital_aid_crud_api.Payloads.ChangePasswordDTO;
import com.vital_aid_crud_api.Payloads.RegisterDTO;
import com.vital_aid_crud_api.Payloads.UserDTO;

public interface UserService {

    // fetch all users
    List<UserDTO> getAllUsers();

    

    // update user profile
    UserDTO updateUserProfile(String personEmail, UserDTO userDTO);

    // updating the user profile image
    void updateLoggedInUserProfileImage(MultipartFile file);

    // fetch user by id
    UserDTO viewUserDetailsbyId(Long personId);

    // delete user by id
    void deleteUser(String personEmail);

    // fetch user by email
    UserDTO getUserByEmail(String personEmail);

    // register user
    RegisterDTO registerUser(RegisterDTO userDTO);

    // change password
    void changePassword(String userEmail, ChangePasswordDTO changePasswordDTO);

    // Forgot Password: Send verification code
    void sendVerificationCode(String personEmail);

    // Forgot Password: Validate OTP
    void validateOtp(String otp);

    // Forgot Password: Reset password
    void resetPassword(String newPassword, String confirmPassword);

}
