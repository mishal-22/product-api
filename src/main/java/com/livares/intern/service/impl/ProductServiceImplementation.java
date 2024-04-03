package com.livares.intern.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.livares.intern.DTO.ProductDTO;
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
	public String addProduct(ProductDTO productDto) {

		if (productRepository.findByName(productDto.getName()) == null) {
			Product product = new Product();
			product.setName(productDto.getName());
			product.setDescription(productDto.getDescription());
			product.setImg(productDto.getImg());
			product.setPrice(productDto.getPrice());
			product.setQuantity(productDto.getQuantity());

			Category productCategory = new Category();
			productCategory.setCategory(productDto.getCategory());
			product.setCategoryId(productCategory);

			Category category = categoryRepository.findByCategory(productDto.getCategory()).orElse(null);
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
	public Page<ProductDTO> getAllProduct(Integer pageNo, Integer pageSize) {

		Pageable paging = PageRequest.of(pageNo, pageSize);

		return productRepository.findAllCategory(paging);

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
	public String addCategory(String category) {
		Category c=new Category();
		c.setCategory(category);
		categoryRepository.save(c);
		return "Category added successfully";
	}

	@Override
	public ResponseEntity<String> addAllProducts(List<ProductDTO> productDto) {
		for (ProductDTO p : productDto) {
			if (productRepository.findByName(p.getName()) == null) {
				if (productRepository.findByName(p.getName()) == null) {
					Product product = new Product();
					product.setName(p.getName());
					product.setDescription(p.getDescription());
					product.setImg(p.getImg());
					product.setPrice(p.getPrice());
					product.setQuantity(p.getQuantity());

					Category productCategory = new Category();
					productCategory.setCategory(p.getCategory());
					product.setCategoryId(productCategory);

					Category category = categoryRepository.findByCategory(p.getCategory()).orElse(null);
					if (category == null) {
						category = categoryRepository.save(product.getCategoryId());
					}
					product.setCategoryId(category);
					productRepository.save(product);
			}

			}
	}
		return new ResponseEntity<>("Products added successfully", HttpStatus.OK);
	}

	@Override
	public List<Product> getProductByCategory(long categoryId) {

		return productRepository.findByCategoryId(categoryId);
	}

}
