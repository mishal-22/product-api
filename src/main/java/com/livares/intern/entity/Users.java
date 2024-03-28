package com.livares.intern.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
     int id;
     String name;
     String email;
     String password;
     @Column(name = "role", columnDefinition = "VARCHAR(255) DEFAULT 'customer'")
     String role;

    
     // for customer
	public Users(int id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		
	
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// for admin
	public Users(int id, String name, String email, String password, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
     
     
}
