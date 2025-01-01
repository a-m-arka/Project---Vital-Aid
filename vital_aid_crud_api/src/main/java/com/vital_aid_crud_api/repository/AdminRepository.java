package com.vital_aid_crud_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vital_aid_crud_api.Entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByPersonEmail(String email);

    Optional<Admin> findByPersonPhone(String phone);

    // Custom methods to exclude a specific admin by ID
    Optional<Admin> findByPersonEmailAndPersonIdNot(String email, Long id);

    Optional<Admin> findByPersonPhoneAndPersonIdNot(String phone, Long id);
}
