package com.telecom.retailreward;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.telecom.retailreward.model.CustRewards;
import com.telecom.retailreward.model.CustTransaction;
import com.telecom.retailreward.service.CustRewardService;

@SpringBootTest
public class RetailRewardServiceTest {

	@InjectMocks
	CustRewardService custRewardService;

	@BeforeEach
	public void setUp() {

		// mock test transactions
		custRewardService.addTransaction(new CustTransaction("P001", 120.0, LocalDate.of(2025, 2, 10))); // 90 pts
		custRewardService.addTransaction(new CustTransaction("P001", 75.0, LocalDate.of(2025, 3, 5))); // 25 pts
		custRewardService.addTransaction(new CustTransaction("P002", 200.0, LocalDate.of(2025, 3, 15))); // 250 pts
		custRewardService.addTransaction(new CustTransaction("P001", 45.0, LocalDate.of(2025, 4, 10))); // 0 pts
	}

	@Test
	public void testCalculatePointsOver50() {
		int points = custRewardService.calculatePoints(55.0);
		assertEquals(5, points); // 1 point for less than 50
	}

	@Test
	public void testCalculatePointsOver100() {
		int points = custRewardService.calculatePoints(120.0);
		assertEquals(90, points); // 2×20 + 1×50
	}

	@Test
	public void testCalculatePointsBelow50() {
		int points = custRewardService.calculatePoints(45.0);
		assertEquals(0, points);
	}

	@Test
	public void testCustRewardPointsWithinTime_success() {
		List<CustRewards> summaries = custRewardService.getCustFinalRewardPointList("2025-02-01", "2025-04-31");

		assertEquals(2, summaries.size());

		CustRewards p001RewardSummary = summaries.stream().filter(s -> s.getCustId().equals("P001")).findFirst()
				.orElse(null);

		assertNotNull(p001RewardSummary);
		assertEquals(115, p001RewardSummary.getTotalPoints());

		Map<String, Integer> monthly = p001RewardSummary.getMonthlyPoints();
		assertEquals(90, monthly.get("FEBRUARY"));
		assertEquals(25, monthly.get("MARCH"));
		assertFalse(monthly.containsKey("MAY")); // 45.0 transaction will not qualify as per mocked test data
	}

	@Test
	void testAsyncRewardPointsCalculation() throws Exception {
		CompletableFuture<List<CustRewards>> future = CompletableFuture.supplyAsync(() -> {
			return custRewardService.getCustFinalRewardPointList("2025-01-01", "2025-03-31");
		});

		// Wait for result
		List<CustRewards> result = future.get();

		assertNotNull(result);
		assertFalse(result.isEmpty());
	}

	@Test
	public void testInvalidDateOrder_ThrowsException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			custRewardService.getCustFinalRewardPointList("2025-05-01", "2025-04-31");
		});

		assertEquals("Start date must be before end date.", exception.getMessage());
	}
}
