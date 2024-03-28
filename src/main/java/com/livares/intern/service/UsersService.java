package com.livares.intern.service;

import java.util.List;


import com.livares.intern.entity.Users;

public interface UsersService {

	String addUser(Users user);

	List<Users> getAllUsers();

	String deleteUser(int id);

	String addAdmin(Users users);

	

}
