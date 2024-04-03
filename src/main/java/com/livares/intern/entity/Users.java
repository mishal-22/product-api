package com.livares.intern.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users extends BaseEntity  {
	
	String firstName;
	String lastName;
     String username;
     String password;
     
}
