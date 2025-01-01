package com.vital_aid_crud_api.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class ForgetPasswordDTO {

    @Email(message = "Please provide a valid email address")
    private String email;


    @Size(min = 6, max = 6, message = "OTP must be 6 characters")
    private String otp;

    // @Size(min = 5,max = 300, message = "Password must be at least 5 characters")
    // @Pattern(
    // regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{5,}$",
    // message = "Password must be at least 5 characters long, include an uppercase letter, a lowercase letter, a digit, and a special character(@$!%*?&).")
    // private String newPassword;
    // private String confirmPassword;

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
