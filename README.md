# EventHub Automation Framework 🚀

This repository contains an automated end-to-end (E2E) testing framework built for the **EventHub** web application. The framework leverages **Selenium WebDriver**, **Java**, and **JUnit 5**, with advanced reporting powered by **Allure Report**.
* **Test Case Reference**: The framework is designed around a strict manual test plan. You can review the full reference test suite and expected matrix directly in this [Google Sheets Document](https://docs.google.com/spreadsheets/d/1zP0B_4AUwyGOZBXpMH4RkPb3HM0VvHTxGpbknR3pGGk/edit?usp=sharing).
  * *Note on Test Management*: This spreadsheet features a dedicated **Reference Repository** tab mapping out all EventHub application features. While still a Work in Progress, this matrix serves as our **Functional Coverage KPI** to track automated test density against manual requirements. It allows us to perform *Risk-Based Testing* by highlighting critical functionalities and setting clear **automation priorities**. Additional End-to-End (E2E) scenarios will be incrementally scripted based on this roadmap.
  * *Tooling Context*: A spreadsheet approach was intentionally selected as a lightweight, cost-effective alternative to Jira Xray (whose free trial limitations restrict long-term sandbox tracking).

### 🌐 Target Application URL
The automated test suite executes directly against the official EventHub platform:
* **Live Application**: [EventHub Portal](https://eventhub.rahulshettyacademy.com/login)
---

## 🚧 Project Status: Work in Progress (WIP)

> **Note**: This project is a personal sandbox designed to experiment with and implement advanced test automation architectures. It is a continuous **Work in Progress (WIP)** that I regularly update and optimize in my free time. As an evolving project, certain areas of the code and architecture are still undergoing active iterations and refactoring; I continuously refine and improve these components based on my availability and learning journey.
> **Note**: As you review the code, please note that the full execution path for this specific test case (including the cancellation workflow) is currently incomplete and still under development.
### 🔧 Upcoming Roadmap & Enhancements:
* [ ] Integrate with a CI/CD pipeline (GitLab CI / Jenkins) using headless browser execution.

---

## 🏗️ Core Architecture & Features

This framework is built with scalability, clean code principles, and robustness in mind:

* **Page Object Model (POM)**: Complete isolation between the test scripts and the UI elements to ensure easy maintenance.
* **Data-Driven Testing**: No hardcoded test data inside the test classes. All environment URLs and test-specific inputs are externalized into `.properties` files (e.g., `config.properties`, `T001-E2E.properties`).
* **Functional Programming (Java 8+ Suppliers)**: Custom assertions (`verifyEquals`, `verifyTrue`) encapsulate lazy-evaluated Selenium expressions. This ensures that any element-lookup failures or timeouts are caught safely inside the evaluation loop and each assertion using theses methods takes screenshot.
* **Smart Failure Capture**: The framework automatically monitors test status. If a step crashes due to a `TimeoutException` or `NoSuchElementException`, a global screenshot is instantly captured and attached to the report before the browser safely quits.

---

## 🛠️ Tech Stack & Dependencies

* **Language**: Java 17
* **Automation**: Selenium WebDriver (4.21.0)
* **Test Runner**: JUnit 5
* **Reporting**: Allure Report
* **Build Tool**: Maven

---

## 🚀 Getting Started

### Prerequisites
Make sure you have the following installed locally:
* **Java Development Kit (JDK 17 or higher)**
* **Apache Maven**
* **Google Chrome** (The framework automatically manages the driver executable via Selenium 4)

### Running the Tests
To execute the test suite, navigate to the project root folder in your terminal and run:

```bash
mvn clean test
