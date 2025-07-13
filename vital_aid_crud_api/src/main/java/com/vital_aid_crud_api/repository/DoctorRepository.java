package com.vital_aid_crud_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vital_aid_crud_api.Entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByPersonEmail(String personEmail);

    Optional<Doctor> findByPersonPhone(String personPhone);

    // Custom methods to exclude a specific user by ID
    Optional<Doctor> findByPersonEmailAndPersonIdNot(String personEmail, Long personId);

    Optional<Doctor> findByPersonPhoneAndPersonIdNot(String personPhone, Long personId);
}
