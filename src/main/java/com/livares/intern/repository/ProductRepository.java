package com.livares.intern.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.livares.intern.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Page<Product> findAll(Pageable pageable);
	
	Product findByName(String name);
	
	@Query(value = "select * from product p where p.category_id=:categoryId ",nativeQuery = true)
	List<Product> findByCategoryId(long categoryId);
	
	@Query("select p from #{#entityName} p where p.quantity=:qty")
	List<Product> findByProductQty(int qty);
	
	
	
	

}
