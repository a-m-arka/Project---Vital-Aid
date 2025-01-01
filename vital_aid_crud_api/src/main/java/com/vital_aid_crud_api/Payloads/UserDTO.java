package com.vital_aid_crud_api.Payloads;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



public class UserDTO {

    // Only those fields are included which are required for the user to see and user to enter as input
    
    
    private Long Id;


    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z.,\\-\\s]+$", message = "Name can not contain numbers")
    private String personName;

    @Email(message = "Email Address is not valid")
    private String personEmail;

    

    @Pattern(regexp = "^01[0-9]{9}$", message = "Invalid Bangladeshi phone number")
    private String personPhone;

    private String userGender;
    private String userAddress;

    private String userBloodGroup;

    @Past(message = "Date of Birth should be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate userDob;


    private String profileImageUrl;

    public UserDTO() {
        
    }



    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
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

    

    public String getUserGender() {
        return this.userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getPersonPhone() {
        return this.personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getUserAddress() {
        return this.userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserBloodGroup() {
        return this.userBloodGroup;
    }

    public void setUserBloodGroup(String userBloodGroup) {
        this.userBloodGroup = userBloodGroup;
    }

    public LocalDate getUserDob() {
        return this.userDob;
    }

    public void setUserDob(LocalDate userDob) {
        this.userDob = userDob;
    }

    public String getProfileImageUrl() {
        return this.profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
