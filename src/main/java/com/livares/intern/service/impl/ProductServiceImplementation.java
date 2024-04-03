package com.livares.intern.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.livares.intern.entity.Category;
import com.livares.intern.entity.Product;
import com.livares.intern.repository.CategoryRepository;
import com.livares.intern.repository.ProductRepository;
import com.livares.intern.service.ProductService;

@Service
public class ProductServiceImplementation implements ProductService {
	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public String addProduct(Product product) {

		if (productRepository.findByName(product.getName()) == null) {

			Category category = categoryRepository.findById(product.getCategoryId().getId()).orElse(null);
			if (category == null) {
				category = categoryRepository.save(product.getCategoryId());
			}
			product.setCategoryId(category);
			productRepository.save(product);
			return "Product added successfully";
		} else {
			return "Product alredy exist";
		}
	}

	@Override
	public Page<Product> getAllProduct(Integer pageNo,Integer pageSize) {
		
		Pageable paging=PageRequest.of(pageNo, pageSize);
		
	return productRepository.findAll(paging);

//		if(pagedResultPage.hasContent()) {
//			return pagedResultPage.getContent();
//		}
//		else {
//			return new ArrayList<Product>();
//		}
		
		
	}

	@Override
	public String updateProduct(Product product) {
		productRepository.save(product);
		return "Product succesfully updated";
	}

	@Override
	public String deleteProduct(long id) {

		productRepository.deleteById(id);
		return "Product with id " + id + " is deleted successfully";
	}

	@Override
	public Optional<Product> getProduct(long id) {

		return productRepository.findById(id);
	}

	@Override
	public String addCategory(Category category) {
		categoryRepository.save(category);
		return "Category added successfully";
	}

	@Override
	public ResponseEntity<String> addAllProducts(List<Product> products) {
		for (Product p : products) {
			if (productRepository.findByName(p.getName()) == null) {
				Category category = categoryRepository.findById(p.getCategoryId().getId()).orElse(null);
				if (category == null) {
					category = categoryRepository.save(p.getCategoryId());
				}
				p.setCategoryId(category);
				productRepository.save(p);
			}

		}
		return new ResponseEntity<>("Products added successfully", HttpStatus.OK);
	}

	@Override
	public List<Product> getProductByCategory(long categoryId) {

		return productRepository.findByCategoryId(categoryId);
	}

}
