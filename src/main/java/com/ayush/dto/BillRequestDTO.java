package com.ayush.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillRequestDTO {
	@NotNull(message = "need appointment Id")
	private Long appointmentId;
	@Min(value = 1)
	private BigDecimal amount;
}
