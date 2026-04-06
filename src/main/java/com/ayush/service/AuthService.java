package com.ayush.service;

import org.springframework.stereotype.Service;

import com.ayush.entity.User;
import com.ayush.repository.UserRepository;
import com.ayush.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repo;

    public String login(String username, String password) {

        User user = repo.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return JwtUtil.generateToken(username);
    }
}
