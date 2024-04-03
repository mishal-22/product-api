package com.livares.intern.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.livares.intern.entity.Category;
import com.livares.intern.entity.Product;

public interface ProductService {

	String addProduct(Product product);

	Page<Product> getAllProduct(Integer pageNo,Integer pageSize);

	String updateProduct(Product product);

	String deleteProduct(long id);

	Optional<Product> getProduct(long id);

	String addCategory(Category category);

	ResponseEntity<String> addAllProducts(List<Product> products);

	List<Product> getProductByCategory(long categoryId);

	

}
