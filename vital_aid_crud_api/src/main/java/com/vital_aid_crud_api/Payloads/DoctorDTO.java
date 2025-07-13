package com.vital_aid_crud_api.Payloads;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vital_aid_crud_api.Entity.ConsultingTimes;
import com.vital_aid_crud_api.Validation.ValidConsultDay;
import com.vital_aid_crud_api.Validation.ValidConsultTimes;
import com.vital_aid_crud_api.Validation.ValidGender;
import com.vital_aid_crud_api.Validation.ValidCity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class DoctorDTO {

    private Long Id;

    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z.,\\-\\s]+$", message = "Name can not contain numbers")
    private String personName;

    @Email(message = "Email Address is not valid")
    private String personEmail;

    @Pattern(regexp = "^01[0-9]{9}$", message = "Invalid Bangladeshi phone number")
    private String personPhone;

    @Pattern(regexp = "^(Available|Unavailable)$", message = "Status must be 'Available' or 'Unavailable'")
    private String availabilityStatus;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 5, max = 300, message = "Password must be at least 5 characters long.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{5,}$", message = "Password must include an uppercase letter, a lowercase letter, a digit, and a special character(@$!%*?&).")
    private String loginPassword;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confirmPassword;

    private Integer doctorFee;

    
    private Float doctorAverageRating;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Gender must contain only letters")
    @ValidGender(message = "It is not a valid gender")
    private String doctorGender;

    @ValidCity(message = "Invalid City")
    private String doctorCity;

    @Pattern(regexp = "^[a-zA-Z.,\\-\\s]+$", message = "Specialization field must not contain number")
    private String specializationField;

    private String doctorProfileImageUrl;

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

    public String getAvailabilityStatus() {
        return this.availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
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

    public String getDoctorProfileImageUrl() {
        return this.doctorProfileImageUrl;
    }

    public void setDoctorProfileImageUrl(String doctorProfileImageUrl) {
        this.doctorProfileImageUrl = doctorProfileImageUrl;
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

    public Float getDoctorAverageRating() {
        return this.doctorAverageRating;
    }

    public void setDoctorAverageRating(Float doctorAverageRating) {
        this.doctorAverageRating = doctorAverageRating;
    }

}
