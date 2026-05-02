package com.ayush.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ayush.entity.User;
import com.ayush.repository.UserRepository;
import com.ayush.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final JwtUtil jwtUtil;

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;
    
    public String login(String username, String password) {

        User user = repo.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}
