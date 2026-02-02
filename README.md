# Expense Tracker API

A robust Spring Boot backend application for managing personal and group expenses, tracking settlements, and organizing financial records.

## 🚀 Features

- **User Management**: Secure authentication using Spring Security and JWT.
- **Expense Tracking**: Create, list, search, and summarize expenses.
- **Group Expenses**: Support for group-based expenses with automatic per-head calculation.
- **Expense Settlements**: Track payments and settlements between group members.
- **Categories**: Organize expenses into customizable categories.
- **Search & Filter**: Powerful search criteria for filtering expenses by user, category, and date range.
- **Email Notifications**: Integrated mail support (Spring Boot Starter Mail).
- **API Documentation**: Interactive Swagger UI for API exploration.

## 🛠 Tech Stack

- **Java**: 21
- **Framework**: Spring Boot 3.4.6
- **Database**: PostgreSQL
- **Security**: Spring Security & JWT
- **ORM**: Spring Data JPA (Hibernate)
- **Documentation**: SpringDoc OpenAPI (Swagger UI)
- **Containerization**: Docker & Docker Compose

## 📋 Prerequisites

- JDK 21 or higher
- Maven 3.x
- Docker and Docker Compose (optional but recommended for Database)

## 🚦 Getting Started

### 1. Database Setup (Docker)

The easiest way to start the database is using Docker Compose:

```bash
docker-compose up -d postgres
```

This will start a PostgreSQL 15 instance with the following credentials (defined in `docker-compose.yml`):
- **Database**: `split_house`
- **User**: `test`
- **Password**: `123`
- **Port**: `5432`

### 2. Configure the Application

Update `src/main/resources/application.properties` if your database settings differ:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/split_house
spring.datasource.username=test
spring.datasource.password=123
```

### 3. Run the Application

You can run the application using Maven:

```bash
./mvnw spring-boot:run
```

The server will start on port `5555`.

## 📖 API Documentation

Once the application is running, you can access the Swagger UI at:
[http://localhost:5555/swagger-ui.html](http://localhost:5555/swagger-ui.html)

## 🐳 Docker Deployment

To run the entire stack (API + Database) in Docker:

1. Build the application:
   ```bash
   ./mvnw clean package
   ```
2. Build and start the containers:
   ```bash
   docker-compose up --build
   ```

## 📂 Project Structure

## 📂 Project Structure

- `com.expense.tracker.Controllers`: REST endpoints.
- `com.expense.tracker.services`: Business logic interfaces and implementations.
- `com.expense.tracker.models`: JPA entities (ORM).
- `com.expense.tracker.repositories`: Data access layer.
- `com.expense.tracker.dtos`: Data Transfer Objects.
- `com.expense.tracker.security`: Security configuration and JWT handling.
- `com.expense.tracker.utilities`: Helper classes and mappers.

## 🗄️ Database Initialization

If you prefer to initialize the database manually without Docker's auto-generation:
- Data scripts and dumps can be found in the `DB files/` directory.
- `dbScript local.sql` contains the schema and initial data for local development.

## 📐 System Design

A visual representation of the system architecture can be found in:
- `System Design/SplitHouse.drawio.png`

---
*Created with ❤️ for better financial management.*
