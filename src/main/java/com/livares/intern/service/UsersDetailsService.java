package com.livares.intern.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.livares.intern.entity.Users;
import com.livares.intern.repository.UsersRepository;

@Service
public class UsersDetailsService implements UserDetailsService{

	@Autowired
	private UsersRepository usersRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> user=usersRepository.findByUsername(username);
		
		if(user.isPresent()) {
			var userObj=user.get();
			return User.builder()
					.username(userObj.getUsername())
					.password(userObj.getPassword())
					.roles(getRoles(userObj))
					.build();
		}
		else {
			throw new UsernameNotFoundException(username);
		}
		
	}
	private String[] getRoles(Users userObj) {
	     if(userObj.getRole()==null) {
	    	 return new String[]{"USER"};
	     }
		return userObj.getRole().split(",");
	}

}
