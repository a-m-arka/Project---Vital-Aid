package com.vital_aid_crud_api.Payloads;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AmbulanceDTO {
    
    @Id
    @Pattern(regexp = "^(DHA|CTG|RAJ|KHU|BAR|SYL|MYM|RAN)-\\d{2}-\\d{4}$", message = "Ambulance number plate must follow the format REG-NN-YYYY")
    @Size(min = 11, max = 11, message = "Invalid number plate, must be 11 characters long")
    private String Id; // ambulanceNumPlate

    @Pattern(regexp = "^01[0-9]{9}$", message = "Invalid Bangladeshi phone number")
    private String ambulanceDriverContact;

    public AmbulanceDTO() {
    }

    public AmbulanceDTO(String Id, String ambulanceDriverContact) {
        this.Id = Id;
        this.ambulanceDriverContact = ambulanceDriverContact;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }



    public String getAmbulanceDriverContact() {
        return ambulanceDriverContact;
    }

    public void setAmbulanceDriverContact(String ambulanceDriverContact) {
        this.ambulanceDriverContact = ambulanceDriverContact;
    }
}
