package com.telecom.retailreward.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {

		return ResponseEntity.badRequest().body(new ErrorResponse(400, ex.getMessage(), LocalDateTime.now()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());

		return ResponseEntity.badRequest().body(new ErrorResponse(400, "Error : " + errors, LocalDateTime.now()));

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGenericException(HttpServletRequest request, Exception ex) throws Exception {
	    String uri = request.getRequestURI();

	    // ✅ Don't handle Swagger or OpenAPI routes
	    if (uri.startsWith("/v3/api-docs") || uri.startsWith("/swagger-ui") || uri.startsWith("/swagger-resources")) {
	    	// Rethrow exception to let springdoc handle it
	        throw ex;	    }

	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(new ErrorResponse(500, "Unexpected error: " + ex.getMessage(), LocalDateTime.now()));
	}


}
