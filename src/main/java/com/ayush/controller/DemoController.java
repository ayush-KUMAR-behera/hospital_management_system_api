package com.ayush.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/jwt")
	public String sayHello() {
		return "Jwt is working";
	}
}
