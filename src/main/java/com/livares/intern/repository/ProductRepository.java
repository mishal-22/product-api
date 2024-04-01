package com.livares.intern.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livares.intern.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Product findByName(String name);

}
