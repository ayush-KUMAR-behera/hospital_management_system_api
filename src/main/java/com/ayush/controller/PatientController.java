 package com.ayush.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.entity.Patient;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

  
	
	@GetMapping
	public List<Patient> getAllPatients(){
		System.out.println("Fetching the patients");
		return null;
	}
	
	@PostMapping
	public Patient createPatient(@RequestBody Patient patient) {
		System.out.println("Creating Patient");
		return patient;
	}
	
	@GetMapping("/{id}")
	public Patient getPatientById(@PathVariable Long id) {
		System.out.println("Fetching patient by Id");
		return null;
	}
	
	@DeleteMapping("/{id}")
	public void deletePatient(@PathVariable Long id) {
		
	}

}
