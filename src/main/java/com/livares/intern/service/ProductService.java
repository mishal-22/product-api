package com.livares.intern.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.livares.intern.DTO.ProductDTO;
import com.livares.intern.entity.Category;
import com.livares.intern.entity.Product;

public interface ProductService {

	String addProduct(ProductDTO product);

	Page<ProductDTO> getAllProduct(Integer pageNo,Integer pageSize);

	String updateProduct(Product product);

	String deleteProduct(long id);

	Optional<Product> getProduct(long id);

	String addCategory(String category);

	ResponseEntity<String> addAllProducts(List<ProductDTO> products);

	List<Product> getProductByCategory(long categoryId);

	

}
