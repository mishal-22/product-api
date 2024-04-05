package com.livares.intern.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livares.intern.DTO.CartDTO;
import com.livares.intern.entity.Product;
import com.livares.intern.entity.UserProductCart;
import com.livares.intern.entity.Users;
import com.livares.intern.exception.CustomException;
import com.livares.intern.exception.ErrorCodes;
import com.livares.intern.repository.ProductRepository;
import com.livares.intern.repository.UserProductCartRepository;
import com.livares.intern.repository.UsersRepository;
import com.livares.intern.service.CartService;

@Service
public class CartServiceImplementation implements CartService {
	@Autowired
	UserProductCartRepository userProductCartRepository;
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	ProductRepository productRepository;

	@Override
	public String addToCart(long userId, long productId) {

		Users users = usersRepository.findById(userId).orElse(null);
		Product product = productRepository.findById(productId).orElse(null);
		if (users == null || product == null) {
			throw new CustomException(ErrorCodes.NOT_FOUND, "User or product is not found");
		}
		int count = userProductCartRepository.countOfProducts(userId, productId);
		if (count == 0) {

			UserProductCart userProductCart = new UserProductCart();
			userProductCart.setUserId(users);
			userProductCart.setProductId(product);
			userProductCartRepository.save(userProductCart);
			return "Product added successfully";

		} else {
			throw new CustomException(ErrorCodes.METHOD_NOT_SUPPORTED, "Product already added to cart");
		}

	}

	@Override
	public List<CartDTO> fetchDetails(long userId) {

		List<CartDTO> cartDTOs = userProductCartRepository.fetchData(userId);
		if (cartDTOs == null || cartDTOs.isEmpty()) {

			throw new CustomException(ErrorCodes.NOT_FOUND, "Items not found with this id");
		} else {
			return cartDTOs;
		}

	}

	@Override
	public String deleteUser(long userId, long productId) {

		UserProductCart cart = userProductCartRepository.findByUserId(userId, productId);
		if (cart == null) {
			throw new CustomException(ErrorCodes.NOT_FOUND, "Item not found in the cart with this id");
		}
		long id = cart.getId();

		userProductCartRepository.deleteById(id);
		return "deleted successfully";

	}

}
