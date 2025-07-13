package com.vital_aid_crud_api.Entity;

import java.util.List;
import java.util.Set;


import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctor_table")
public class Doctor extends Person{

    

    @Column
    private Integer doctorFee;

    @Column(length = 200)
    private String doctorGender;

    @Column(length = 200)
    private String doctorCity;


    @Column(length = 200)
    private String specializationField;

    @Column(length = 1000, columnDefinition = "VARCHAR(500) DEFAULT ''")
    private String doctorProfileImageUrl;

    @Column
    private String availabilityStatus;

    @Column(columnDefinition = "FLOAT DEFAULT 0.0")
    private Float doctorAverageRating;

    @ElementCollection
    @CollectionTable(name = "Doctor_Specialization", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(length = 200)
    private Set<String> doctorSpecialization;

    @ElementCollection
    @CollectionTable(name = "Doctor_Consultation_Days", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(length = 200)
    private Set<String> consultationDays;

    @Embedded
    private ConsultingTimes consultingTime;

    @ManyToOne(cascade = CascadeType.PERSIST) // Many doctors can work at one hospital
    @JoinColumn(name = "working_hospital_id") // Ensures FK relationship
    private Hospital worksAt; // Reference to the hospital

    // @ManyToOne(cascade = CascadeType.PERSIST) // Many doctors can work at one hospital
    // @JoinColumn(name = "doctorManagingAdminId") // Ensures FK relationship
    // private Admin doctorManagedBy;

    @OneToMany(mappedBy = "withDoctor", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<Appointment> doctorAppointments;

    @OneToMany(mappedBy = "ratingMadeForDoctor", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<DoctorRating> doctorRatings;

    public Doctor() {
        this.setPersonRole("ROLE_DOCTOR");
        this.doctorAverageRating = 0.0f;
    }


    // public Long getDoctorId() {
    //     return this.doctorId;
    // }

    // public void setDoctorId(Long doctorId) {
    //     this.doctorId = doctorId;
    // }

    // public String getDoctorName() {
    //     return this.doctorName;
    // }

    // public void setDoctorName(String doctorName) {
    //     this.doctorName = doctorName;
    // }

    // public String getDoctorEmail() {
    //     return this.doctorEmail;
    // }

    // public void setDoctorEmail(String doctorEmail) {
    //     this.doctorEmail = doctorEmail;
    // }

    // public String getDoctorPhone() {
    //     return this.doctorPhone;
    // }

    // public void setDoctorPhone(String doctorPhone) {
    //     this.doctorPhone = doctorPhone;
    // }

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

    public String getAvailabilityStatus() {
        return this.availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public Float getDoctorAverageRating() {
        return this.doctorAverageRating;
    }

    public void setDoctorAverageRating(Float doctorAverageRating) {
        this.doctorAverageRating = doctorAverageRating;
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

    public Set<String> getConsultationDays() {
        return this.consultationDays;
    }

    public void setConsultationDays(Set<String> consultationDays) {
        this.consultationDays = consultationDays;
    }

    public ConsultingTimes getConsultingTime() {
        return this.consultingTime;
    }

    public void setConsultingTime(ConsultingTimes consultingTime) {
        this.consultingTime = consultingTime;
    }

    public Hospital getWorksAt() {
        return this.worksAt;
    }

    public void setWorksAt(Hospital worksAt) {
        this.worksAt = worksAt;
    }

    // public Admin getDoctorManagedBy() {
    //     return this.doctorManagedBy;
    // }

    // public void setDoctorManagedBy(Admin doctorManagedBy) {
    //     this.doctorManagedBy = doctorManagedBy;
    // }

    public List<Appointment> getDoctorAppointments() {
        return this.doctorAppointments;
    }

    public void setDoctorAppointments(List<Appointment> doctorAppointments) {
        this.doctorAppointments = doctorAppointments;
    }

    public List<DoctorRating> getDoctorRatings() {
        return this.doctorRatings;
    }

    public void setDoctorRatings(List<DoctorRating> doctorRatings) {
        this.doctorRatings = doctorRatings;
    }

}
