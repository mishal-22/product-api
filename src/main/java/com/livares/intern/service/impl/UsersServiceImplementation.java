package com.livares.intern.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.livares.intern.DTO.UserDTO;
import com.livares.intern.DTO.UserFetchDTO;
import com.livares.intern.entity.Users;
import com.livares.intern.exception.CustomException;
import com.livares.intern.exception.ErrorCodes;
import com.livares.intern.repository.UsersRepository;
import com.livares.intern.response.ResponseHandler;
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

		return usersRepository.findAllUsers();
				
	}

	@Override
	public String deleteUser(long id) {
		usersRepository.deleteById(id);
		return "User deleted successfully";
				
	}

//	@Override
//	public ResponseEntity< String> addAdmin(Users users) {
//		usersRepository.save(users);
//		return  "Admin user added successfully";
//	}

//	@Override
//	public String login(String userName, String password) {
//
//		return null;
//	}

}
