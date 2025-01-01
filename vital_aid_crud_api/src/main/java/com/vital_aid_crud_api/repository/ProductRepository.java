package com.vital_aid_crud_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vital_aid_crud_api.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
