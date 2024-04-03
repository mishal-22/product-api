package com.livares.intern.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.livares.intern.DTO.CartDTO;
import com.livares.intern.service.CartService;

@RestController
@RequestMapping("cart")
public class CartController {
	
	@Autowired
    CartService cartService;
	
	
	
	@PostMapping("add")
	public String addToCart(long userId,long ProductId) {
		return cartService.addToCart(userId,ProductId);
	}
	
	@PostMapping("fetchDetails")
	public List<CartDTO> fetchDetails(@RequestParam long userId){
		return cartService.fetchDetails(userId);
	}
	
//	@DeleteMapping("delete/{userId}")
//	public String deleteFromCart(@PathVariable long userId) {
//		return cartService.delete(userId);
//	}
}
