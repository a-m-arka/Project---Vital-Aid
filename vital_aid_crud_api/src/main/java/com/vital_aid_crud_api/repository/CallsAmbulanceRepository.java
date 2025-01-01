package com.vital_aid_crud_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vital_aid_crud_api.EmbeddedClass.CallsAmbulanceId;
import com.vital_aid_crud_api.Entity.CallsAmbulance;

public interface CallsAmbulanceRepository extends JpaRepository<CallsAmbulance, CallsAmbulanceId> {

}
