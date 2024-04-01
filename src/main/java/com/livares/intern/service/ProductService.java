package com.livares.intern.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.livares.intern.entity.Category;
import com.livares.intern.entity.Product;

public interface ProductService {

	String addProduct(Product product);

	List<Product> getAllProduct();

	String updateProduct(Product product);

	String deleteProduct(int id);

	Optional<Product> getProduct(int id);

	String addCategory(Category category);

	ResponseEntity<String> addAllProducts(List<Product> products);

}
