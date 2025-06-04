package com.telecom.retailreward.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.telecom.retailreward.model.CustomerRewards;
import com.telecom.retailreward.model.CustomerTransaction;
import com.telecom.retailreward.service.CustomerRewardService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customerRewards")
public class CustomerRewardController {

	@Autowired
	CustomerRewardService customerRewardService;

	@GetMapping("/getCustomerRewardPoints")
    @Operation(summary = "Get customer reward points summary")
	public ResponseEntity<List<CustomerRewards>> getCustomerRewardPointsSummary(@RequestParam String startDate,
			@RequestParam String endDate) {

		List<CustomerRewards> customerRewardList = customerRewardService.getCustFinalRewardPointList(startDate,
				endDate);
		return ResponseEntity.ok(customerRewardList);
	}

	@PostMapping("/addCustomerTransactions")
    @Operation(summary = "Add a customer transaction")
	public ResponseEntity<String> addTransaction(@Valid @RequestBody CustomerTransaction transaction,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Invalid input data");
		}
		customerRewardService.addTransaction(transaction);
		return ResponseEntity.ok("Transaction added successfully");
	}
}