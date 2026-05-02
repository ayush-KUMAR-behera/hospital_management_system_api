package com.ayush.serviceimpl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ayush.dto.BillResponseDTO;
import com.ayush.entity.Appointment;
import com.ayush.entity.Bill;
import com.ayush.enums.PaymentStatus;
import com.ayush.exception.ResourceNotFoundException;
import com.ayush.repository.AppointmentRepository;
import com.ayush.repository.BillRepository;
import com.ayush.service.BillService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BillServiceImpl implements BillService {

	private final BillRepository repo;
	private final AppointmentRepository appRepo;
	private static final Logger logger=LoggerFactory.getLogger(BillServiceImpl.class);
	
	@Override
	public Page<BillResponseDTO> getAllBills(int page, int size, String sortBy) {
		logger.info("Fetch all bills");
		Pageable pageable=PageRequest.of(page, size,Sort.by(sortBy).ascending());
		Page<Bill> billPage=repo.findAll(pageable);
		
		return billPage.map(this::mapToResponse);
	}

	@Override
	public BillResponseDTO getBillById(Long billId) {
		
		logger.info("Fetch bill by id{}",billId);
			Bill bill=repo.findById(billId)
					.orElseThrow(()->
					new ResourceNotFoundException("Not found any bill with this id:"+billId));
		return mapToResponse(bill);
	}

	@Override
	public BillResponseDTO generateBill(Long appointmentId, BigDecimal amount) {
		
			Appointment appointment=appRepo.findById(appointmentId).orElseThrow(
					()->new ResourceNotFoundException("Appointment not found with thid id:"+appointmentId));
			
			if(repo.existsByAppointment(appointment)) {
			    throw new IllegalArgumentException("Bill already exists for this appointment");
			}
			
			Bill bill = new Bill();
			bill.setAmount(amount);
			bill.setAppointment(appointment);
			Bill saved = repo.save(bill);
			logger.info("Bill generate with bill id:"+saved.getId());
		return mapToResponse(saved);
	}

	@Override
	public BillResponseDTO updatePaymentStatus(Long billId, PaymentStatus status) {
		logger.info("Update bill id:"+billId+" status"+status);
		Bill bill=repo.findById(billId).orElseThrow(()-> new ResourceNotFoundException("Bill id not found:"+billId));
		bill.setStatus(status);
		repo.save(bill);
		return mapToResponse(bill);
	}
	
	private BillResponseDTO mapToResponse(Bill bill) {
		BillResponseDTO dto=new BillResponseDTO();
		dto.setBillId(bill.getId());
		dto.setAppointmentId(bill.getAppointment().getId());
		dto.setPatientName(bill.getAppointment().getPatient().getName());
		dto.setDoctorName(bill.getAppointment().getDoctor().getName());
		dto.setAmount(bill.getAmount());
		dto.setStatus(bill.getStatus());
		dto.setDateTime(bill.getCreatedAt());
		return dto;
	}
}
