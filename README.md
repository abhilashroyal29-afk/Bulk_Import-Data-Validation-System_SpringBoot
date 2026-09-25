# Bulk Data Import & Validation System

## Technologies Used
- Java 25
- Spring Boot 3
- Spring Data JPA
- MySQL
- Maven

## Features
- Upload CSV/Excel Files
- Validate Records
- Async File Processing
- Duplicate File Check
- Import Summary API
- Import Status API

## REST APIs

### Upload File
POST /api/import/upload

### Get Status
GET /api/import/status/{jobId}

### Get Summary
GET /api/import/summary/{jobId}

## Database Tables

### ImportJob
- id
- file_name
- status
- created_at

### ImportRecord
- id
- job_id
- data
- status
- error_message

## Steps to Run

1. Clone the project
2. Configure MySQL in application.properties
3. Run the Spring Boot Application
4. Test APIs using Postman

## Sample Files
- students.csv
- students.xlsx