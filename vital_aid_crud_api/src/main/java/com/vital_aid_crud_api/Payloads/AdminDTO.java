package com.vital_aid_crud_api.Payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AdminDTO {

    // This class will be use for update the admin information and get the admin information
    private Long Id;


    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z.,\\-\\s]+$", message = "Name can not contain numbers")
    private String personName;


    @Email(message = "Email Address is not valid")
    private String personEmail;

    

    @Pattern(regexp = "^01[0-9]{9}$", message = "Invalid Bangladeshi phone number")
    private String personPhone;

    // private String adminRole;
    public AdminDTO() {
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

    

    public String getPersonPhone() {
        return this.personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

}
