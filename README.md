# Load-Balancer

**Load-Balancer** is a Spring Boot-based intelligent load balancer that monitors system metrics (CPU, RPS, Memory) and dynamically switches between synchronous and asynchronous processing modes. It routes requests smartly to handlers optimized for real-time or background tasks.

## Features

- **Load Meter:** Tracks CPU usage, requests per second (RPS), and memory.
- **Alert System:** Logs alerts when CPU or RPS exceed 70%.
- **Decision Engine:** Automatically toggles between sync and async modes based on load.
- **Smart Dispatcher:** Routes `/processData` requests to appropriate handlers.
- **Async Worker:** Handles heavy background tasks like report generation and image processing.
- **Sync Processor:** Handles low latency, real-time operations like CRUD and user-facing APIs.

## Getting Started

### Prerequisites

- Java 17 or 21
- Maven
- Spring Boot 3.x

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/Rahulbudhiraja25/Load-Balancer.git
   cd Load-Balancer
