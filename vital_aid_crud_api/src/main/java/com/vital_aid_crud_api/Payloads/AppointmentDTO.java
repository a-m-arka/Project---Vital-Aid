package com.vital_aid_crud_api.Payloads;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vital_aid_crud_api.Validation.ValidGender;
import com.vital_aid_crud_api.Validation.ValidVisitDay;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AppointmentDTO {
    private Long appointmentToken;
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z.,\\-\\s]+$", message = "Name can not contain numbers")
    private String patientName;

    @Past(message = "Date of birth must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate patientDob;

    @Pattern(regexp = "^01[0-9]{9}$", message = "Invalid Bangladeshi phone number")
    private String patientPhone;

    @Email(message = "Email Address is not valid")
    private String patientEmail;

    @ValidGender
    private String patientGender;

    @Size(max = 300, message = "Please write your reason in less than 300 characters")
    private String reasonForVisit;

    @ValidVisitDay
    private String visitDay;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Appointment date must be in the future")
    private LocalDate appointmentDate;

    private String appointmentBy;

    private String appointmentWith;

    private Integer patientAge;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime doctorAppointmentStartTime;

    public AppointmentDTO() {
    }


    public Long getAppointmentToken() {
        return this.appointmentToken;
    }

    public void setAppointmentToken(Long appointmentToken) {
        this.appointmentToken = appointmentToken;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDate getPatientDob() {
        return this.patientDob;
    }

    public void setPatientDob(LocalDate patientDob) {
        this.patientDob = patientDob;
    }

    public String getPatientPhone() {
        return this.patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getPatientEmail() {
        return this.patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientGender() {
        return this.patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getReasonForVisit() {
        return this.reasonForVisit;
    }

    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }

    public String getVisitDay() {
        return this.visitDay;
    }

    public void setVisitDay(String visitDay) {
        this.visitDay = visitDay;
    }

    public Integer getPatientAge() {
        return this.patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public String getAppointmentBy() {
        return this.appointmentBy;
    }

    public void setAppointmentBy(String appointmentBy) {
        this.appointmentBy = appointmentBy;
    }

    public String getAppointmentWith() {
        return this.appointmentWith;
    }

    public void setAppointmentWith(String appointmentWith) {
        this.appointmentWith = appointmentWith;
    }

    public LocalDate getAppointmentDate() {
        return this.appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getDoctorAppointmentStartTime() {
        return this.doctorAppointmentStartTime;
    }

    public void setDoctorAppointmentStartTime(LocalTime doctorAppointmentStartTime) {
        this.doctorAppointmentStartTime = doctorAppointmentStartTime;
    }

}
