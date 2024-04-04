package com.livares.intern.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.livares.intern.DTO.ProductDTO;

public interface ProductService {

	String addProduct(ProductDTO product);

	Page<ProductDTO> getAllProduct(Integer pageNo,Integer pageSize);

	String updateProduct(ProductDTO product);

	String deleteProduct(long id);

	ProductDTO getProduct(String name);

	String addCategory(String category);

	String addAllProducts(List<ProductDTO> products);

	List<ProductDTO> getProductByCategory(String category);

	

}
