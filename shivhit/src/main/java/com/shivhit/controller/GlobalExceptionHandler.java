package com.shivhit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.shivhit.exception.DataNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<String> handleDataNotFoundException(DataNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
