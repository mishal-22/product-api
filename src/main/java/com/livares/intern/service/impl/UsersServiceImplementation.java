package com.livares.intern.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.livares.intern.DTO.UserDTO;
import com.livares.intern.DTO.UserFetchDTO;
import com.livares.intern.entity.Users;
import com.livares.intern.exception.CustomException;
import com.livares.intern.exception.ErrorCodes;
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

		Users users = usersRepository.findByUsername(user.getUsername()).orElse(null);
		try {
			if (users == null) {
				String bCryptPassword = bCryptPasswordEncoder.encode(user.getPassword());
				user.setPassword(bCryptPassword);
				Users newUser = new Users();
				newUser.setFirstName(user.getFirstName());
				newUser.setLastName(user.getLastName());
				newUser.setUsername(user.getUsername());
				newUser.setPassword(user.getPassword());
				newUser.setRole(user.getRole());
				usersRepository.save(newUser);
				return "User added successfully";

			} else {
				throw new CustomException(ErrorCodes.BAD_REQUEST, "User alredy exist");
			}
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<UserFetchDTO> getAllUsers() {

		List<UserFetchDTO> userFetchDTOs = usersRepository.findAllUsers();
		if (userFetchDTOs.isEmpty()) {
			throw new CustomException(ErrorCodes.NOT_FOUND, "Users does not exist");
		} else {

			return userFetchDTOs;
		}

	}

	@Override
	public String deleteUser(long id) {
		Users user = usersRepository.findById(id).orElse(null);
		if (user != null) {

			usersRepository.deleteById(id);
			return "User deleted successfully";
		} else {
			throw new CustomException(ErrorCodes.NOT_FOUND, "User is not found!");
		}

	}

	@Override
	public String login(String userName, String password) {
		Users users = usersRepository.findByUsername(userName).orElse(null);
		if (users == null) {
			return "User doesn't exist";
		} else {

			String db_password = users.getPassword();
			if (db_password.equals(password)) {
				return "Login Successfully Completed";
			} else {
				return "Invalid userName or Password";
			}
		}

	}

}
