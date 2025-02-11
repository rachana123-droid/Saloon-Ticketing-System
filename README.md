# ✨ Salon Ticketing System (JavaFX + MySQL) 💇‍♀️💆‍♂️💅  

A modern, stylish, and fully offline Salon Ticketing System built with **JavaFX**, **MySQL**, and **CSS animations**.  
This system allows **salons to manage bookings, generate tickets, and store customer details** efficiently.

## 🎯 Features  
✔️ Login System - Secure authentication for salon owners.  
✔️ Service Selection - Choose from predefined salon services.  
✔️ Stylish UI - Luxurious salon theme with animations & gradients.  
✔️ Generate Tickets - Print customer tickets with unique IDs.  
✔️ Database Integration - MySQL storage for customer details.  
✔️ TableView Display - View all generated tickets in a modern UI.   
✔️ Offline Support - Works without an internet connection.

## 🖥️ Tech Stack 
🔹 Frontend: JavaFX (FXML & CSS)  
🔹 Backend: Java (JDBC for MySQL)  
🔹 Database: MySQL  
🔹 Libraries: JavaFX Controls  

## 🛠️ Installation & Setup 

### 1️⃣ Prerequisites  
Before running the project, install:  
✅ Java JDK 17+ (Required for JavaFX)  
✅ JavaFX SDK (Download & Configure in IntelliJ)  
✅ MySQL Server (For storing customer bookings)  
✅ IntelliJ IDEA (Recommended IDE)  

### 2️⃣ Clone Repository  
git clone https://github.com/yourusername/Salon-Ticketing-System.git
cd Salon-Ticketing-System

### 3️⃣ Setup MySQL Database

1️⃣ Open MySQL Workbench or Command Line Client
2️⃣ Create a new database:
    CREATE DATABASE salon_ticketing;
    USE salon_ticketing;
3️⃣ Create the tickets table:
    CREATE TABLE tickets (
        id INT AUTO_INCREMENT PRIMARY KEY,
        customer_name VARCHAR(100),
        services TEXT,
        total_amount DECIMAL(10,2)
    );
### 4️⃣ Configure Database in Java

  In DatabaseConnection.java, update the credentials:
  
  private static final String URL = "jdbc:mysql://localhost:3306/salon_ticketing";
  private static final String USER = "root";  // Change if needed
  private static final String PASSWORD = "yourpassword";  // Update accordingly
### 5️⃣ Run the Project
  
  1️⃣ Open IntelliJ IDEA
  2️⃣ Import the Project
  3️⃣ Configure JavaFX in Run Configurations:
  
  --module-path "C:\javafx-sdk\lib" --add-modules javafx.controls,javafx.fxml
  
  4️⃣ Run Main.java 🎉

  
