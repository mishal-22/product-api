package com.livares.intern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livares.intern.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	

}
