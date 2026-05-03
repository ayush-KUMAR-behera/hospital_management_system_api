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

import com.ayush.dto.BillRequestDTO;
import com.ayush.dto.BillResponseDTO;
import com.ayush.enums.PaymentStatus;
import com.ayush.service.BillService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/billing")
@RequiredArgsConstructor
public class BillingController {

	private final BillService service;
	
	@GetMapping
	public Page<BillResponseDTO> getAllBills(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "id") String sortBy
			){
		return service.getAllBills(page, size, sortBy);
	}
	
	@GetMapping("/{id}")
	public BillResponseDTO getBillById(@PathVariable Long id) {
		return service.getBillById(id);
	}
	
	@PostMapping
	public BillResponseDTO generateBill(@Valid @RequestBody BillRequestDTO dto) {
		return service.generateBill(dto.getAppointmentId(),dto.getAmount());
	}
	
	@PutMapping("/{id}")
	public BillResponseDTO updatePaymentStatus(@PathVariable Long id,@RequestParam PaymentStatus status) {
		return service.updatePaymentStatus(id,status);
	}
	
	
}
