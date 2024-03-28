package com.livares.intern.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.livares.intern.entity.Users;
import com.livares.intern.service.UsersService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UsersController {
	@Autowired
	UsersService usersService;
	
	@PostMapping("/add-user")
	public String addUser(@RequestBody Users user) {
		return usersService.addUser(user);
	}
	
	@GetMapping("get-all-users")
	public List<Users> getAllUsers() {
		return usersService.getAllUsers();
	}
	
	@DeleteMapping("delete-user/{id}")
	public String deleteUser(@PathVariable int id) {
		return usersService.deleteUser(id);
	}

	@PostMapping("add-admin")
	public String addAdmin(@RequestBody Users users) {
		
		
		return usersService.addAdmin(users) ;
	}
	
	
	

}
