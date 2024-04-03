package com.livares.intern.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
     
	String name;
	String description;
	String img;
	float price;
	String categoryName;
	String userName;
	
}
