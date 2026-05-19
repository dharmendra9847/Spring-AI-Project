# 🚀 Spring-AI-Project

> Enterprise-grade Spring Boot project integrated with Spring AI for building modern AI-powered applications.

---

# 📌 Overview

Spring-AI-Project is a professional backend starter template built using:

- Spring Boot 3
- Spring AI
- REST APIs
- Maven
- Docker
- Java 21

This project is designed to help developers quickly start building AI-integrated enterprise applications using OpenAI, Ollama, Azure OpenAI, and other LLM providers.

---

# ✨ Features

## ✅ Core Features

- Spring Boot 3.x Architecture
- Spring AI Integration
- RESTful APIs
- Environment-based Configuration
- Layered Clean Architecture
- Production-Ready Structure
- Docker Support
- Centralized Exception Handling
- Logging Support
- Validation Support
- Lombok Integration
- API Versioning Ready
- Scalable Design Pattern

---

# 🛠️ Tech Stack

| Technology | Version |
|------------|----------|
| Java | 21 |
| Spring Boot | 3.x |
| Spring AI | Latest |
| Maven | Latest |
| Docker | Latest |
| Lombok | Latest |

---

# 📂 Complete Professional Project Structure

```bash
Spring-AI-Project/
│
├── .github/
│   └── workflows/
│       └── ci.yml
│
├── docker/
│   └── Dockerfile
│
├── logs/
│
├── src/
│   ├── main/
│   │
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── springai/
│   │   │           └── project/
│   │   │
│   │   │               ├── SpringAiProjectApplication.java
│   │   │               │
│   │   │               ├── config/
│   │   │               │   ├── AiConfig.java
│   │   │               │   ├── SwaggerConfig.java
│   │   │               │   └── SecurityConfig.java
│   │   │               │
│   │   │               ├── controller/
│   │   │               │   └── ChatController.java
│   │   │               │
│   │   │               ├── service/
│   │   │               │   ├── ChatService.java
│   │   │               │   └── impl/
│   │   │               │       └── ChatServiceImpl.java
│   │   │               │
│   │   │               ├── repository/
│   │   │               │
│   │   │               ├── dto/
│   │   │               │   ├── ChatRequest.java
│   │   │               │   └── ChatResponse.java
│   │   │               │
│   │   │               ├── entity/
│   │   │               │
│   │   │               ├── exception/
│   │   │               │   ├── GlobalExceptionHandler.java
│   │   │               │   └── ResourceNotFoundException.java
│   │   │               │
│   │   │               ├── util/
│   │   │               │   └── AppConstants.java
│   │   │               │
│   │   │               └── security/
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       ├── application-prod.properties
│   │       ├── banner.txt
│   │       └── static/
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── springai/
│                   └── project/
│                       └── SpringAiProjectApplicationTests.java
│
├── target/
│
├── .gitignore
├── .gitattributes
├── pom.xml
├── README.md
├── mvnw
├── mvnw.cmd
└── LICENSE
```

---

# ⚙️ Getting Started

# 1️⃣ Clone Repository

```bash
git clone https://github.com/dharmendra9847/Spring-AI-Project.git
cd Spring-AI-Project
```

---

# 2️⃣ Configure API Key

Open:

```bash
src/main/resources/application.properties
```

Add:

```properties
spring.ai.openai.api-key=${OPENAI_API_KEY}
```

OR

```properties
spring.ai.openai.api-key=YOUR_API_KEY
```

---

# 3️⃣ Install Dependencies

```bash
mvn clean install
```

---

# 4️⃣ Run Application

```bash
mvn spring-boot:run
```

Application Starts On:

```bash
http://localhost:8080
```

---

# 🤖 Sample API

## Request

```http
GET /api/v1/chat?prompt=Hello
```

## Response

```json
{
  "response": "Hello! How can I help you today?"
}
```

---

# 🐳 Docker Support

# Build Docker Image

```bash
docker build -t spring-ai-project .
```

# Run Docker Container

```bash
docker run -p 8080:8080 spring-ai-project
```

---

# 🔒 Security Best Practices

- Never commit secrets
- Use `.env` files
- Use Spring Profiles
- Configure environment variables
- Use HTTPS in production
- Enable centralized logging

---

# 🧪 Testing

Run all tests:

```bash
mvn test
```

---

# 📈 Future Improvements

- AI Streaming
- Vector Database Integration
- Retrieval-Augmented Generation (RAG)
- JWT Authentication
- Role-Based Access Control
- Chat History Persistence
- Kubernetes Deployment
- CI/CD Pipelines

---

# 🚀 Deployment Options

- Docker
- AWS ECS
- Kubernetes
- Railway
- Render
- Azure App Service

---

# 🤝 Contribution Workflow

```bash
# Fork repository
# Create branch
git checkout -b feature/new-feature

# Commit changes
git commit -m "feat: add new AI endpoint"

# Push changes
git push origin feature/new-feature
```

---

# 📄 License

Licensed under the MIT License.

---

# 👨‍💻 Author

Developed using ❤️ with Spring Boot & Spring AI.