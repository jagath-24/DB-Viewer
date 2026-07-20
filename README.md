# DB Viewer

A lightweight Spring Boot app that lists all MySQL databases on your local machine through a clean web UI.

## Features

- View all MySQL databases (including system schemas)
- Toggle to show only user-created databases (filters out `information_schema`, `mysql`, `performance_schema`, `sys`)
- REST API endpoints for programmatic access

## Tech Stack

- **Backend:** Java 17, Spring Boot 3.3.2, Spring JDBC
- **Database:** MySQL
- **Frontend:** Vanilla HTML/CSS/JS (served as a static page)

## Prerequisites

- Java 17+
- Maven
- MySQL running on `localhost:3306`

## Getting Started

1. **Clone the repo**
   ```bash
   git clone https://github.com/jagath-24/DB-Viewer.git
   cd DB-Viewer
   ```

2. **Configure your database credentials**

   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/?allowPublicKeyRetrieval=true&useSSL=false
   spring.datasource.username=root
   spring.datasource.password=your_password_here
   ```

3. **Run the app**
   ```bash
   mvn spring-boot:run
   ```

4. **Open in browser**

   Navigate to [http://localhost:8080](http://localhost:8080)

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/databases` | List all databases |
| GET | `/api/databases/user` | List user-created databases only |

### Example Response
```json
{
  "status": "success",
  "count": 3,
  "databases": ["my_app", "test_db", "shop"]
}
```
