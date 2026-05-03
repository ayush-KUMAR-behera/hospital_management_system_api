package com.ayush.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientResponseDTO {
	private Long id;
	private String name;
	private String gender;
	private Integer age;
	private String email;
}
