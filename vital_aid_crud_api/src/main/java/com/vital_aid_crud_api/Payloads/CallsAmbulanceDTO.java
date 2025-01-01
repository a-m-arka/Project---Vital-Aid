package com.vital_aid_crud_api.Payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CallsAmbulanceDTO {

    private Long calledByUserId;
    private String ambulanceNumberplate;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Contact is required")
    @Pattern(regexp = "^01[0-9]{9}$", message = "Invalid Bangladeshi phone number")
    private String contact;

    public CallsAmbulanceDTO() {
    }

    public CallsAmbulanceDTO( Long calledByUserId, String ambulanceNumberplate, String address, String contact) {
        this.calledByUserId = calledByUserId;
        this.ambulanceNumberplate = ambulanceNumberplate;
        this.address = address;
        this.contact = contact;
    }

    public Long getCalledByUserId() {
        return this.calledByUserId;
    }

    public void setCalledByUserId(Long calledByUserId) {
        this.calledByUserId = calledByUserId;
    }

    public String getAmbulanceNumberplate() {
        return this.ambulanceNumberplate;
    }

    public void setAmbulanceNumberplate(String ambulanceNumberplate) {
        this.ambulanceNumberplate = ambulanceNumberplate;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
 
}
