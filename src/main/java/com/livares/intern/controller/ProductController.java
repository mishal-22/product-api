package com.livares.intern.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livares.intern.entity.Product;
import com.livares.intern.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	
	@PostMapping("/add")
	public String addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	@GetMapping("/getAll")
	public List<Product> getAllProduct(){
		return productService.getAllProduct();
		
	}
	@GetMapping("get/{id}")
	public Optional<Product> getProduct(@PathVariable int id) {
		return productService.getProduct(id);
	}
	@PutMapping("update")
	public String updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}
	@DeleteMapping("delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		return productService.deleteProduct(id);
	}
}