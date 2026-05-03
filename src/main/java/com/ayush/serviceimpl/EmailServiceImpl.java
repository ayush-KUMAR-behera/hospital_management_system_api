package com.ayush.serviceimpl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ayush.service.EmailService;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements  EmailService{

	private  final JavaMailSender mailSender;
	private static final  Logger logger=LoggerFactory.getLogger(EmailServiceImpl.class);
	
	@Async
	@Override
	public void sendAppointmentConfirmation(String toEmail, String patientName, String doctorName,
			LocalDateTime appointmentTime) {
	
		SimpleMailMessage message=new SimpleMailMessage();
		
		String body="Dear "+patientName+",\n\n"+
					"Your appointment with "+doctorName+
					" is confirmed for "+appointmentTime +".\n\n"+
					"Thank you for choosing our hospital.";
		message.setTo(toEmail);
		message.setSubject("Appointment Confirmation");
		message.setText(body);
		
		try {
			mailSender.send(message);
			logger.info(" Email sent successfully to: {}",toEmail);
		}catch (Exception e) {
			logger.error("Failed to send email to {}: {}",toEmail+e.getMessage());
		}
	}

}
