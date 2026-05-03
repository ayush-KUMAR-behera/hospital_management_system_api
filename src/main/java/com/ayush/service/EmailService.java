package com.ayush.service;

import java.time.LocalDateTime;

public interface EmailService {

	public void sendAppointmentConfirmation(
		    String toEmail, 
		    String patientName, 
		    String doctorName, 
		    LocalDateTime appointmentTime);
}
