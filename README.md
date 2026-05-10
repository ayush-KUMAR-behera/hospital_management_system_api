# 🏥 Hospital Management System

A production-grade REST API built with Spring Boot for managing hospital operations — patients, doctors, appointments and billing.

## ✅ Features

✅ Key Features

- JWT Authentication + Role-Based Authorization (ADMIN / DOCTOR)
- Patient, Doctor, Appointment, Billing REST APIs (22 endpoints)
- Soft Delete · Pagination & Sorting · Bean Validation
- Async Email Notification on appointment booking (@Async)
- Global Exception Handling with consistent error responses
- Spring Actuator for health and metrics monitoring
- Unit tested with JUnit 5 + Mockito (6 test cases)
- Swagger UI with JWT bearer auth support

## 🛠 Tech Stack

- Java 21
- Spring Boot 3.4.4
- Spring Security
- Spring Data JPA
- MySQL
- Lombok
- JWT (jjwt 0.11.5)
- Springdoc OpenAPI (Swagger)
- Junit5
- Mockito
- JavaMail
- Spring Actuator

## 🏗 Architecture & Design

- Layered Architecture (Controller → Service → Repository)

- DTO Pattern for request/response handling

- Service–Repository design pattern

- Centralized Global Exception Handling

- SOLID design principles

- JWT-based Authentication & Authorization

- Async Email Notification using JavaMail + @Async

- Unit Testing with JUnit 5 & Mockito

## 🔐 Access Control

### ADMIN
- Full CRUD access
- Manage doctors, patients, appointments, billing
- Access monitoring endpoints
- Book appointments

### DOCTOR
- View patients, appointments, billing
- Book appointments
- No create/update/delete permissions
- No Actuator access




## 🚀 How to Run

# 1. Clone
git clone https://github.com/ayush-KUMAR-behera/hospital_management_system_api.git

# 2. Create DB
CREATE DATABASE hospital_db;

# 3. Configure
cp src/main/resources/application-example.properties src/main/resources/application.properties
# fill in MySQL credentials, JWT secret, Gmail app password

# 4. Run
mvn spring-boot:run

# 5. Open Swagger
http://localhost:8080/swagger-ui/index.html



## 📊 Actuator

/actuator/health   → DB and app status
/actuator/metrics  → JVM memory, HikariCP connections

## 👨‍💻 Developer
Ayush Kumar Behera ---
ayushkumarbehera0237@gmail.com 