package com.ayush.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ayush.dto.RegisterRequestDTO;
import com.ayush.entity.User;
import com.ayush.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService {

	private final UserRepository repo;
	private final PasswordEncoder passwordEncoder;
	
	public void register(RegisterRequestDTO dto) {

	    User user = new User();
	    user.setUsername(dto.getUsername());
	    user.setRole(dto.getRole());

	    //  encrypt password
	    user.setPassword(passwordEncoder.encode(dto.getPassword()));

	    repo.save(user);
	}
}
