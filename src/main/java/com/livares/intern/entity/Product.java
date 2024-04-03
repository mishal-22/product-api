package com.livares.intern.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
 
	@Id
	long id;
	String name;
	String description;
	String img;
	float price;
	@ManyToOne
	@JoinColumn(name = "categoryId", referencedColumnName = "id")
	Category categoryId;
	int quantity;

	
	
}
