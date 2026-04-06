package com.ayush.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.dto.LoginRequestDTO;
import com.ayush.dto.LoginResponseDTO;
import com.ayush.service.AuthService;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {

        String token = service.login(request.getUsername(), request.getPassword());

        LoginResponseDTO res = new LoginResponseDTO();
        res.setToken(token);

        return res;
    }
}

