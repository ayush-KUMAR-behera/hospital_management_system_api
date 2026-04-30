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

import com.ayush.dto.AppointmentRequestDTO;
import com.ayush.dto.AppointmentResponseDTO;
import com.ayush.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {
	
	private final AppointmentService service;

	@GetMapping
	public Page<AppointmentResponseDTO> getAllAppointment(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "id") String sortBy 
			){
	
		return service.getAllAppointments(page, size, sortBy);
	}
	
	@GetMapping("/{id}")
	public AppointmentResponseDTO getAppointmentById(@PathVariable Long id) {
		return service.getAppointmentById(id);
	}
	
	@PostMapping
	public AppointmentResponseDTO createAppointment(@Valid @RequestBody AppointmentRequestDTO dto) {
		return service.createAppointment(dto);
	}
	
	@PutMapping("/{id}")
	public AppointmentResponseDTO updateAppointment(@PathVariable Long id,@Valid @RequestBody AppointmentRequestDTO dto) {
		return service.updateAppointment(id,dto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAppointment(@PathVariable Long id) {
		 service.deleteAppointment(id);
	}
}
