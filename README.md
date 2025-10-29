# Hexagonal Architecture (Ports & Adapters) Demo 🚀

A practical demonstration of **Hexagonal Architecture** (aka Ports & Adapters) using **Java 17+** and **Spring Boot**.  

This project implements a **multi-channel, multi-input notification service**, showing the true value of this architecture beyond simple "to-do" examples.

---

## 🎯 Core Concept

The main goal of Hexagonal Architecture is to **isolate core business logic (the Hexagon)** from all external technologies and frameworks.

- **Domain (Core)**: Pure Java, framework-independent. Knows nothing about Spring, Kafka, REST, or any database.
- **Inbound Ports (Driving Ports)**: Define what the application can do (`SendNotificationUseCase`).
- **Outbound Ports (Driven Ports)**: Define what the core needs from the outside (`NotificationDispatcher`, `UserPreferenceRepository`).

**Dependency Rule**: Technology must adapt to the application's core, not the other way around.


## ✨ Key Features

- **Isolated Core Logic**: `NotificationService` is pure Java with zero dependencies on Spring or external providers.  
- **Multiple Inbound Adapters**: REST and Kafka use the same `SendNotificationUseCase`.  
- **Plug & Play Outbound Adapters**: `List<NotificationDispatcher>` allows adding channels easily.  
- **Mocked Adapters**: Twilio, SendGrid, and Slack are simulated and log to the console.  

---

