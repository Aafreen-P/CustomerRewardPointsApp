package com.telecom.retailreward.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Standard error response format")
public class ErrorResponse {

	@Schema(description = "HTTP status code", example = "400")
	private int status;

	@Schema(description = "Error message", example = "Invalid input")
	private String message;

	@Schema(description = "Timestamp of the error", example = "2025-06-02 16:15:00")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp;

}
