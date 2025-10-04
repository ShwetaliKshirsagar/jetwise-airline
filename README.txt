#Jetwise Airline - Microservices Project

Jetwise Airline is a **Spring Boot microservices-based project** designed for learning and interview preparation.  
It demonstrates core concepts like **role-based access, service segregation, database integration, validation, exception handling, logging, and JWT authentication**.

---

## Services Overview

Each microservice runs independently with its own database and port.

| Service Name        | Port | Base URL                          | Description                              |
|---------------------|------|-----------------------------------|------------------------------------------|
| **user-service**    | 3633 | http://localhost:3633/api/users    | Handles user registration, login, roles, authentication |
| **flight-service**  | 3634 | http://localhost:3634/api/flights  | Manage flights (search, list, create, update) |
| **booking-service** | 3635 | http://localhost:3635/api/bookings | Booking management with concurrency handling |
| **payment-service** | 3636 | http://localhost:3636/api/payments | Payment processing & ticket generation |

---

## Tech Stack

- **Backend**: Java 17, Spring Boot 3.5.6  
- **Database**: MySQL  
- **Security**: Spring Security with JWT (to be integrated)  
- **Tools**: Git, Postman, JUnit5 for testing  
- **Build Tool**: Maven  

---

##  API Reference

###  User Service
- `POST /api/users/register` → Register a new user  
- `POST /api/users/login` → User login (JWT to be implemented)  

###  Flight Service
- `GET /api/flights/search` → Search flights  
- `POST /api/flights/add` → Add a new flight (Admin only)  
- `PUT /api/flights/update/{id}` → Update flight details  

###  Booking Service
- `POST /api/bookings/create` → Create booking  
- `GET /api/bookings/{id}` → Get booking details  

###  Payment Service
- `POST /api/payments/process` → Process payment  
- `GET /api/payments/status/{id}` → Payment status  

---

##  Features Implemented
- ✅ Role-based access (USER, ADMIN)  
- ✅ Validation & Custom Exceptions  
- ✅ Centralized Exception Handling with `@ControllerAdvice`  
- ✅ Database Integration with JPA & MySQL  
- ✅ DTO-based request/response handling  
- ✅ Logging (to be added)  
- 🔜 JWT Authentication for secure communication between services  

---

