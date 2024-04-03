package com.livares.intern.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	String name;
	String description;
	String img;
	float price;
	String category;
	int quantity;
}
