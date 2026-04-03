package com.ayush.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
	private String message;
	private int status;
	private LocalDateTime timestamp;
}
