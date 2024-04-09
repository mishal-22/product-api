package com.livares.intern.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.livares.intern.DTO.UsersLoginDto;
import com.livares.intern.entity.Users;
import com.livares.intern.repository.UsersRepository;

@Service
public class AuthenticationService {

	 @Autowired
	    UsersRepository userRepository;

	    @Autowired
	    PasswordEncoder passwordEncoder;

	    @Autowired
	    AuthenticationManager authenticationManager;

	    public Users authenticate(UsersLoginDto input) {
	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword()));

	        return userRepository.findByUsername(input.getUsername()).orElseThrow();
	    }
}
