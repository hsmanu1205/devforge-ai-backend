# 🚀 DevForge AI Backend

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.3-6DB33F?style=for-the-badge\&logo=springboot\&logoColor=white)
![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge\&logo=openjdk\&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-4169E1?style=for-the-badge\&logo=postgresql\&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-Authentication-black?style=for-the-badge\&logo=jsonwebtokens)
![Render](https://img.shields.io/badge/Hosted_on-Render-46E3B7?style=for-the-badge\&logo=render\&logoColor=black)

### AI-Powered Full Stack Project Generator Backend

Secure Spring Boot REST API powering DevForge AI.

🌐 Live API: https://devforge-ai-backend.onrender.com

</div>

---

## ✨ Features

* 🔐 JWT Authentication
* 👤 User Registration & Login
* 🤖 AI Project Generation
* 📂 User-Specific Project Storage
* 🗄 PostgreSQL Database
* 🔒 Spring Security
* 🌍 CORS Support
* ☁️ Render Deployment
* 📄 RESTful APIs

---

## 🛠 Tech Stack

| Technology        | Usage                 |
| ----------------- | --------------------- |
| Java 21           | Backend Language      |
| Spring Boot 3.5.3 | Framework             |
| Spring Security   | Authentication        |
| JWT               | Authorization         |
| PostgreSQL        | Database              |
| Hibernate/JPA     | ORM                   |
| Maven             | Dependency Management |
| Render            | Deployment            |

---

## 📡 API Endpoints

### Authentication

```http
POST /api/auth/register
POST /api/auth/login
```

### AI

```http
POST /api/ai/generate
```

### Projects

```http
GET    /api/projects
POST   /api/projects
DELETE /api/projects/{id}
```

---

## 🗄 Database

PostgreSQL Database hosted on Render.

Features:

* User Management
* Project Storage
* Authentication Records
* Persistent Cloud Storage

---

## 🔒 Security

* JWT Authentication
* Password Encryption using BCrypt
* Stateless Session Management
* Protected API Routes
* Spring Security Integration

---

## 🚀 Local Setup

Clone Repository

```bash
git clone https://github.com/hsmanu1205/devforge-ai-backend.git
```

Install Dependencies

```bash
mvn clean install
```

Run Application

```bash
mvn spring-boot:run
```

---

## 🌐 Production Deployment

Hosted on Render:

https://devforge-ai-backend.onrender.com

---

## 👨‍💻 Author

### Harshit Singh

🔗 GitHub: https://github.com/hsmanu1205

---

## ⭐ Support

If you like this project, please consider giving it a ⭐ on GitHub.
