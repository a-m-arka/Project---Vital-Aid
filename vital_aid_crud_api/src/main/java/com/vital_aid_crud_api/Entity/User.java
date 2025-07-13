package com.vital_aid_crud_api.Entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_table")
public class User extends Person {

    @Column(length = 10, columnDefinition = "VARCHAR(10) DEFAULT''")
    private String userGender;

    @Column(length = 300, columnDefinition = "VARCHAR(300) DEFAULT ''")
    private String userAddress;

    @Column(length = 5, columnDefinition = "VARCHAR(5) DEFAULT ''")
    private String userBloodGroup;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE DEFAULT '1970-01-01'")
    private LocalDate userDob;

    @Column(columnDefinition = "VARCHAR(500) Default ''")
    private String profileImageUrl;

    @OneToMany(mappedBy = "madeByUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<Appointment> madeAppointments;
    
    @OneToMany(mappedBy = "orderMadeByUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<Order> madeOrders;

    @OneToMany(mappedBy = "calledByUser", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<CallsAmbulance> callsAmbulances;

    @OneToMany(mappedBy = "ratingMadeByUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<DoctorRating> ratedDoctors;

    @OneToMany(mappedBy = "productRatingMadeByUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<ProductRating> productsRated;

        
    public User() {
        this.setPersonRole("ROLE_USER");
    }

    public String getUserGender() {
        return this.userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
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
    
    public List<Appointment> getMadeAppointments() {
        return this.madeAppointments;
    }

    public void setMadeAppointments(List<Appointment> madeAppointments) {
        this.madeAppointments = madeAppointments;
    }

    public List<Order> getMadeOrders() {
        return this.madeOrders;
    }

    public void setMadeOrders(List<Order> madeOrders) {
        this.madeOrders = madeOrders;
    }

    public List<CallsAmbulance> getCallsAmbulances() {
        return this.callsAmbulances;
    }

    public void setCallsAmbulances(List<CallsAmbulance> callsAmbulances) {
        this.callsAmbulances = callsAmbulances;
    }

    public List<DoctorRating> getRatedDoctors() {
        return this.ratedDoctors;
    }

    public void setRatedDoctors(List<DoctorRating> ratedDoctors) {
        this.ratedDoctors = ratedDoctors;
    }

    public List<ProductRating> getProductsRated() {
        return this.productsRated;
    }

    public void setProductsRated(List<ProductRating> productsRated) {
        this.productsRated = productsRated;
    }


    
    @Override
    public String toString() {
        return "{" +
            " user_id='" + getPersonId() + "'" +
            ", user_name='" + getPersonName() + "'" +
            ", user_email='" + getPersonEmail() + "'" +
            ", user_phone='" + getPersonPhone() + "'" +
            ", user_password='" + getLoginPassword() + "'" +
            " user_gender='" + getUserGender() + "'" +
            ", user_address='" + getUserAddress() + "'" +
            ", user_blood_group='" + getUserBloodGroup() + "'" +
            ", user_dob='" + getUserDob() + "'" +
            ", profile_image_url='" + getProfileImageUrl() + "'" +
            "}";
    }
    


    



}


