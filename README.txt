#Jetwise Airline - Microservices Project
Jetwise Airline is a Spring Boot microservices project designed for learning and real-world simulation.
It demonstrates concepts like role-based access, service segregation, database integration, validation, exception handling, JWT authentication, scheduled jobs, asynchronous processing, caching using hazelcast and cloud-native features.
---

## Services Overview

Each microservice runs independently with its own database and port.

| Service Name        | Port    | Base URL                           | Description                              |
|---------------------|---------|------------------------------------|------------------------------------------|
| **user-service**    | 8081    | http://localhost:8081/api/users    | Handles user registration, login, roles, JWT authentication |
| **flight-service**  | 8082    | http://localhost:8082/api/flights  | Manage flights (search, list, create, update) |
| **booking-service** | 8083    | http://localhost:8083/api/bookings | Booking creation, pending status handling, scheduled jobs, Ticket generation after payment success |
| **payment-service** | 8084    | http://localhost:8084/api/payments | Payment processing |
| **jwt-common**      |(Library)|                -                   | Shared JWT utility dependency for all services |

---
🗄️ Database: All services use a single MySQL instance with multiple schemas (user-service, flight-service, booking-service, payment-service

---

## JWT-Common Module Overview

The jwt-common project is a shared library that provides reusable JWT-related components for all microservices.

### Purpose:
To ensure consistent JWT token generation, validation, and claims management across services — reducing code duplication.


### Features:
Common JwtTokenProvider for token creation & validation
Shared JwtAuthenticationFilter
JwtUtils for extracting username/roles
Centralized JWT exception handling
Added as a Maven dependency in all other microservices

---

## Tech Stack

Language: Java 17

Framework: Spring Boot 3.5.6

Database: MySQL (single DB, multiple schemas)

Security: Spring Security with shared JWT-Common module

Service Discovery: Eureka Server

Gateway: Spring Cloud Gateway

Async/Scheduled: Spring Task (@Async, @Scheduled)

Logging: SLF4J + AOP

Mapping: ModelMapper

Boilerplate Reduction: Lombok

Testing: JUnit5

Containerization: Docker, Docker Compose

Build Tool: Maven
---

##  API Reference

###  User Service
- `POST /api/users/register` → Register a new user (Done) 
- `POST /api/users/login` → User login (JWT token generation completed)  

###  Flight Service
- `GET /api/flights/search` → Search flights (Done) 
- `POST /api/flights/add` → Add a new flight (Admin only)(ROLE access pending) 
- `PUT /api/flights/update/{id}` → Update flight details (Admin Only) (ROLE access pending)
- `DELETE /api/flight/{flightNumber} → Delete flight (Admin Only) (ROLE access pending)
- `GET ` /api/flights/getFlight/{flightId} → Get flight details for booking, generate ticket (Done)

###  Booking Service
- `POST /api/booking/create` → Create booking  (Done)
- `GET /api/booking/fetch/{bookingid}` → Get booking details  (Done)
- `PUT /api/booking/update-status/{bookingid}/{status} → Update booking confirmation after payment success.  (Done)
- `POST /api/booking/generateTicket/{bookingId} → Generate ticket after payment confirmation   (Done)

###  Payment Service
- `POST /api/payment/process` → Process payment  (Done)
- `GET /api/payment/status/{id}` → Payment status  (Pending)

---

##  Features Implemented

| Feature                                              | Status      |
| ---------------------------------------------------- | ----------- |
| Role-based access (USER, ADMIN)                      | In Progress |
| Validation & Custom Exceptions                       | Completed   |
| Centralized Exception Handling (`@ControllerAdvice`) | Completed   |
| Database Integration with JPA & MySQL                | Completed   |
| DTO-based request/response mapping using ModelMapper | Completed   |
| Logging with SLF4J + AOP                             | In Progress |
| JWT Authentication for secure communication          | In Progress |
| Scheduled Jobs for pending bookings                  | Completed   |
| Asynchronous PDF/email generation                    | Completed   |
| Dockerized microservices with Docker Compose         | Completed   |
| API Gateway + Eureka Discovery                       | Completed   |
| Unit & Integration Testing with JUnit5               | Partial     |
| Kafka Events                                         | Planned     |
| Swagger API Docs                                     | Planned     |
| Hazelcast for caching                                | Planned     |


##  Architecture & Design

Microservices Architecture: Each service has its own responsibility and schema, can be deployed independently.

API Gateway: Routes incoming requests to respective services.

Service Discovery: Eureka Server enables services to locate each other dynamically.

Asynchronous Tasks: Background tasks for PDF generation and emails.

Scheduled Jobs: Booking service automatically cancels pending bookings after 10 minutes.

Containerization: Each service can be run in Docker for easy deployment and scaling.
