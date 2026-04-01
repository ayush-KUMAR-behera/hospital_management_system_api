package com.ayush.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.entity.Doctor;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
	
	@GetMapping
	public List<Doctor> getAllDoctors(){
		System.out.println("get All Doctors");
		return null;
	}
	
	@GetMapping("/{id}")
	public Doctor getDoctorById(Long id) {
		return null;
	}
	
	@PostMapping
	public String createDoctor(@RequestBody Doctor doctor) {
		return null;
	}
	
	@PutMapping("/{id}")                                         
	public void uppdateDoctor(@PathVariable Long id) {
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteDoctor(@PathVariable Long id) {
		
	}

}
