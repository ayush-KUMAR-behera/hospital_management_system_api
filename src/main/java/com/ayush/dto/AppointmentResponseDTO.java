package com.ayush.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentResponseDTO {
	private Long appointmentId;
	private String patientName;
	private String doctorName;
	private String doctorSpeciality;
	private LocalDateTime appointmentTime;
}
