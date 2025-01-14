package com.vital_aid_crud_api.Entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointment_table")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentToken;

    @Column(length = 100)
    private String patientName;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate patientDob;

    @Column(length = 20,columnDefinition = "VARCHAR(20) DEFAULT ''")
    private String patientPhone;

    @Column(length = 200,columnDefinition = "VARCHAR(200) DEFAULT ''")
    private String patientEmail;

    
    @Column(length = 10,columnDefinition = "VARCHAR(10) DEFAULT ''")
    private String patientGender;

    @Column(length = 300,columnDefinition = "VARCHAR(300) DEFAULT ''")
    private String reasonForVisit;

    
    @Column(length = 10,columnDefinition = "VARCHAR(10) DEFAULT ''")
    private String visitDay;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;

    @ManyToOne
    @JoinColumn(name = "madeByUser")
    private User madeByUser;

    @ManyToOne
    @JoinColumn(name = "withDoctor")
    private Doctor withDoctor;


    

    public Appointment() {
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
    
    public User getMadeByUser() {
        return this.madeByUser;
    }

    public void setMadeByUser(User madeByUser) {
        this.madeByUser = madeByUser;
    }

    public Doctor getWithDoctor() {
        return this.withDoctor;
    }

    public void setWithDoctor(Doctor withDoctor) {
        this.withDoctor = withDoctor;
    }

    public LocalDate getAppointmentDate() {
        return this.appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    
}
