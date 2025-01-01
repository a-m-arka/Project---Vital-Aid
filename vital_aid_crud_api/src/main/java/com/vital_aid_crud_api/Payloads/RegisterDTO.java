package com.vital_aid_crud_api.Payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterDTO {


    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z.,\\-\\s]+$", message = "Name can not contain numbers")
    private String personName;

    @Email(message = "Email Address is not valid")
    private String personEmail;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 5,max = 300, message = "Password must be at least 5 characters")
    @Pattern(
    regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{5,}$",
    message = "Password must be at least 5 characters long, include an uppercase letter, a lowercase letter, a digit, and a special character(@$!%*?&).")
    private String loginPassword;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confirmPassword;

    @Pattern(regexp = "^01[0-9]{9}$", message = "Invalid Bangladeshi phone number")
    private String personPhone;

    public RegisterDTO() {
    }

    public String getPersonName() {
        return this.personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonEmail() {
        return this.personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getLoginPassword() {
        return this.loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPersonPhone() {
        return this.personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

}
