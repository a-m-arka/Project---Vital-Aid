package com.vital_aid_crud_api.Entity;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_table")
public class Admin extends Person{
    
    // @OneToMany(mappedBy = "doctorManagedBy", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    // private List<Doctor> doctors;

    @OneToMany(mappedBy = "hospitalManagedBy", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<Hospital> Hospitals;

    @OneToMany(mappedBy = "ambulanceManagedBy", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<Ambulance> ambulances;

    @OneToMany(mappedBy = "storeManagedBy", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<Product> medicalStoreProducts;

    public Admin() {
        super();
        this.setPersonRole("ROLE_ADMIN");
    }

    // public List<Doctor> getDoctors() {
    //     return doctors;
    // }

    // public void setDoctors(List<Doctor> doctors) {
    //     this.doctors = doctors;
    // }

    public List<Hospital> getHospitals() {
        return Hospitals;
    }

    public void setHospitals(List<Hospital> hospitals) {
        Hospitals = hospitals;
    }

    public List<Ambulance> getAmbulances() {
        return ambulances;
    }

    public void setAmbulances(List<Ambulance> ambulances) {
        this.ambulances = ambulances;
    }

    public List<Product> getMedicalStoreProducts() {
        return medicalStoreProducts;
    }

    public void setMedicalStoreProducts(List<Product> medicalStoreProducts) {
        this.medicalStoreProducts = medicalStoreProducts;
    }

    
    

}
