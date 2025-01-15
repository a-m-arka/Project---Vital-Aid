package com.vital_aid_crud_api.Payloads;


import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class HospitalDTO {

    private Long Id;

    @Size(min = 3, max = 100, message = "Hospital Name must be between 3 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Hospital Name must contain only letters")
    private String hospitalName;

    private String hospitalLocation;

    private String hospitalCity;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid contact number")
    private String hospitalContact;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String hospitalEmail;

    private Integer totalGeneralBeds;

    private Integer totalIcuBeds;

    private Integer totalVentilators;

    private Set<String> hospitalFacilities;

    private String hospitalPhotoUrl;

    private Integer hospitalTotalDoctor;



    public HospitalDTO() {}


    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getHospitalName() {
        return this.hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalLocation() {
        return this.hospitalLocation;
    }

    public void setHospitalLocation(String hospitalLocation) {
        this.hospitalLocation = hospitalLocation;
    }

    public String getHospitalContact() {
        return this.hospitalContact;
    }

    public void setHospitalContact(String hospitalContact) {
        this.hospitalContact = hospitalContact;
    }

    public String getHospitalEmail() {
        return this.hospitalEmail;
    }

    public void setHospitalEmail(String hospitalEmail) {
        this.hospitalEmail = hospitalEmail;
    }

    public Integer getTotalGeneralBeds() {
        return this.totalGeneralBeds;
    }

    public void setTotalGeneralBeds(Integer totalGeneralBeds) {
        this.totalGeneralBeds = totalGeneralBeds;
    }

    public Integer getTotalIcuBeds() {
        return this.totalIcuBeds;
    }

    public void setTotalIcuBeds(Integer totalIcuBeds) {
        this.totalIcuBeds = totalIcuBeds;
    }

    public Integer getTotalVentilators() {
        return this.totalVentilators;
    }

    public void setTotalVentilators(Integer totalVentilators) {
        this.totalVentilators = totalVentilators;
    }

    public Set<String> getHospitalFacilities() {
        return this.hospitalFacilities;
    }

    public void setHospitalFacilities(Set<String> hospitalFacilities) {
        this.hospitalFacilities = hospitalFacilities;
    }

    public String getHospitalPhotoUrl() {
        return this.hospitalPhotoUrl;
    }

    public void setHospitalPhotoUrl(String hospitalPhotoUrl) {
        this.hospitalPhotoUrl = hospitalPhotoUrl;
    }

    public Integer getHospitalTotalDoctor() {
        return this.hospitalTotalDoctor;
    }

    public void setHospitalTotalDoctor(Integer hospitalTotalDoctor) {
        this.hospitalTotalDoctor = hospitalTotalDoctor;
    }

    public String getHospitalCity() {
        return this.hospitalCity;
    }

    public void setHospitalCity(String hospitalCity) {
        this.hospitalCity = hospitalCity;
    }

}
