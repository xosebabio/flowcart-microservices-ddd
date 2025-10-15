# FlowCart: A DDD & Microservices E-commerce Platform

A modern, scalable e-commerce backend built with Spring Boot, Domain-Driven Design (DDD), Hexagonal Architecture, and a complete microservice ecosystem. This project serves as a professional portfolio piece to showcase advanced backend development and architectural skills.

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Docker](https://img.shields.io/badge/Docker-blue)
![Gradle](https://img.shields.io/badge/Gradle-8.x-yellow)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-darkblue)
![CI/CD](https://img.shields.io/badge/CI/CD-GitHub%20Actions-lightgrey)

---

## 🎯 Project Goal & Philosophy

The primary goal of **FlowCart** is to implement a realistic, production-ready backend system for an e-commerce platform. This is not just a simple CRUD application; it's an opinionated software architecture showcase that demonstrates:

* **Domain-Driven Design (DDD):** The codebase is organized around business domains, with rich models, aggregates, and domain events.
* **Hexagonal Architecture (Ports & Adapters):** A clear separation between the core domain logic and infrastructure concerns (web controllers, database persistence, message queues).
* **Microservice Patterns:** Implementation of key patterns like API Gateway, asynchronous event-driven communication, and containerization.
* **Clean Code & Testing:** A strong emphasis on writing clean, maintainable, and well-tested code, including unit, integration, and contract tests.

---

## 🏗️ Architecture Overview

This project is structured as a **monorepo** to simplify dependency management and development workflow. The system is composed of several independent microservices that communicate with each other through synchronous REST APIs and asynchronous events.




### Core Services

* `/services/catalog-service`: Manages products, categories, and inventory.
* `/services/identity-service`: Handles user registration, authentication (JWT), and profiles.
* `/services/ordering-service`: Orchestrates the checkout process and manages orders.
* `/services/basket-service`: Manages temporary user shopping carts (using Redis).
* `/infrastructure/api-gateway`: A single entry point into the system, built with Spring Cloud Gateway.
