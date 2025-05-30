package com.telecom.retailreward.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.telecom.retailreward.model.CustRewards;
import com.telecom.retailreward.model.CustTransaction;
import com.telecom.retailreward.service.CustRewardService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customerRewards")
public class RetailRewardController {

	@Autowired
	CustRewardService custRewardService;

	@GetMapping("/getCustomerRewardPoints")
	public ResponseEntity<List<CustRewards>> getCustomerRewardPointsSummary(@RequestParam String startDate,
			@RequestParam String endDate) {

		List<CustRewards> customerRewardList = custRewardService.getCustFinalRewardPointList(startDate, endDate);
		return ResponseEntity.ok(customerRewardList);
	}

	@PostMapping("/addCustomerTransactions")
	public ResponseEntity<String> addTransaction(@Valid @RequestBody CustTransaction transaction,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Invalid input data");
		}
		custRewardService.addTransaction(transaction);
		return ResponseEntity.ok("Transaction added successfully");
	}
}