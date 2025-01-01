package com.vital_aid_crud_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vital_aid_crud_api.Entity.Hospital;
import java.util.Optional;


public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    Optional<Hospital> findByHospitalName(String hospitalName);
}