# Retail Reward Points Calculator API

A Spring Boot RESTful API for managing customer transactions and calculating reward points based on purchases.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Running Tests](#running-tests)
- [Swagger Documentation](#swagger-documentation)
- [Contact](#contact)

---

## Overview

This application allows customers to record transactions and retrieve reward points summaries within a specified date range. Rewards are calculated based on transaction amounts.

---

## Features

- Add customer transactions with validation
- Retrieve customer reward points summary between two dates
- Input validation with meaningful error responses
- API documentation with Swagger/OpenAPI

---

## Technology Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Validation (Jakarta Validation)
- Lombok
- JUnit 5 & Mockito for testing
- Swagger (Springdoc OpenAPI)
- Maven for build and dependency management

---

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Build and Run

```bash
# Clone the repository
git clone https://github.com/Aafreen-P/CustomerRewardPointsApp
cd CustomerRewardPointsApp

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```
The API will start at:
```
http://localhost:8080
```

---

### API Endpoints

▶️ Get Customer Reward Points Summary
Endpoint: GET /api/customerRewards/getCustomerRewardPoints

Description: Returns the reward points summary for all customers within the specified date range.

Query Parameters:

startDate – Start date in YYYY-MM-DD format (required)

endDate – End date in YYYY-MM-DD format (required)

##### Request - Get Reward Points
```json
http://localhost:8080/api/customerRewards/getCustomerRewardPoints?startDate=2025-01-04&endDate=2025-05-31
```
##### Response - Get Reward Points

```json
[
  {
    "customerId": "Cust1",
    "monthlyPoints": {
      "MAY": 10,
      "APRIL": 370
    },
    "totalPoints": 380,
    "transactions": [
      {
        "amount": 250,
        "date": "2025-04-04"
      },
      {
        "amount": 70,
        "date": "2025-04-04"
      },
      {
        "amount": 60,
        "date": "2025-05-04"
      },
      {
        "amount": 40,
        "date": "2025-05-07"
      }
    ]
  }
]
```

▶️ POST Customer Reward Points Summary

Description: Add a customer transaction.

##### Request - Add Transaction

http://localhost:8080/api/customerRewards/addCustomerTransactions


```json
{
  "customerId": "cust1",
  "amount": 250.0,
  "date": "2025-04-04"
}
```

Response : Status :200

Transaction added successfully

---

## Running Tests

To run unit tests:

```bash
mvn test
```

Tests cover controller endpoints using JUnit 5 and Mockito.

---

## Swagger Documentation

API documentation is available at:

```
http://localhost:8080/swagger-ui.html
```

or

```
http://localhost:8080/swagger-ui/index.html
```

depending on your Springdoc OpenAPI configuration.

---

## Contact

For questions or support, please contact:

- **Aafreen Patel**
- GitHub: [Aafreen-P](https://github.com/Aafreen-P/CustomerRewardPointsApp)

---