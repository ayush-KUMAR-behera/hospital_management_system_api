package com.ayush.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentRequestDTO {

	@NotNull(message = "Patient Id is Required")
	private Long patientId;
	@NotNull(message = " Doctor Id is Required")
	private Long doctorId;
	@Future(message = "Appointment Date is Required")
	private LocalDateTime appointmentTime;
	
}
