# Web Order Management System

## 📝 Introduction

This project is a **web-based order management system** built using **Java EE standards** (Servlets, JSP, Java Beans) and follows the **MVC (Model-View-Controller)** design pattern. The application allows users to manage orders, clients, products, and order details efficiently. It is designed to demonstrate proficiency in Java EE technologies, including Servlets, JSP, and MySQL for database management.

---

## 🎯 Objective

The primary goal of this project is to create a **web application** that manages orders for multiple clients. The application adheres to the **MVC architecture** and provides essential CRUD (Create, Read, Update, Delete) operations for managing clients, products, orders, and order details. The project also emphasizes the use of modern web development practices, including dependency management with **Maven** and database interaction with **MySQL**.

---

## 🛠️ Tools and Technologies

- **Backend**: Java EE (Servlets, JSP, Java Beans)
- **Database**: MySQL
- **Build Tool**: Maven
- **Web Server**: Apache Tomcat
- **Frontend**: HTML, CSS, JavaScript, JSTL
- **IDE**: IntelliJ IDEA

---

## 📂 Project Structure

The project is organized into the following layers:

1. **Model Layer**:
   - Contains entity classes (`Client`, `Commande`, `Produit`, `LignedeCommande`) in the package `ma.fstt.entities`.
   - Includes DAO (Data Access Object) classes in the package `ma.fstt.dao` for database interaction.
   - Defines service interfaces in the package `ma.fstt.service` for abstraction.

2. **Controller Layer**:
   - Servlets handle HTTP requests and interact with the model layer.
   - Uses dependency injection (`@Inject`) for loose coupling.

3. **View Layer**:
   - JSP pages for rendering the user interface.
   - Utilizes JSTL (JavaServer Pages Standard Tag Library) for dynamic content.

---

## 🚀 Installation and Setup

### Prerequisites:
1. **Java Development Kit (JDK)**: Ensure JDK 8 or higher is installed.
2. **Apache Tomcat**: Download and configure Tomcat (version 9 or higher).
3. **MySQL**: Install MySQL and set up the database schema.
4. **Maven**: Install Maven for dependency management.

---

## 📋 Features

- **Client Management**:
  - Add, update, delete, and view clients.
- **Product Management**:
  - Add, update, delete, and view products.
- **Order Management**:
  - Create, update, delete, and view orders.
- **Order Details**:
  - Manage order line items (products and quantities).

---

## 📄 Report

A detailed report documenting the project's design decisions, demo and implementation details is available in the file:  
[**Report.docx**](./LAB1_Report.docx)

---

## 📚 Additional Notes
**JSTL**: Used in JSP pages for cleaner and more maintainable code.
**Dependency Injection**: Utilized for better separation of concerns and testability.


---

### Steps to Run the Project:
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/WebOrderManagementSystem.git

---

🚀 Feel free to explore the code and contribute to the project. Your feedback and suggestions are highly appreciated! 🚀
