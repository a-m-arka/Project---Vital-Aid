package com.vital_aid_crud_api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.vital_aid_crud_api.Entity.DoctorRating;

public interface DoctorRatingRepository extends JpaRepository<DoctorRating, Long> {
}
