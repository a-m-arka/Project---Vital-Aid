package com.vital_aid_crud_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vital_aid_crud_api.Entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByPersonEmail(String personEmail);

}
