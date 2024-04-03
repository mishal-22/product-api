package com.livares.intern.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.livares.intern.DTO.ProductDTO;
import com.livares.intern.entity.Category;
import com.livares.intern.entity.Product;
import com.livares.intern.service.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Products", description = "Product management APIs")
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/add")
	public String addProduct(@RequestBody ProductDTO product) {
		return productService.addProduct(product);
	}

	@PostMapping("/addItems")
	public ResponseEntity<String> addMultipleProducts(@RequestBody List<ProductDTO> products) {
		return productService.addAllProducts(products);
	}

	@GetMapping("/getAll")
	public Page<ProductDTO> getAllProduct(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "5") Integer pageSize) {
		return productService.getAllProduct(pageNo, pageSize);

	}

	@GetMapping("get/{id}")
	public Optional<Product> getProduct(@PathVariable long id) {
		return productService.getProduct(id);
	}

	@PutMapping("update")
	public String updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}

	@DeleteMapping("delete/{id}")
	public String deleteProduct(@PathVariable long id) {
		return productService.deleteProduct(id);
	}

	@PostMapping("addCategory")
	public String addCategory(@RequestBody String category) {

		return productService.addCategory(category);
	}

	@GetMapping("getByCategory/{categoryId}")
	public List<Product> getByCategory(@PathVariable long categoryId) {
		return productService.getProductByCategory(categoryId);
	}

}
