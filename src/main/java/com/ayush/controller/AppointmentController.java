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

import com.ayush.entity.Appointment;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

	@GetMapping
	public List<Appointment> getAllAppointment(){
		System.out.println("Get all Appointments");
		return null;
	}
	
	@GetMapping("/{id}")
	public Appointment getAppointmentById(@PathVariable Long id) {
		return null;
	}
	
	@PostMapping
	public String createAppointment(@RequestBody Appointment appointment) {
		return null;
	}
	
	@PutMapping("/{id}")
	public String updateAppointment(@PathVariable Long id) {
		return null;
	}
	
	@DeleteMapping("/{id}")
	public void deleteAppointment(@PathVariable Long id) {
		
	}
}
