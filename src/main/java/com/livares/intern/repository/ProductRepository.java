package com.livares.intern.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.livares.intern.DTO.ProductDTO;
import com.livares.intern.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Page<Product> findAll(Pageable pageable);

	Product findByName(String name);

	@Query("select new com.livares.intern.DTO.ProductDTO(p.name,p.description,p.img,p.price,c.category,p.quantity) from Product p join Category c on p.categoryId.id=c.id where c.category=:category")
	List<ProductDTO> findByCategoryName(String category);

	@Query("select p from #{#entityName} p where p.quantity=:qty")
	List<Product> findByProductQty(int qty);

	@Query("select new com.livares.intern.DTO.ProductDTO(p.name,p.description,p.img,p.price,c.category,p.quantity) from Product p join Category c on p.categoryId.id=c.id")
	Page<ProductDTO> findAllCategory(Pageable pageable);

	@Query("select new com.livares.intern.DTO.ProductDTO(p.name,p.description,p.img,p.price,c.category,p.quantity) "
			+ " from Product p join Category c on p.categoryId.id=c.id where p.name like %:name%")
	ProductDTO getProductByName(String name);
}
