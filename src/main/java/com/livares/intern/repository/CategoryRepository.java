package com.livares.intern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livares.intern.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	
}
