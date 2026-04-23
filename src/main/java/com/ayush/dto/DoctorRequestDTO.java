package com.ayush.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorRequestDTO {
	@NotBlank(message = "Name is Required")
	private String name;
	@NotBlank(message = "speciality is required")
	private String speciality;
	@NotBlank(message = "Phone Number Required")
	private String phone;

}
