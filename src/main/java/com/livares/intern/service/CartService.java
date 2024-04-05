package com.livares.intern.service;

import java.util.List;

import com.livares.intern.DTO.CartDTO;

public interface CartService {

	String addToCart(long userId,long productId);

	List<CartDTO> fetchDetails(long userId);

	String deleteUser(long userId,long productId);

}
