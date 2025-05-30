package com.telecom.retailreward;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import com.telecom.retailreward.model.CustRewards;
import com.telecom.retailreward.rest.RetailRewardController;
import com.telecom.retailreward.service.CustRewardService;

@SpringBootTest
class RetailrewardControllerTest {

	@Mock
	CustRewardService custRewardService;

	@InjectMocks
	RetailRewardController retailRewardController;

	@Test
	void testgetCustomerRewardPointsSummary_Success() {

		ResponseEntity<List<CustRewards>> response = retailRewardController.getCustomerRewardPointsSummary("2020-05-25",
				"2020-05-10");
		assertNotNull(response);
	}

}
