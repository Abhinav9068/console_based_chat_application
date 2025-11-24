# Java Web Chat Application

A dynamic, multi-tier web application for real-time messaging, refactored from a legacy console-based system. This project uses Java Servlets, JSP, and a MySQL database to provide a secure and persistent chat environment.

## 🚀 Features

*   **Web-Based Interface**: Modern web UI using JSP and CSS (replaces the old console UI).
*   **User Management**: Registration and Login system with Role-Based Access Control (Admin/Guest).
*   **Persistent Storage**: MySQL database stores users, messages, and logs (replaces flat files).
*   **Session Management**: Secure `HttpSession` handling for tracking logged-in users.
*   **Concurrency**: Thread-safe `SessionManager` to handle multiple concurrent users.
*   **Security**: PreparedStatement implementation to prevent SQL Injection.
*   **Architecture**: MVC pattern with DAO (Data Access Object) layer and Service layer.

## 🛠️ Tech Stack

*   **Language**: Java 21
*   **Build Tool**: Maven
*   **Web Framework**: Jakarta EE 10 (Servlet 6.0, JSP 3.1)
*   **Database**: MySQL 8.0+
*   **Server**: Apache Tomcat 10.1 or 11.0+

## 📋 Prerequisites

Ensure you have the following installed on your system:
1.  **Java Development Kit (JDK) 21**
2.  **Apache Maven**
3.  **MySQL Server** (and a tool like MySQL Workbench)
4.  **Apache Tomcat 11** (Required for Jakarta EE 10 support)

## ⚙️ Installation & Setup

### 1. Database Setup
1.  Open your MySQL client (e.g., Workbench, Command Line).
2.  Open the `db.sql` file located in the project root.
3.  **Execute the entire script**. This will:
    *   Create the database `chatapp_db`.
    *   Create tables: `users`, `messages`, `user_logs`.
    *   (Note: The script drops existing tables to ensure a clean state).

### 2. Application Configuration
1.  Navigate to `src/main/java/com/chatapp/util/DatabaseConnectionManager.java`.
2.  Update the `PASSWORD` field with your local MySQL root password:
    ```java
    private static final String PASSWORD = "YOUR_MYSQL_PASSWORD";
    ```

### 3. Build the Project
Open a terminal in the project root directory (where `pom.xml` is located) and run:
```bash
mvn clean package
```
This will generate a WAR file in the `target/` directory: `chat-application-1.0-SNAPSHOT.war`.

### 4. Deploy to Tomcat
1.  Navigate to the `target/` folder.
2.  Copy the `chat-application-1.0-SNAPSHOT.war` file.
3.  Paste it into your Tomcat's `webapps` directory (e.g., `C:\Program Files\Apache Software Foundation\Tomcat 11.0\webapps`).
4.  Start the Tomcat server (run `bin/startup.bat`).

## 🏃‍♂️ How to Run

1.  Open your web browser.
2.  Navigate to:
    ```
    http://localhost:8080/chat-application-1.0-SNAPSHOT/
    ```
3.  **Register**: Click "Need an account? Register" to create a new user.
4.  **Login**: Use your new credentials to log in.
5.  **Chat**: Post messages and see them appear in the chat history.

## 📂 Project Structure

```
src/
├── main/
│   ├── java/com/chatapp/
│   │   ├── controller/   # Servlets (Login, Register, Chat)
│   │   ├── dao/          # Database Access Objects (JDBC)
│   │   ├── model/        # Data Models (User, Message)
│   │   ├── service/      # Business Logic (SessionManager)
│   │   ├── util/         # Database Connection Utils
│   │   └── exception/    # Custom Exceptions
│   └── webapp/
│       ├── WEB-INF/      # Configuration (web.xml)
│       ├── chat.jsp      # Chat Interface
│       └── index.jsp     # Login/Register Interface
```
