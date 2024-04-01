package com.livares.intern.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import com.livares.intern.entity.AuthRequest;
import com.livares.intern.entity.Users;
import com.livares.intern.service.JwtService;
import com.livares.intern.service.UsersService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UsersController {
	@Autowired
	UsersService usersService;
	
//	@Autowired
//	private JwtService jwtService;
	
//	@Autowired
//	private AuthenticationManager authenticationManager;
	
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

//	@PostMapping("add-admin")
//	public String addAdmin(@RequestBody Users users) {
//		
//		
//		return usersService.addAdmin(users) ;
//	}
	
//	@GetMapping("user/userProfile")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
//	public String userProfile() {
//	   return "Welcome to user profile";	
//	}
//	@GetMapping("admin/adminProfile")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
//	public String adminProfile() {
//	   return "Welcome to admin profile";	
//	}
//	
//	@PostMapping("generateToken")
//	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
//		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
//		if(authentication.isAuthenticated()) {
//			return jwtService.generateToken(authRequest.getEmail());
//		}
//		else {
//			throw new UsernameNotFoundException("Invalid user Request!");
//		}
//	}
	

}
