package com.ayush.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRequestDTO {
	@NotBlank(message = "Name is requierd")
	private String name;
	
	@NotBlank(message = "Gender is required")
	private String gender;
	
	@NotNull(message = "Age is Required")
	@Min(value =0,message = "Value must be positive")
	private Integer age;
	
	@Email(message = "Please provide a valid email")
	@NotBlank(message = "Email is required")
	private String email;

}
