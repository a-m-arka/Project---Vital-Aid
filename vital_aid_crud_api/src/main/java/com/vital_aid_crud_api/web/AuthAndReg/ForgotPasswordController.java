package com.vital_aid_crud_api.web.AuthAndReg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vital_aid_crud_api.Payloads.ChangePasswordDTO;
import com.vital_aid_crud_api.Payloads.ForgetPasswordDTO;
import com.vital_aid_crud_api.service.Interfaces.AdminService;
import com.vital_aid_crud_api.service.Interfaces.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vital_aid/forgotPassword")
public class ForgotPasswordController {
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

                                        // FOR ADMIN

    // Step 1: Send verification code
    @PostMapping("/admin/sendCode")
    public ResponseEntity<String> adminSendVerificationCode(@Valid @RequestBody ForgetPasswordDTO dto) {
        adminService.sendVerificationCode(dto.getEmail());
        return ResponseEntity.ok("Verification code sent to your email.");
    }

    // Step 2: Validate OTP
    @PostMapping("/admin/validateOtp")
    public ResponseEntity<String> adminValidateOtp(@Valid @RequestBody ForgetPasswordDTO dto) {
        adminService.validateOtp(dto.getOtp());
        return ResponseEntity.ok("OTP verrified successfully.");
    }

    // Step 3: Reset Password
    @PostMapping("/admin/resetPassword")
    public ResponseEntity<String> adminResetPassword(@Valid @RequestBody ChangePasswordDTO dto) {
        adminService.resetPassword(dto.getNewPassword(), dto.getConfirmPassword());
        return ResponseEntity.ok("Password has been reset successfully.");
    }






                                        // FOR USER

    // Step 1: Send verification code
    @PostMapping("/sendCode")
    public ResponseEntity<String> sendVerificationCode(@Valid @RequestBody ForgetPasswordDTO dto) {
        userService.sendVerificationCode(dto.getEmail());
        return ResponseEntity.ok("Verification code sent to your email.");
    }

    // Step 2: Validate OTP
    @PostMapping("/validateOtp")
    public ResponseEntity<String> validateOtp(@Valid @RequestBody ForgetPasswordDTO dto) {
        userService.validateOtp(dto.getOtp());
        return ResponseEntity.ok("OTP verrified successfully.");
    }

    // Step 3: Reset Password
    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ChangePasswordDTO dto) {
        userService.resetPassword(dto.getNewPassword(), dto.getConfirmPassword());
        return ResponseEntity.ok("Password has been reset successfully.");
    }
}
