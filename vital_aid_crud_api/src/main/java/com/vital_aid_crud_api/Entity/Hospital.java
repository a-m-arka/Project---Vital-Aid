package com.vital_aid_crud_api.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "hospital_table")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long hospitalId;

    @Column(length = 200,columnDefinition = "VARCHAR(200) DEFAULT ''")
    private String hospitalName;

    @Column(length = 500,columnDefinition = "VARCHAR(500) DEFAULT ''")
    private String hospitalLocation;

    @Column(length = 15,columnDefinition = "VARCHAR(15) DEFAULT ''")
    private String hospitalContact;

    @Column(length = 100,columnDefinition = "VARCHAR(100) DEFAULT ''")
    private String hospitalEmail;

    @Column(length = 20,columnDefinition = "VARCHAR(20) DEFAULT ''")
    private String hospitalCity;

    @Column(nullable = true, length = 500, columnDefinition = "VARCHAR(500) DEFAULT ''")
    private String hospitalPhotoUrl; // Optional field for storing the image URL

    @Column(nullable = true)
    private Integer totalGeneralBeds;

    @Column(nullable = true)
    private Integer totalIcuBeds;

    @Column(nullable = true)
    private Integer totalVentilators;

    @ElementCollection
    @CollectionTable(name = "hospital_Available_Facilities", joinColumns = @JoinColumn(name = "hospital_id"))
    @Column(length = 100)
    private Set<String> hospitalFacilities;

    @OneToMany(mappedBy = "worksAt", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<Doctor> doctors = new ArrayList<Doctor>();
    
    @ManyToOne(cascade = CascadeType.PERSIST) // Many doctors can work at one hospital
    @JoinColumn(name = "hospitalManagingAdminId") // Ensures FK relationship
    private Admin hospitalManagedBy;



    public Hospital() {}

    public Long getHospitalId() {
        return this.hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
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

    public String getHospitalPhotoUrl() {
        return this.hospitalPhotoUrl;
    }

    public void setHospitalPhotoUrl(String hospitalPhotoUrl) {
        this.hospitalPhotoUrl = hospitalPhotoUrl;
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

    public List<Doctor> getDoctors() {
        return this.doctors;
    }



    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Admin getHospitalManagedBy() {
        return this.hospitalManagedBy;
    }

    public void setHospitalManagedBy(Admin hospitalManagedBy) {
        this.hospitalManagedBy = hospitalManagedBy;
    }

    public String getHospitalCity() {
        return this.hospitalCity;
    }

    public void setHospitalCity(String hospitalCity) {
        this.hospitalCity = hospitalCity;
    }


    @Override
    public String toString() {
        return "{" +
            " hospitalId='" + getHospitalId() + "'" +
            ", hospitalName='" + getHospitalName() + "'" +
            ", hospitalLocation='" + getHospitalLocation() + "'" +
            ", hospitalContact='" + getHospitalContact() + "'" +
            ", hospitalEmail='" + getHospitalEmail() + "'" +
            ", hospitalCity='" + getHospitalCity() + "'" +
            ", hospitalPhotoUrl='" + getHospitalPhotoUrl() + "'" +
            ", totalGeneralBeds='" + getTotalGeneralBeds() + "'" +
            ", totalIcuBeds='" + getTotalIcuBeds() + "'" +
            ", totalVentilators='" + getTotalVentilators() + "'" +
            ", hospitalFacilities='" + getHospitalFacilities() + "'" +
            ", doctors='" + getDoctors() + "'" +
            ", hospitalManagedBy='" + getHospitalManagedBy() + "'" +
            "}";
    }
    
    


}
