package com.ayush.controller;

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

import com.ayush.entity.Doctor;
import com.ayush.service.DoctorService;
import com.ayush.serviceimpl.DoctorServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.ayush.dto.*;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {
	private final DoctorService service;
	
    @GetMapping
    public Page<DoctorResponseDTO> getAllDoctors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        return service.getAllDoctors(page, size, sortBy);
    }
	
	@GetMapping("/{id}")
	public DoctorResponseDTO getDoctorById(@PathVariable Long id) {
		return service.getDoctorById(id);
	}
	
	@PostMapping
	public DoctorResponseDTO createDoctor(@Valid @RequestBody DoctorRequestDTO dto) {
	return	service.createDoctor(dto);
	}
	
	@PutMapping("/{id}")                                         
	public String uppdateDoctor(@PathVariable Long id, @Valid @RequestBody DoctorRequestDTO dto) {
		service.updateDoctor(id, dto);
		return "Update Successfully with id:"+id;
	}
	
	@DeleteMapping("/{id}")
	public String deleteDoctor(@PathVariable Long id) {
		service.deleteDoctor(id);
		return "Doctor deleted successfully";
	}

}
