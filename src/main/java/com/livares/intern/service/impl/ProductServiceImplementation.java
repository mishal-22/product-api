package com.livares.intern.service.impl;

import java.util.List;

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
import com.livares.intern.exception.CustomException;
import com.livares.intern.exception.ErrorCodes;
import com.livares.intern.repository.CategoryRepository;
import com.livares.intern.repository.ProductRepository;
import com.livares.intern.response.ResponseHandler;
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
			return "Product successfully added";
		} else {
			throw new CustomException(ErrorCodes.BAD_REQUEST, "Product alredy exist");
		}
	}

	@Override
	public Page<ProductDTO> getAllProduct(Integer pageNo, Integer pageSize) {

		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<ProductDTO> productPage= productRepository.findAllCategory(paging);
		if(productPage!=null) {
			
			return productPage;
			}
			else {
				throw new CustomException(ErrorCodes.NOT_FOUND, "Products doesn't exist");
			}

		

//		if(pagedResultPage.hasContent()) {
//			return pagedResultPage.getContent();
//		}
//		else {
//			return new ArrayList<Product>();
//		}

	}

	@Override
	public String updateProduct(ProductDTO product) {
		Product dbProduct = productRepository.findByName(product.getName());
		dbProduct.setDescription(product.getDescription());
		dbProduct.setImg(product.getImg());
		dbProduct.setPrice(product.getPrice());
		dbProduct.setQuantity(product.getQuantity());
		productRepository.save(dbProduct);
		return "Product Updated successfully";
	}

	@Override
	public String deleteProduct(long id) {

		productRepository.deleteById(id);
		return "Deleted successfully";
				
	}

	@Override
	public ProductDTO getProduct(String name) {

		
		return productRepository.getProductByName(name);
				
	}

	@Override
	public String addCategory(String category) {
		Category c = categoryRepository.findByCategory(category).orElse(null);
		if (c == null) {
			c = new Category();
			c.setCategory(category);
			categoryRepository.save(c);
			return "Category added successfully";
					
		} else {
			throw new CustomException(ErrorCodes.BAD_REQUEST, "Category already exist");
		}

	}

	@Override
	public String addAllProducts(List<ProductDTO> productDto) {
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
		return "Products added successfully";
		
	}

	@Override
	public List<ProductDTO> getProductByCategory(String category) {

		return productRepository.findByCategoryName(category);
				
	}

}
