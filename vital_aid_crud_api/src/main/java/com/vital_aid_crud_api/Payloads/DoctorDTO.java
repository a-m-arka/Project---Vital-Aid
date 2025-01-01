package com.vital_aid_crud_api.Payloads;

import java.util.Set;

import com.vital_aid_crud_api.Entity.ConsultingTimes;
import com.vital_aid_crud_api.Validation.ValidConsultDay;
import com.vital_aid_crud_api.Validation.ValidConsultTimes;
import com.vital_aid_crud_api.Validation.ValidCity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class DoctorDTO {

    private Long Id;


    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z.,\\-\\s]+$", message = "Name can not contain numbers")
    private String doctorName;

    @Email(message = "Email Address is not valid")
    private String doctorEmail;


    @Pattern(regexp = "^01[0-9]{9}$", message = "Invalid Bangladeshi phone number")
    private String doctorPhone;

    private Integer doctorFee;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Gender must contain only letters")
    private String doctorGender;

    @ValidCity(message = "Invalid City")
    private String doctorCity;

    
    @Pattern(regexp = "^[a-zA-Z.,\\-\\s]+$", message = "Specialization field must not contain number")
    private String specializationField;

    private String doctorPhotoUrl;

    private Set<String> doctorSpecialization;

    @ValidConsultDay(message = "Invalid Consultation Day")
    private Set<String> consultationDays;

    @ValidConsultTimes
    private ConsultingTimes consultingTime;

    private String hospitalName;


    public DoctorDTO() {
    }



    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getDoctorName() {
        return this.doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorEmail() {
        return this.doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorPhone() {
        return this.doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public Integer getDoctorFee() {
        return this.doctorFee;
    }

    public void setDoctorFee(Integer doctorFee) {
        this.doctorFee = doctorFee;
    }

    public String getDoctorGender() {
        return this.doctorGender;
    }

    public void setDoctorGender(String doctorGender) {
        this.doctorGender = doctorGender;
    }

    public String getDoctorCity() {
        return this.doctorCity;
    }

    public void setDoctorCity(String doctorCity) {
        this.doctorCity = doctorCity;
    }

    public String getSpecializationField() {
        return this.specializationField;
    }

    public void setSpecializationField(String specializationField) {
        this.specializationField = specializationField;
    }

    public String getDoctorPhotoUrl() {
        return this.doctorPhotoUrl;
    }

    public void setDoctorPhotoUrl(String doctorPhotoUrl) {
        this.doctorPhotoUrl = doctorPhotoUrl;
    }

    public Set<String> getDoctorSpecialization() {
        return this.doctorSpecialization;
    }

    public void setDoctorSpecialization(Set<String> doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    @ValidConsultDay(message = "Invalid Consultation Day")
    public Set<String> getConsultationDays() {
        return this.consultationDays;
    }

    
    public void setConsultationDays(Set<String> consultationDays) {
        this.consultationDays = consultationDays;
    }

    // @Valid
    public ConsultingTimes getConsultingTime() {
        return this.consultingTime;
    }
    

    public void setConsultingTime(ConsultingTimes consultingTime) {
        this.consultingTime = consultingTime;
    }
    

    public String getHospitalName() {
        return this.hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
    

    
}
