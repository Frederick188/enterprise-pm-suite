# Enterprise Project Management System

A full-stack project management platform built using Java Spring Boot, React, and MySQL. The application enables teams to manage projects, track tasks, organize sprints, collaborate in real time, and monitor activities through a secure multi-tenant architecture.

## Features

### Authentication & Authorization

* JWT-based authentication
* Secure login and registration
* Role-Based Access Control (RBAC)
* Protected API endpoints

### Project Management

* Create, update, and delete projects
* Multi-tenant architecture
* Organization-level data isolation
* Project dashboard

### Task Management

* Create and assign tasks
* Task priorities and statuses
* Kanban board workflow
* Task status transitions (TODO → IN_PROGRESS → DONE)

### Sprint Management

* Create sprints
* Start and close sprints
* Associate tasks with sprints
* Sprint progress tracking

### Real-Time Collaboration

* WebSocket-based notifications
* Live task updates
* Team activity updates

### File Management

* Upload task attachments
* Manage project-related files

### Enterprise Features

* Audit logging
* Global exception handling
* Optimistic locking
* RESTful API architecture
* Secure multi-tenant design

---

## Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA (Hibernate)
* JWT Authentication
* WebSocket (STOMP)
* Maven

### Frontend

* React

### Database

* MySQL

### Tools

* Git & GitHub
* Postman

---

## System Architecture

React Frontend
↓
REST APIs
↓
Spring Boot Backend
↓
MySQL Database

---

## Key Modules

### User Management

* User Registration
* User Authentication
* Role Management

### Project Module

* Project CRUD Operations
* Organization Isolation

### Task Module

* Task Creation
* Assignment
* Status Tracking
* Kanban Workflow

### Sprint Module

* Sprint Planning
* Sprint Execution
* Sprint Completion

### Notification Module

* Real-Time Updates
* WebSocket Communication

### Audit Module

* Activity Tracking
* User Action Logs

---

## API Examples

### Login

POST /api/auth/login

### Create Project

POST /api/projects

### Get Projects

GET /api/projects

### Create Task

POST /api/tasks

### Update Task Status

PATCH /api/tasks/{taskId}/status

### Create Sprint

POST /api/sprints

---

## Installation

### Clone Repository

```bash
git clone https://github.com/yourusername/enterprise-project-management-system.git
cd enterprise-project-management-system
```

### Configure Database

Update application.properties:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/enterprise_pm
spring.datasource.username=root
spring.datasource.password=yourpassword
```

### Run Backend

```bash
mvn clean install
mvn spring-boot:run
```

### Run Frontend

you can access the frontend repository here: https://github.com/Frederick188/enterprise-frontend

```bash
cd frontend
npm install
npm run dev
```

---

## Future Enhancements

* Docker Deployment
* CI/CD Pipeline
* Swagger/OpenAPI Documentation
* Redis Caching
* Email Notifications
* Advanced Analytics Dashboard
* AWS Cloud Deployment
* Microservices Architecture

---

## Resume Highlights

* Designed and developed a Jira-inspired Enterprise Project Management System using Spring Boot, React, MySQL, and JWT Authentication.
* Implemented multi-tenant architecture, RBAC, WebSockets, audit logging, sprint management, and Kanban-based task tracking.
* Built secure REST APIs with organization-level data isolation and enterprise-grade backend design principles.

---

## Author

Udit Ray
