package com.livares.intern.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.livares.intern.entity.Users;

import com.livares.intern.repository.UsersRepository;

@Service
public class UsersServiceImplementation implements UsersService{

	@Autowired
	UsersRepository usersRepository;
	
	
	@Override
	public String addUser(Users user) {
		usersRepository.save(user);
		return "User added successfully";
	}

	@Override
	public List<Users> getAllUsers() {
		
		return usersRepository.findAll();
	}

	@Override
	public String deleteUser(int id) {
		usersRepository.deleteById(id);
		return "User deleted successfully";
	}

	@Override
	public String addAdmin(Users users) {
		usersRepository.save(users);
		return "Admin user added successfully";
	}



}
