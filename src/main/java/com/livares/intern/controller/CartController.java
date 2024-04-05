package com.livares.intern.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.livares.intern.DTO.CartDTO;
import com.livares.intern.response.ResponseHandler;
import com.livares.intern.service.CartService;

@RestController
@RequestMapping("cart")
public class CartController {

	@Autowired
	CartService cartService;

	@PostMapping("add")
	public ResponseEntity<Object> addToCart(long userId, long ProductId) {
		String response = cartService.addToCart(userId, ProductId);
		return ResponseHandler.generateResponse(response, HttpStatus.OK, ProductId);
	}

	@PostMapping("fetchDetails")
	public ResponseEntity<Object> fetchDetails(@RequestParam long userId) {
		List<CartDTO> cartDTO = cartService.fetchDetails(userId);
		return ResponseHandler.generateResponse("Cart Data", HttpStatus.OK, cartDTO);
	}

	@DeleteMapping("delete")
	public ResponseEntity<Object> deleteFromCart(@RequestParam long userId, @RequestParam long productId) {

		String response = cartService.deleteUser(userId, productId);
		return ResponseHandler.generateResponse(response, HttpStatus.OK, userId);
	}
}
