package com.vital_aid_crud_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vital_aid_crud_api.Entity.Ambulance;

public interface AmbulanceRepository extends JpaRepository<Ambulance, String> {
    public Boolean existsByAmbulanceNumPlate(String numPlate);
}
