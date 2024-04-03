package com.livares.intern.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.livares.intern.DTO.UserDTO;
import com.livares.intern.entity.Users;
import com.livares.intern.repository.UsersRepository;
import com.livares.intern.service.UsersService;

@Service
public class UsersServiceImplementation implements UsersService {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public String addUser(UserDTO user) {

		if (usersRepository.findByUsername(user.getUsername()).isPresent()) {
			String bCryptPassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(bCryptPassword);
			Users newUser = new Users();
			newUser.setFirstName(user.getFirstName());
			usersRepository.save(newUser);
			return "User added successfully";
		} else {
			return "User alredy exist";
		}
	}

	@Override
	public List<Users> getAllUsers() {

		return usersRepository.findAll();
	}

	@Override
	public String deleteUser(long id) {
		usersRepository.deleteById(id);
		return "User deleted successfully";
	}

	@Override
	public String addAdmin(Users users) {
		usersRepository.save(users);
		return "Admin user added successfully";
	}

//	@Override
//	public String login(String userName, String password) {
//
//		return null;
//	}

}
