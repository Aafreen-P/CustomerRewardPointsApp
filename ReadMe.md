# 🛍️ Customer Rewards Points Summary Calculator Application

A Spring Boot RESTful service to calculate reward points for customers based on their transactions.

---

## 📌 Features

- Add transactions for customers via POST API.
- Calculate reward points per customer based on:
  - **2 points** for every dollar spent over $100.
  - **1 point** for every dollar spent between $50 and $100.
- Filter and calculate rewards over a dynamic date range.
- Monthly and total reward summaries.
- REST API with Swagger documentation.
- JUnit 5 test coverage for service logic.

---

## ▶️ Getting Started

### 🧰 Prerequisites

- Java 8+
- Maven
- IDE (Spring Tool Suite, IntelliJ, VSCode, etc.)

---

### 🚀 Running the Application

```bash
mvn spring-boot:run  OR
Right Click in STS and run AS SpringBootApplication
```
Once started, the application will be accessible at:

```
http://localhost:8080/swagger-ui/index.html
```

Make sure you have the required springdoc-openapi-ui dependency in your pom.xml.
```
## 🧪 Testing

- JUnit 5 tests implemented for the reward calculation service.
- Includes simulation of asynchronous API service behavior.
- Test cases cover:
  - Valid transactions
  - Edge cases
  - Empty results
  
> **Note:**  
> The detailed test document is provided under the `src/test/resources` folder.
File Name : CustomerRewardPoints_TestPlan


## 👨‍💻 Author

Created by Aafreen Sirajuddin Patel

