# 🧪 Customer Rewards API – Test Case Documentation

This document outlines a test scenario for verifying reward point calculations for different customers using the Customer Rewards API.

---

## 🚀 Sample cURL Commands (POST Transactions)

    ### 👤 Customer: `p1001`
	**Transactions:**
	- **JAN**: $130 
	- **FEB**: $60
	- **MAR**: $25

```bash
curl -X POST 'http://localhost:8080/api/customerRewards/addCustomerTransactions' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
    "custId": "p1001",
    "amt": 130,
    "date": "2025-01-30"
}'
curl -X POST 'http://localhost:8080/api/customerRewards/addCustomerTransactions' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
    "custId": "p1001",
    "amt": 60,
    "date": "2025-02-28"
}'
curl -X POST 'http://localhost:8080/api/customerRewards/addCustomerTransactions' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
    "custId": "p1001",
    "amt": 25,
    "date": "2025-03-28"
}'

  ### 👤 Customer: `p1002`
**Transactions:**
- **JAN**: $75  
- **FEB**: $150 

**CURL Commands:**
```bash

curl -X POST 'http://localhost:8080/api/customerRewards/addCustomerTransactions' \
  -H 'accept: */*' -H 'Content-Type: application/json' \
  -d '{"custId": "p1002", "amt": 75, "date": "2025-01-28"}'

curl -X POST 'http://localhost:8080/api/customerRewards/addCustomerTransactions' \
  -H 'accept: */*' -H 'Content-Type: application/json' \
  -d '{"custId": "p1002", "amt": 150, "date": "2025-02-28"}'

  ### 👤 Customer: `p1003`
**Transactions:**
- **MAR**: $10

curl -X POST 'http://localhost:8080/api/customerRewards/addCustomerTransactions' \
  -H 'accept: */*' -H 'Content-Type: application/json' \
  -d '{"custId": "p1003", "amt": 10, "date": "2025-03-28"}'

📊 GET API to Fetch Rewards Summary

```json
GET http://localhost:8080/api/customerRewards/getAllCustomerRewards

## 📦 Sample JSON Response

```json
[
  {
    "custId": "p1002",
    "monthlyPoints": {
      "JANUARY": 25,
      "FEBRUARY": 150
    },
    "totalPoints": 175
  },
  {
    "custId": "p1001",
    "monthlyPoints": {
      "JANUARY": 110,
      "FEBRUARY": 10,
      "MARCH": 0
    },
    "totalPoints": 120
  },
  {
    "custId": "p1003",
    "monthlyPoints": {
      "MARCH": 0
    },
    "totalPoints": 0
  }
]


## ✅ Rewards points Summary Table

| Customer | JANUARY | FEBRUARY | MARCH | Total Points |
|----------|---------|----------|-------|---------------|
| p1001    | 110     | 10       | 0     | 120           |
| p1002    | 25      | 150      | -     | 175           |
| p1003    |

 

