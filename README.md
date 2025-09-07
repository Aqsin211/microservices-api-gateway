# Microservices API Gateway

A robust microservice-based architecture featuring an **API Gateway** built with Spring Cloud Gateway, seamlessly routing requests to dedicated backend services: **Profile Service** and **Feedback Service**. This project demonstrates scalable API routing, centralized entry point, and service isolation, suitable for modern cloud-native applications.

---

## Overview

This repository implements an advanced API Gateway pattern using **Spring Cloud Gateway**. The Gateway acts as the single entry point for client requests, routing them to two core microservices:

- **Profile Service**: Handles CRUD operations for user profiles (name, email, bio).
- **Feedback Service**: Collects and lists user feedback entries.

The API Gateway applies logging filters for observability and uses path-based routing to direct traffic to the appropriate services.

---

## Architecture

```
                +------------------+
                |    API Gateway   |
                | (Spring Cloud)   |
                +--------+---------+
                         |
         +---------------+---------------+
         |                               |
+--------v--------+             +--------v--------+
|   Profile       |             |   Feedback      |
|   Service       |             |   Service       |
| (CRUD: Users)   |             | (Feedback API)  |
+-----------------+             +-----------------+
```

- Requests to `/profiles/**` are routed to the **Profile Service**.
- Requests to `/feedback/**` are routed to the **Feedback Service**.
- The Gateway logs incoming request details (method, path, headers).

---

## Features

### Core Functionality

- **API Gateway with Spring Cloud Gateway**
  - Central entry point for all client requests.
  - Path-based routing to backend services.
- **Profile Service**
  - RESTful APIs for Create, Read, Update, Delete operations on user profiles.
- **Feedback Service**
  - RESTful APIs to submit new feedback and retrieve all feedback entries.
- **Custom Logging Filter**
  - Logs HTTP method, URL path, and headers of each request at the Gateway level.
- **Dockerized Deployment**
  - All services are containerized and orchestrated using Docker Compose.
- **Swagger/OpenAPI Documentation**
  - Interactive API docs available for both Profile and Feedback services.
- **Easy Local Development**
  - Run the entire stack with a single `docker-compose up` command.

---

## Getting Started

### Prerequisites
- Java 21
- Docker & Docker Compose
- Gradle 

### Running with Docker Compose

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Aqsin211/microservices-api-gateway.git
   cd microservices-api-gateway
   ```

2. **Start all services:**
   ```bash
   docker-compose up --build
   ```

   - The API Gateway will be available at `http://localhost:8080`
   - Profile Service and Feedback Service are available through the Gateway.

3. **Access Swagger API Docs:**
   - Profile Service: `http://localhost:8080/swagger-ui/index.html?ms-profile`
   - Feedback Service: `http://localhost:8080/swagger-ui/index.html?ms-feedback`

---

### API Endpoints

#### Profile Service

* `POST /profiles` – Create a profile
* `GET /profiles` – Retrieve all profiles
* `GET /profiles/{id}` – Retrieve a profile by ID
* `PUT /profiles/{id}` – Update a profile
* `DELETE /profiles/{id}` – Delete a profile

#### Feedback Service

* `POST /feedback` – Submit feedback
* `GET /feedback` – Retrieve all feedback
* `GET /feedback/{id}` – Retrieve feedback by ID
* `GET /feedback/profile/{profileId}` – Retrieve feedback for a specific profile
* `PUT /feedback` – Update feedback
* `DELETE /feedback` – Delete feedback
* `DELETE /feedback/profile/{profileId}` – Delete all feedback for a profile

*All endpoints are accessible via the API Gateway.*

---

## Configuration

- **Gateway Routing**: Configured to route `/profiles/**` to Profile Service and `/feedback/**` to Feedback Service.
- **Logging Filter**: Logs each incoming request’s method, path, and headers for traceability.
- **Environment Variables**: See `docker-compose.yml` for configurable ports and service endpoints.

---

## Technical Details

- **Frameworks & Tools:**
  - Spring Boot 3.5.5
  - Spring Cloud Gateway 2025
  - Spring Data JPA with PostgreSQL
  - OpenFeign for inter-service communication
  - MapStruct for DTO mapping
  - Swagger/OpenAPI for API documentation
  - Docker & Docker Compose for containerization

- **Architecture Highlights:**
  - Microservices are independent and communicate via REST APIs.
  - API Gateway centralizes routing and logging.
  - Each service includes **global exception handling** and validation.
  - Docker Compose ensures easy setup of PostgreSQL, Gateway, and backend services.

---

## Project Structure

```
microservices-api-gateway/
├── api-gateway/        # Spring Cloud Gateway source
├── profile-service/    # Profile Service source
├── feedback-service/   # Feedback Service source
├── docker-compose.yml  # Multi-service orchestration
└── README.md
```

---

## Contact

For questions or support, please open an issue or contact [Aqsin211](https://github.com/Aqsin211).
