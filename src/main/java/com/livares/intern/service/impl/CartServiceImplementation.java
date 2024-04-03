package com.livares.intern.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livares.intern.DTO.CartDTO;
import com.livares.intern.entity.Product;
import com.livares.intern.entity.UserProductCart;
import com.livares.intern.entity.Users;
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
		
		Users users = usersRepository.findById(userId).get();
		Product product = productRepository.findById(productId).get();
		int count=userProductCartRepository.countOfProducts(userId,productId);
         if(count==0) {
        	 
			UserProductCart userProductCart = new UserProductCart();
			userProductCart.setUserId(users);
			userProductCart.setProductId(product);
			userProductCartRepository.save(userProductCart);
			return "Product added successfully";
         }else {
        	 return "already exist";
         }
		

	}

	@Override
	public List<CartDTO> fetchDetails(long userId) {

		return userProductCartRepository.fetchData(userId);
	}

//	@Override
//	public String delete(long userId) {
//		userProductCartRepository.deleteByUserId(userId);
//		return "deleted successfully";
//	}

}
