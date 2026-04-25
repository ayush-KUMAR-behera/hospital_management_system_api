package com.ayush.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.MethodArgumentNotValidException;
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {
	

    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ErrorResponse handleResourceNotFound(ResourceNotFoundException ex) {
		ErrorResponse er=new ErrorResponse();
		er.setMessage(ex.getMessage());
		er.setStatus(HttpStatus.NOT_FOUND.value());
		er.setTimestamp(LocalDateTime.now());
		return er;
	}
	
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ErrorResponse handleIllegalArgument(IllegalArgumentException ex) {
	    ErrorResponse error = new ErrorResponse();
	    error.setMessage(ex.getMessage());
	    error.setStatus(HttpStatus.BAD_REQUEST.value());
	    error.setTimestamp(LocalDateTime.now());
	    return error;
	}
	
	
	

@ExceptionHandler(MethodArgumentNotValidException.class)
public ErrorResponse handleValidation(MethodArgumentNotValidException ex) {

    ErrorResponse error = new ErrorResponse();

    String msg = ex.getBindingResult()
            .getFieldErrors()
            .get(0)
            .getDefaultMessage();
    error.setMessage(msg);
    error.setStatus(HttpStatus.BAD_REQUEST.value());
    error.setTimestamp(LocalDateTime.now());

    return error;
}

@ExceptionHandler(Exception.class)
public ErrorResponse handleGenericException(Exception ex) {
    logger.error("Unexpected error: {}", ex.getMessage()); // add this line
    ErrorResponse error = new ErrorResponse();
    error.setMessage("Something went wrong");
    error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    error.setTimestamp(LocalDateTime.now());
    return error;
}

}
