# 🧾 Invoice Generator Web App
This repository hosts a lightweight, full-stack Invoice Generator application built with Java Spring Boot and MySQL. The backend manages dealers, vehicles, and invoices, providing a structured way to generate and manage invoices dynamically. The project is built with Spring Boot, JPA/Hibernate, and Thymeleaf, making it scalable, maintainable, and easy to deploy on cloud servers like AWS.

## 📋 Table of Contents

- [Overview](#-overview)
- [Feature](#️-feature)  
- [Technology Used](#-technology-used)  
- [Project Structure](#-project-structure)
- [Setup Instructions](#-setup-instruction) 
- [Contact](#-contact)  
---

## 🚀 Overview
- **📑 Invoice Generation:** Automatically generate invoices with dealer, vehicle, and transaction details.
- **🛠 Dealer & Vehicle Management:** CRUD operations for dealers, addresses, and vehicles.
- **🔑 Transaction Tracking:** Generates unique transaction IDs for every invoice.
- **🎨 Thymeleaf UI:** Simple, responsive web interface to generate and view invoices.
- **⚙ Modular Backend:** Follows layered architecture with controller, service, repository, and entity separation.
---
## 🚀 Features
- 🧑‍💼 Dealer CRUD with address mapping (One-to-One relationship)
- 🚘 Vehicle CRUD with VIN and registration tracking
- 🧾 Invoice generation with auto-generated Transaction ID
- 📊 MySQL persistence using JPA/Hibernate
- 🔐 Centralized exception handling (user-friendly error messages)
- 🌍 Deployable on Linux servers with Nginx reverse proxy & SSL
---
## 🛠️ Technologies Used

| Layer             | Technology                          |
|------------------|-------------------------------------|
| Language          | Java 17                             |
| Framework         | Spring Boot,                        |
| ORM               | Hibernate / JPA                     |
| Database          | MySQL                               |
| API Architecture  | RESTful APIs                        |
| Templating Engine | Thymeleaf                           |
| Frontend          | HTML, Tailwind CSS                  |
| Build Tool        | Maven                               |
| Others            | Lombok, Git                         |

---

## 🗂️ Project Structure
* `src/`
    * `main/`
        * `java/`
            * `com/`
                * `invoicegenerator/`
                    * `controller/`
                    * `dto/`
                    * `entity/`
                    * `helper/`
                    * `repository/`
                    * `service/`
                        * `serviceImpl`   
                    * `exception`  
                    * `InvoiceGeneratorApplication.java`
        * `resources/`
            * `static/`
            * `template/`
            * `application.properties`
            * `application-prod.properties`
---

## 🛠️ Setup Instructions (Local)

1. **Clone the repo:**
   ```bash
   git clone https://github.com/ranjanvscode/Generate-Invoice.git
   cd Generate-Invoice
2. **Configure MySQL DB:**
   ```bash
   spring.datasource.url=${DB_URL}
   spring.datasource.username=${DB_USERNAME}
   spring.datasource.password=${DB_PASSWORD}
## 🧑‍💻 Contact
- Ranjan Kumar
- Backend Java Developer
- 📧 ranjangp2255@gmail.com
