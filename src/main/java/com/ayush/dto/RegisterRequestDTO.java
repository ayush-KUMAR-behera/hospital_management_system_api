package com.ayush.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {
	@NotBlank(message = "enter your name")
	private String username;
	@NotBlank(message = "Password Requierd")
    private String password;
	@NotBlank(message = "Role is Requierd")
    private String role;
}
