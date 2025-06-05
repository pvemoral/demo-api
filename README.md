# 🚀 Demo API

> An elegant RESTful API microservice built with Spring Boot

[![Java](https://img.shields.io/badge/Java-11-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Gradle](https://img.shields.io/badge/Gradle-7.x-blue.svg)](https://gradle.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 📋 Overview

This project demonstrates a RESTful API microservice implementation using Spring Boot. It provides a simple entity management system with CRUD operations and follows modern development practices including testing, documentation, and CI/CD principles.

## 🛠️ Technologies

This application is built using the following technologies:

- 🔄 **Spring Boot** - Java's powerful framework for building production-ready applications
- 📊 **Spring Data JPA** - For storing and retrieving data in a relational database
- 💾 **H2 Database** - Java SQL in-memory database for development and testing
- 🧪 **JUnit** - Testing framework for Java applications
- 📏 **JaCoCo** - Java Code Coverage Library
- 📝 **Swagger/OpenAPI** - API documentation and testing tool
- 🛠️ **Gradle** - Build automation tool
- 📦 **GitHub** - Version control and repository hosting

## 🏗️ Project Structure

The application follows a clear and maintainable structure:

```bash
-- src/main/java/com.api.demo
| -- config        # Configuration files including Swagger setup
| -- controllers   # REST controllers handling HTTP requests
| -- models        # Entity models and DTOs
| -- repositories  # Data access layer
| -- services      # Business logic layer
```

```bash
-- src/test/java/com.api.demo
| -- controllers   # Controller tests
| -- services      # Service layer tests
```

## 🚀 Getting Started

### Prerequisites

- Java 11 or higher
- Git
- Your favorite IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

### Installation

1. Clone the repository:
```bash
git clone https://github.com/pvemoral/demo-api.git
cd demo-api
```

2. Open as a Spring Boot project in your IDE.

3. Build the project:
```bash
./gradlew build
```

4. Run the application:
```bash
./gradlew bootRun
```

## 🧪 Testing

Run tests with:
```bash
./gradlew test
```

### Code Coverage

Generate and view JaCoCo test coverage report:
```bash
./gradlew build jacocoTestReport
```

After running this command, you can find the report at: `build/reports/jacoco/test/html/index.html`

## 📡 API Endpoints

The service exposes the following RESTful endpoints:

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | `/entities` | Retrieve all entities |
| GET    | `/entities/{entityId}` | Retrieve a specific entity |
| POST   | `/entities` | Create a new entity |
| PUT    | `/entities/{entityId}` | Update an existing entity |
| DELETE | `/entities/{entityId}` | Delete an entity |

### Example Requests

#### Create Entity
```json
POST /entities
{
    "entityName": "Example Entity"
}
```

#### Update Entity
```json
PUT /entities/{entityId}
{
    "entityName": "Updated Entity Name"
}
```

## 📚 API Documentation

The API is documented using Swagger UI, which provides an interactive documentation experience.

Access Swagger UI at: `http://localhost:8080/swagger-ui.html` (when running locally)

## 🔒 Security

This demo API doesn't include security features. In a production environment, consider implementing:

- Authentication (OAuth2, JWT)
- HTTPS
- Input validation
- Rate limiting

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

⭐ **Created by [pvemoral](https://github.com/pvemoral)** ⭐


## Client API

The Client API allows for CRUD operations on client entities.

### Client Object

**ClientDTO Structure:**
```json
{
  "name": "string",
  "address": "string",
  "phone": "string",
  "dni": "string"
}
```

**Client Entity Structure (includes ID):**
```json
{
  "id": integer,
  "name": "string",
  "address": "string",
  "phone": "string",
  "dni": "string"
}
```

### Endpoints

#### 1. Get All Clients
- **GET** `/clients`
- **Description:** Retrieves a list of all clients.
- **Success Response (200 OK):**
  ```json
  [
    {
      "name": "John Doe",
      "address": "123 Main St",
      "phone": "555-1234",
      "dni": "12345678A"
    },
    {
      "name": "Jane Smith",
      "address": "456 Oak Ave",
      "phone": "555-5678",
      "dni": "87654321B"
    }
  ]
  ```

#### 2. Get Client by ID
- **GET** `/clients/{id}`
- **Description:** Retrieves a specific client by their ID.
- **URL Parameters:**
  - `id` (integer, required): The ID of the client.
- **Success Response (200 OK):**
  ```json
  {
    "name": "John Doe",
    "address": "123 Main St",
    "phone": "555-1234",
    "dni": "12345678A"
  }
  ```
- **Error Response (404 Not Found):** If the client with the specified ID does not exist.
  ```json
  {}
  ```

#### 3. Create Client
- **POST** `/clients`
- **Description:** Creates a new client.
- **Request Body:** `ClientDTO`
  ```json
  {
    "name": "Samuel Brown",
    "address": "789 Pine Ln",
    "phone": "555-8877",
    "dni": "11223344C"
  }
  ```
- **Success Response (201 Created):** `ClientDTO` of the created client.
  ```json
  {
    "name": "Samuel Brown",
    "address": "789 Pine Ln",
    "phone": "555-8877",
    "dni": "11223344C"
  }
  ```

#### 4. Update Client
- **PUT** `/clients/{id}`
- **Description:** Updates an existing client.
- **URL Parameters:**
  - `id` (integer, required): The ID of the client to update.
- **Request Body:** `ClientDTO` with updated fields.
  ```json
  {
    "name": "Samuel L. Brown",
    "address": "789 Pine Lane, Updated City",
    "phone": "555-8879",
    "dni": "11223344C"
  }
  ```
- **Success Response (200 OK):** `ClientDTO` of the updated client.
  ```json
  {
    "name": "Samuel L. Brown",
    "address": "789 Pine Lane, Updated City",
    "phone": "555-8879",
    "dni": "11223344C"
  }
  ```
- **Error Response (404 Not Found):** If the client with the specified ID does not exist.
  ```json
  {}
  ```

#### 5. Delete Client
- **DELETE** `/clients/{id}`
- **Description:** Deletes a client by their ID.
- **URL Parameters:**
  - `id` (integer, required): The ID of the client to delete.
- **Success Response (204 No Content):** No content is returned.
- **Error Response (404 Not Found):** If the client with the specified ID does not exist.
  ```json
  {}
  ```
