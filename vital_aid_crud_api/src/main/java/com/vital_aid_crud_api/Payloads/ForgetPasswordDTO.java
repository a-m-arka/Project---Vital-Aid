package com.vital_aid_crud_api.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class ForgetPasswordDTO {

    @Email(message = "Please provide a valid email address")
    private String email;


    @Size(min = 6, max = 6, message = "OTP must be 6 characters")
    private String otp;

    public ForgetPasswordDTO() {
    }


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return this.otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    // public String getNewPassword() {
    //     return this.newPassword;
    // }

    // public void setNewPassword(String newPassword) {
    //     this.newPassword = newPassword;
    // }

    // public String getConfirmPassword() {
    //     return this.confirmPassword;
    // }

    // public void setConfirmPassword(String confirmPassword) {
    //     this.confirmPassword = confirmPassword;
    // }

}
