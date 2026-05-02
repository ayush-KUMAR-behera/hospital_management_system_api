package com.ayush.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ayush.enums.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillResponseDTO {
	private Long billId;
	private Long appointmentId;
	private String patientName;
	private String doctorName;
	private BigDecimal amount;
	private PaymentStatus status;
	private LocalDateTime dateTime;
}
