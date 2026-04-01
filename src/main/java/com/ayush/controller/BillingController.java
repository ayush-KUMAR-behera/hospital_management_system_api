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

import com.ayush.entity.Bill;

@RestController
@RequestMapping("/api/v1/billing")
public class BillingController {

	@GetMapping
	public List<Bill> getAllBills(){
		return null;
	}
	
	@GetMapping("/{id}")
	public Bill getBillById(@PathVariable Long id) {
		return null;
	}
	
	@PostMapping
	public String createBill(@RequestBody Bill bill) {
		return null;
	}
	
	@PutMapping("/{id}")
	public String updatebill(@PathVariable Long id) {
		return null;
	}
	
	@DeleteMapping("/{id}")
	public void deleteBill(@PathVariable Long id) {
	
	}
}
