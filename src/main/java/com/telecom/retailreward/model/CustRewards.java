package com.telecom.retailreward.model;

import java.util.Map;

public class CustRewards {

	private String custId;
	private Map<String, Integer> monthlyPoints;
	private int totalPoints;

	public CustRewards(String custId, Map<String, Integer> monthlyPoints, int totalPoints) {
		super();
		this.custId = custId;
		this.monthlyPoints = monthlyPoints;
		this.totalPoints = totalPoints;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Map<String, Integer> getMonthlyPoints() {
		return monthlyPoints;
	}

	public void setMonthlyPoints(Map<String, Integer> monthlyPoints) {
		this.monthlyPoints = monthlyPoints;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

	@Override
	public String toString() {
		return "CustRewards{" + "customerId='" + custId + '\'' + ", monthlyPoints=" + monthlyPoints + ", totalPoints="
				+ totalPoints;

	}

}