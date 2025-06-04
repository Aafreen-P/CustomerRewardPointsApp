package com.telecom.retailreward.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionDetail {

	private Double amount;
	private LocalDate date;

}
