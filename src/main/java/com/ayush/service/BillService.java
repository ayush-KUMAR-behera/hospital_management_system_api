package com.ayush.service;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.ayush.dto.BillResponseDTO;
import com.ayush.enums.PaymentStatus;

public interface BillService {
	
	Page<BillResponseDTO> getAllBills(int page,int size,String sortBy);
	public BillResponseDTO getBillById(Long billId);
	public BillResponseDTO generateBill(Long appointmentId,BigDecimal amount);
	public BillResponseDTO updatePaymentStatus(Long billId,PaymentStatus status);
	
}
