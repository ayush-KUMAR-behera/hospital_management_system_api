# 🏥 Hospital Management System

A secure REST API built with Spring Boot for managing hospital operations.

## ✅ Features

- ✔ JWT Authentication
- ✔ Role-based Authorization (ADMIN / DOCTOR)
- ✔ BCrypt Password Encryption
- ✔ REST APIs (Patient Management)
- ✔ Swagger UI Documentation

## 🛠 Tech Stack

- Java 21
- Spring Boot 3.4.4
- Spring Security
- Spring Data JPA
- MySQL
- Lombok
- JWT (jjwt 0.11.5)
- Springdoc OpenAPI (Swagger)

## 🚀 How to Run

1. Clone the repo
2. Configure `application.properties` with your MySQL credentials
3. Run `mvn clean install`
4. Start the app and visit `http://localhost:8080/swagger-ui/index.html`