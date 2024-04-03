package com.livares.intern.service;

import java.util.List;

import com.livares.intern.DTO.UserDTO;
import com.livares.intern.entity.Users;

public interface UsersService {

	String addUser(UserDTO user);

	List<Users> getAllUsers();

	String deleteUser(long id);

	String addAdmin(Users users);

//	String login(String userName, String password);

	

}
