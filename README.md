# PayPal Integration with Spring Boot

This project demonstrates the integration of PayPal with a Spring Boot application. The application uses PayPal's REST API for processing payments, with a simple structure and no database interaction.

---

## Features

- PayPal configuration using `PaypalConfig.java`
- Payment initiation and execution using `PaypalService.java`
- REST endpoints to manage payment workflows in `PaypalController.java`
- Lightweight and database-free implementation

---

## Prerequisites

Before running the project, ensure you have the following:

- Java 17 or above
- Maven or Gradle
- PayPal Developer Account
  - Sandbox client ID and secret from [PayPal Developer Dashboard](https://developer.paypal.com/)

---

## Setup Instructions

1. **Clone the Repository**

   ```bash
   git clone https://github.com/mustaphaAitigunaoun/paypal-spring.git
   cd paypal-spring

2. **Configure PayPal Credentials**

   ```bash
   paypal.client.id=your-client-id
   paypal.client.secret=your-client-secret
   paypal.mode=sandbox # Change to 'live' for production
   
3. **Build and Run the Project**

   Using Maven:
   ```bash
   mvn clean install
   mvn spring-boot:run


  

   
