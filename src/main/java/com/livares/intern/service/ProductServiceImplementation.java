package com.livares.intern.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livares.intern.entity.Product;
import com.livares.intern.repository.ProductRepository;

@Service
public class ProductServiceImplementation implements ProductService {
	@Autowired
	ProductRepository productRepository;
	@Override
	public String addProduct(Product product) {
		productRepository.save(product);
		return "Product added successfully";
	}

	@Override
	public List<Product> getAllProduct() {
		
		return productRepository.findAll();
	}

	@Override
	public String updateProduct(Product product) {
		productRepository.save(product);
		return "Product succesfully updated"; 
	}

	@Override
	public String deleteProduct(int id) {
		
		productRepository.deleteById(id);
		return "Product with id "+id+" is deleted successfully";
	}

	@Override
	public Optional<Product> getProduct(int id) {
		
		return productRepository.findById(id);
	}

}
