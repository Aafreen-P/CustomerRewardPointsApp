package com.telecom.retailreward.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.telecom.retailreward.model.CustomerRewards;
import com.telecom.retailreward.model.CustomerTransaction;
import com.telecom.retailreward.model.TransactionDetail;

@Service
public class CustomerRewardService {

	private final List<CustomerTransaction> customerTansactions = new ArrayList<>();

	public List<CustomerRewards> getCustFinalRewardPointList(String startDate, String endDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDt;
		LocalDate endDt;
		startDt = LocalDate.parse(startDate, formatter);
		endDt = LocalDate.parse(endDate, formatter);

		if (startDt.isAfter(endDt)) {
			throw new IllegalArgumentException("Start date must be before end date.");
		}

		// Map to hold customerId -> (Month -> Points)

		Map<String, Map<String, Integer>> monthlyRewardsPointsMap = new HashMap<>();
		Map<String, List<TransactionDetail>> transactionDetailsMap = new HashMap<>();

		for (CustomerTransaction tx : customerTansactions) {

			// Skip transactions outside the requested date range
			if (tx.getDate().isBefore(startDt) || tx.getDate().isAfter(endDt)) {
				continue;
			}

			String customerId = tx.getCustomerId();
			String month = tx.getDate().getMonth().toString();
			int points = calculatePoints(tx.getAmount());

			// Update monthly points for this customer
			monthlyRewardsPointsMap.computeIfAbsent(customerId, k -> new HashMap<>()).merge(month, points,
					Integer::sum);

			transactionDetailsMap.computeIfAbsent(customerId, k -> new ArrayList<>())
					.add(new TransactionDetail(tx.getAmount(), tx.getDate()));

		}

		// Convert the map into a list of CustRewar objects with total points

		return monthlyRewardsPointsMap.entrySet().stream().map(entry -> {
			String customerId = entry.getKey();
			Map<String, Integer> monthlyPoints = entry.getValue();
			int totalPoints = monthlyPoints.values().stream().mapToInt(Integer::intValue).sum();
			List<TransactionDetail> txDetails = transactionDetailsMap.getOrDefault(customerId, List.of());
			return new CustomerRewards(customerId, monthlyPoints, totalPoints, txDetails);
		}).collect(Collectors.toList());

	}

	/**
	 * Helper method to calculate reward points from transaction amount.
	 */
	public int calculatePoints(double amt) {
		if (amt > 100)
			return (int) Math.round(2 * (amt - 100) + 50);
		else if (amt > 50)
			return (int) Math.round(amt - 50);
		else
			return 0;
	}

	/**
	 * Adds a transaction to the in-memory transaction list.
	 * 
	 * @param tx the transaction to add
	 */
	public void addTransaction(CustomerTransaction transaction) {
		customerTansactions.add(transaction);
	}

}
