package com.bank.customer.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bank.common.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandiling {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> resourceNotFoundException(ResourceNotFoundException rx) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ApiResponse<>(404, "Resourse not found", rx.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> validationException(MethodArgumentNotValidException mx) {

		Map<String, String> errors = new HashMap<>();

		mx.getBindingResult().getFieldErrors().forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ApiResponse<>(400, "Validation Exception", errors));
	}
}
