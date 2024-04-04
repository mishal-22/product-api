package com.livares.intern.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.livares.intern.DTO.UserDTO;
import com.livares.intern.DTO.UserFetchDTO;

public interface UsersService {

	String addUser(UserDTO user);

	List<UserFetchDTO> getAllUsers();

	String deleteUser(long id);

//	ResponseEntity< String> addAdmin(Users users);

//	String login(String userName, String password);

	

}
