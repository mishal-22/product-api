package com.livares.intern.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.livares.intern.DTO.LoginResponse;
import com.livares.intern.DTO.UserDTO;
import com.livares.intern.DTO.UserFetchDTO;
import com.livares.intern.DTO.UsersLoginDto;
import com.livares.intern.entity.Users;
import com.livares.intern.response.ResponseHandler;
import com.livares.intern.service.AuthenticationService;
import com.livares.intern.service.JwtService;
import com.livares.intern.service.UsersService;

@RestController
public class UsersController {
	@Autowired
	UsersService usersService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/register/user")
	public ResponseEntity<Object> addUser(@RequestBody UserDTO user) {

		String response = usersService.addUser(user);
		return ResponseHandler.generateResponse(response, HttpStatus.OK, user);
	}

	@PostMapping("login")
	public ResponseEntity<Object> loginPage(@RequestBody UsersLoginDto loginDto) {
		Users authenticatedUser = authenticationService.authenticate(loginDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
		return ResponseHandler.generateResponse("Success", HttpStatus.OK, loginResponse);
	}

	@GetMapping("get-all-users")
	public ResponseEntity<Object> getAllUsers() {
		List<UserFetchDTO> userDTOs = usersService.getAllUsers();
		return ResponseHandler.generateResponse("Users", HttpStatus.OK, userDTOs);
	}

	@DeleteMapping("delete-user/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable long id) {
		String response = usersService.deleteUser(id);
		return ResponseHandler.generateResponse(response, HttpStatus.OK, id);
	}

	@GetMapping("user/userProfile")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String userProfile() {
	   return "Welcome to user profile";	
	}
	@GetMapping("admin/adminProfile")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String adminProfile() {
	   return "Welcome to admin profile";	
	}
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
