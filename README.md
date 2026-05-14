# AWS File Storage App

Full-stack web application for uploading and managing user profile images using AWS cloud services.

---

## Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL (AWS RDS)
- AWS SDK (S3, SQS, SNS)
- AWS Secrets Manager

### Frontend
- React
- Axios
- React Dropzone

---

## AWS Services

- EC2 (backend hosting)
- S3 (image storage)
- RDS (database)
- SQS (event messaging)
- AWS Lambda (SQS consumer / image processing)
- SNS (notifications)
- Secrets Manager (credentials management)
- CloudWatch (logging & monitoring)

---

## Architecture

User → React Frontend → Spring Boot API (EC2) → S3 (store images) → RDS (store metadata) → SQS (event) → AWS Lambda (processing) → SNS (notifications) → CloudWatch (logs)

---

## Features

- Upload profile images
- Store images in Amazon S3
- Save user data in PostgreSQL (RDS)
- Event-driven processing using SQS and Lambda
- Notifications via SNS
- Image download API
- Cloud logging with CloudWatch

---

## API Endpoints

### Get all users
GET /api/v1/user-profile

### Upload image
POST /api/v1/user-profile/{userProfileId}/image/upload

### Download image
GET /api/v1/user-profile/{userProfileId}/image/download

---

## Security

- AWS credentials stored in AWS Secrets Manager
- No hardcoded secrets in code

---

## Author

Gabriel — AWS & Backend Learning Project 🚀
