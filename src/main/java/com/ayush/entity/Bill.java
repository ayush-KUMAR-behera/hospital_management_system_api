package com.ayush.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ayush.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bills")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal amount;
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;
	private LocalDateTime createdAt;
	
	@OneToOne
	@JoinColumn(name = "appointment_id")
	private Appointment appointment;
	
	@PrePersist
	public void onCreate() {
		this.createdAt=LocalDateTime.now();
		this.status=PaymentStatus.PENDING;
	}
	
}
