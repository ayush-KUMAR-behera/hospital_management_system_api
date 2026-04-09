package com.ayush.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.dto.RegisterRequestDTO;
import com.ayush.service.RegisterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth/register")
@RequiredArgsConstructor
public class RegisterController {
	
	private final RegisterService service;
	
	@PostMapping()
	public String register(@RequestBody RegisterRequestDTO dto) {
		service.register(dto);
		return "Register Success Fully";
	}

}
