 package com.ayush.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.dto.PatientRequestDTO;
import com.ayush.dto.PatientResponseDTO;
import com.ayush.entity.Patient;
import com.ayush.service.PatientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

  private final PatientService service;
	
  @GetMapping
  public Page<PatientResponseDTO> getAllPatients(
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "5") int size,
          @RequestParam(defaultValue = "id") String sortBy) {

      return service.getAllPatient(page, size, sortBy);
  }
	
	@GetMapping("/{id}")
	public PatientResponseDTO getPatientById(@PathVariable Long id) {
		System.out.println("Fetching patient by Id");
		return service.getPatientById(id);
	}
	
	@PostMapping
	public PatientResponseDTO createPatient(@Valid @RequestBody PatientRequestDTO patient) {
		return service.createPatient(patient) ;
	}
	
	
	@PutMapping("/{id}")
	public String updatePatient(@PathVariable Long id,@Valid @RequestBody PatientRequestDTO dto) {
			service.updatePatient(id, dto);
		return "Patient details updated successfully";
	}
	
	
	@DeleteMapping("/{id}")
	public String deletePatient(@PathVariable Long id) {
		service.deletePatient(id);
		return "Patient deleted Succesfully";
	}

}
