package com.vital_aid_crud_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vital_aid_crud_api.Entity.ProductRating;

public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {

}
