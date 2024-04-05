package com.livares.intern.service;

import java.util.List;

import com.livares.intern.DTO.UserDTO;
import com.livares.intern.DTO.UserFetchDTO;

public interface UsersService {

	String addUser(UserDTO user);

	List<UserFetchDTO> getAllUsers();

	String deleteUser(long id);

	String login(String userName, String password);





	

}
