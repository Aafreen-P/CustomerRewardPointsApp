package com.telecom.retailreward.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.telecom.retailreward.model.CustRewards;
import com.telecom.retailreward.model.CustTransaction;

@Service
public class CustRewardService {

	private final List<CustTransaction> custTansactions = new ArrayList<>();

	public List<CustRewards> getCustFinalRewardPointList(String startDate, String endDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDt;
		LocalDate endDt;
		try {
			startDt = LocalDate.parse(startDate, formatter);
			endDt = LocalDate.parse(endDate, formatter);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd.");
		}

		if (startDt.isAfter(endDt)) {
			throw new IllegalArgumentException("Start date must be before end date.");
		}
		
        // Map to hold customerId -> (Month -> Points)

		Map<String, Map<String, Integer>> monthlyRewardsPointsMap = new HashMap<>();

		for (CustTransaction custTx : custTansactions) {

            // Skip transactions outside the requested date range
			if (custTx.getDate().isBefore(startDt) || custTx.getDate().isAfter(endDt)) {
				continue;
			}

			String customerId = custTx.getCustId();
			String month = custTx.getDate().getMonth().toString();
			int points = calculatePoints(custTx.getAmt());
			
            // Update monthly points for this customer
			monthlyRewardsPointsMap.computeIfAbsent(customerId, k -> new HashMap<>()).merge(month, points,
					Integer::sum);

		}
		
        // Convert the map into a list of CustRewar objects with total points

		return monthlyRewardsPointsMap.entrySet().stream().map(entry -> {
			String customerId = entry.getKey();
			Map<String, Integer> monthlyPoints = entry.getValue();
			int totalPoints = monthlyPoints.values().stream().mapToInt(Integer::intValue).sum();
			return new CustRewards(customerId, monthlyPoints, totalPoints);
		}).collect(Collectors.toList());

	}

	 /**
     * Helper method to calculate reward points from transaction amount.
     */
	public int calculatePoints(double amt) {
		int points = 0;
		if (amt > 100)
			points = (int) (points + (2 * (amt - 100)) + 50);
		else if (amt > 50)
			points = (int) (points + (amt - 50));
		return points;
	}

	/**
     * Adds a transaction to the in-memory transaction list.
     * @param tx the transaction to add
     */
	public void addTransaction(CustTransaction transaction) {
		custTansactions.add(transaction);
	}

}
