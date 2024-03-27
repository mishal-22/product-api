package com.livares.intern.service;

import java.util.List;
import java.util.Optional;

import com.livares.intern.entity.Product;

public interface ProductService {

	String addProduct(Product product);

	List<Product> getAllProduct();

	String updateProduct(Product product);

	String deleteProduct(int id);

	Optional<Product> getProduct(int id);

}
