package com.vital_aid_crud_api.Entity;

import com.vital_aid_crud_api.EmbeddedClass.CallsAmbulanceId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "calls_ambulance")
public class CallsAmbulance {

    @EmbeddedId
    private CallsAmbulanceId id;
    private String address; // Descriptive attribute
    private String contact; // Descriptive attribute
    
    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private User calledByUser;

    @ManyToOne
    @MapsId("ambulanceNumPlate")
    @JoinColumn(name = "ambulance_number_plate")
    private Ambulance callForAmbulance;

    public CallsAmbulance() {
    }


    public CallsAmbulanceId getId() {
        return this.id;
    }

    public void setId(CallsAmbulanceId id) {
        this.id = id;
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

    public User getCalledByUser() {
        return this.calledByUser;
    }

    public void setCalledByUser(User calledByUser) {
        this.calledByUser = calledByUser;
    }

    public Ambulance getCallForAmbulance() {
        return this.callForAmbulance;
    }

    public void setCallForAmbulance(Ambulance callForAmbulance) {
        this.callForAmbulance = callForAmbulance;
    }
    

}
