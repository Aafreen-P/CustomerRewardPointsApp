package com.telecom.retailreward;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telecom.retailreward.model.CustomerRewards;
import com.telecom.retailreward.model.CustomerTransaction;
import com.telecom.retailreward.rest.CustomerRewardController;
import com.telecom.retailreward.service.CustomerRewardService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CustomerRewardController.class)
public class CustomerRewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRewardService customerRewardService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetCustomerRewardPointsSummary() throws Exception {
        // Prepare mock response
        List<CustomerRewards> mockRewards = Arrays.asList(
                new CustomerRewards("cust1", null, 100, null),
                new CustomerRewards("cust2", null, 150, null)
        );

        // Match the exact input parameters as in the request
        given(customerRewardService.getCustFinalRewardPointList(
                LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31)))
                .willReturn(mockRewards);

        mockMvc.perform(get("/api/customerRewards/getCustomerRewardPoints")
                        .param("startDate", "2023-01-01")
                        .param("endDate", "2023-12-31"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockRewards)));
    }

    @Test
    void testAddTransaction_ValidInput() throws Exception {
        CustomerTransaction transaction = new CustomerTransaction("Cust1", 250.0, LocalDate.now());

        doNothing().when(customerRewardService).addTransaction(any(CustomerTransaction.class));

        mockMvc.perform(post("/api/customerRewards/addCustomerTransactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isOk())
                .andExpect(content().string("Transaction added successfully"));
    }

    @Test
    void testAddTransaction_InvalidInput() throws Exception {
        // Invalid transaction: missing required fields
        CustomerTransaction invalidTransaction = new CustomerTransaction(); // all fields null/blank

        mockMvc.perform(post("/api/customerRewards/addCustomerTransactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidTransaction)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid input data"));
    }
}
