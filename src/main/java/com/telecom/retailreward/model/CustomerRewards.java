package com.telecom.retailreward.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRewards {

	private String customerId;
	private Map<String, Integer> monthlyPoints;
	private int totalPoints;
    private List<TransactionDetail> transactions; // NEW FIELD


}