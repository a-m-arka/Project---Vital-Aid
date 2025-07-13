package com.vital_aid_crud_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vital_aid_crud_api.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPersonEmail(String personEmail);

    Optional<User> findByPersonPhone(String personPhone);

    // Custom methods to exclude a specific user by ID
    Optional<User> findByPersonEmailAndPersonIdNot(String personEmail, Long personId);

    Optional<User> findByPersonPhoneAndPersonIdNot(String personPhone, Long personId);
}
