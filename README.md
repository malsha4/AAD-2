# Smart Parking Management System (SPMS)

## Overview
A microservice-based Smart Parking Management System built with Spring Boot, Spring Cloud, and Java 21.

## Architecture
- Service Registry (Eureka Server) - Port 8761
- Config Server - Port 8888
- API Gateway - Port 8080
- User Service - Port 8081
- Vehicle Service - Port 8082
- Parking Space Service - Port 8083
- Payment Service - Port 8084

## Technologies
- Java 21
- Spring Boot 3.3.5
- Spring Cloud 2023.0.4
- Spring Cloud Netflix Eureka
- Spring Cloud Config
- Spring Cloud Gateway
- H2 In-Memory Database
- Maven

## How to Run
1. Start Eureka Server: `cd eureka-server && ../mvnw spring-boot:run`
2. Start Config Server: `cd config-server && ../mvnw spring-boot:run`
3. Start API Gateway: `cd api-gateway && ../mvnw spring-boot:run`
4. Start User Service: `cd user-service && ../mvnw spring-boot:run`
5. Start Vehicle Service: `cd vehicle-service && ../mvnw spring-boot:run`
6. Start Parking Space Service: `cd parking-space-service && ../mvnw spring-boot:run`
7. Start Payment Service: `cd payment-service && ../mvnw spring-boot:run`

## API Endpoints

| Service | HTTP Method | Endpoint | Description |
|---|---|---|---|
| **User Service** | POST | `/api/users/register` | Register a new user |
| | POST | `/api/users/login` | Login user |
| | GET | `/api/users/{id}` | Get user by ID |
| | PUT | `/api/users/{id}` | Update user details |
| | GET | `/api/users` | Get all users |
| | DELETE | `/api/users/{id}` | Delete user |
| **Vehicle Service** | POST | `/api/vehicles` | Register a vehicle |
| | GET | `/api/vehicles/{id}` | Get vehicle by ID |
| | GET | `/api/vehicles` | Get all vehicles |
| | GET | `/api/vehicles/user/{userId}` | Get vehicles for a user |
| | PUT | `/api/vehicles/{id}` | Update vehicle |
| | POST | `/api/vehicles/{id}/entry` | Simulate vehicle entry |
| | POST | `/api/vehicles/{id}/exit` | Simulate vehicle exit |
| | DELETE | `/api/vehicles/{id}` | Delete vehicle |
| **Parking Service**| POST | `/api/parking` | Add a parking space |
| | GET | `/api/parking` | Get all parking spaces |
| | GET | `/api/parking/{id}` | Get parking space by ID |
| | PUT | `/api/parking/{id}` | Update parking space |
| | DELETE | `/api/parking/{id}` | Delete parking space |
| | PUT | `/api/parking/{id}/reserve` | Reserve a parking space |
| | PUT | `/api/parking/{id}/release` | Release a parking space |
| | GET | `/api/parking/available` | Get available spaces |
| | GET | `/api/parking/city/{city}` | Filter spaces by city |
| | GET | `/api/parking/zone/{zone}` | Filter spaces by zone |
| | GET | `/api/parking/owner/{ownerId}`| Filter spaces by owner |
| | PUT | `/api/parking/{id}/status` | Update IoT status |
| **Payment Service**| POST | `/api/payments` | Process a payment |
| | GET | `/api/payments/{id}` | Get payment by ID |
| | GET | `/api/payments` | Get all payments |
| | GET | `/api/payments/user/{userId}` | Get payments by user |
| | GET | `/api/payments/{id}/receipt` | Get payment receipt |
| | PUT | `/api/payments/{id}/refund` | Process payment refund |

## Resources
- [Postman Collection](./postman_collection.json)
- ![Eureka Dashboard](https://github.com/malsha4/AAD-2/blob/main/docs/screenshots/Screenshot%20(147).png)
