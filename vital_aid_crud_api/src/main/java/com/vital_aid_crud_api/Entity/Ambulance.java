package com.vital_aid_crud_api.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ambulance_table")
public class Ambulance {

    @Id
    @Column(length = 12,unique = true)
    private String ambulanceNumPlate;

    @Column(length = 15)
    private String ambulanceDriverContact;

    @ManyToOne
    @JoinColumn(name = "ambulanceManagingAdminId")
    private Admin ambulanceManagedBy;

    @OneToMany(mappedBy = "callForAmbulance", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<CallsAmbulance> callsAmbulances;

    public Ambulance() {
    }

    

    public String getAmbulanceNumPlate() {
        return ambulanceNumPlate;
    }

    public void setAmbulanceNumPlate(String ambulanceNumPlate) {
        this.ambulanceNumPlate = ambulanceNumPlate;
    }

    public String getAmbulanceDriverContact() {
        return ambulanceDriverContact;
    }

    public void setAmbulanceDriverContact(String ambulanceDriverContact) {
        this.ambulanceDriverContact = ambulanceDriverContact;
    }

    public Admin getAmbulanceManagedBy() {
        return ambulanceManagedBy;
    }

    public void setAmbulanceManagedBy(Admin ambulanceManagedBy) {
        this.ambulanceManagedBy = ambulanceManagedBy;
    }

    public List<CallsAmbulance> getCallsAmbulances() {
        return callsAmbulances;
    }

    public void setCallsAmbulances(List<CallsAmbulance> callsAmbulances) {
        this.callsAmbulances = callsAmbulances;
    }
}
