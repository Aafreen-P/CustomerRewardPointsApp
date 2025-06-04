package com.telecom.retailreward.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTransaction {

	@NotBlank(message = "Customer ID cannot be blank")
	private String customerId;

	@NotNull(message = "Amount cannot be null")
	private Double amount;

	@NotNull(message = "Date cannot be null")
	private LocalDate date;

}
