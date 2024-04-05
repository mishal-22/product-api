package com.livares.intern.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
import com.livares.intern.response.ResponseHandler;
import com.livares.intern.service.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Products", description = "Product management APIs")
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/add")
	public ResponseEntity<Object> addProduct(@RequestBody ProductDTO product) {
		String response = productService.addProduct(product);
		return ResponseHandler.generateResponse(response, HttpStatus.OK, product);
	}

	@PostMapping("/addItems")
	public ResponseEntity<Object> addMultipleProducts(@RequestBody List<ProductDTO> products) {
		String response = productService.addAllProducts(products);
		return ResponseHandler.generateResponse(response, HttpStatus.OK, products);
	}

	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllProduct(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "5") Integer pageSize) {
		Page<ProductDTO> productPage = productService.getAllProduct(pageNo, pageSize);

		return ResponseHandler.generateResponse("Products", HttpStatus.OK, productPage);

	}

	@GetMapping("get/{name}")
	public ResponseEntity<Object> getProduct(@PathVariable String name) {
		ProductDTO productDTO = productService.getProduct(name);
		return ResponseHandler.generateResponse("Product", HttpStatus.OK, productDTO);
	}

	@PutMapping("update")
	public ResponseEntity<Object> updateProduct(@RequestBody ProductDTO product) {
		String response = productService.updateProduct(product);
		return ResponseHandler.generateResponse(response, HttpStatus.OK, product);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable long id) {
		String response = productService.deleteProduct(id);
		return ResponseHandler.generateResponse(response, HttpStatus.OK, id);
	}

	@PostMapping("addCategory")
	public ResponseEntity<Object> addCategory(@RequestBody String category) {

		String response = productService.addCategory(category);
		return ResponseHandler.generateResponse(response, HttpStatus.OK, category);
	}

	@GetMapping("getByCategory/{category}")
	public ResponseEntity<Object> getByCategory(@PathVariable String category) {
		List<ProductDTO> productDTO = productService.getProductByCategory(category);
		return ResponseHandler.generateResponse("Products by category", HttpStatus.OK, productDTO);
	}

}
