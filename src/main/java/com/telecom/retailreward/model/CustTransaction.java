package com.telecom.retailreward.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CustTransaction {

	@NotBlank(message = "Customer ID cannot be blank")
	private String custId;

	@NotNull(message = "Amount cannot be null")
	private Double amt;

	@NotNull(message = "Date cannot be null")
	private LocalDate date;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public CustTransaction(@NotBlank(message = "Customer ID cannot be blank") String custId,
			@NotNull(message = "Amount cannot be null") Double amt,
			@NotNull(message = "Date cannot be null") LocalDate date) {
		super();
		this.custId = custId;
		this.amt = amt;
		this.date = date;
	}

}
