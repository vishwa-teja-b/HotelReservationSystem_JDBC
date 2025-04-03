# 🏨 Hotel Reservation System

## 📌 About the Project
The **Hotel Reservation System** is a **Java-based console application** that allows users to:
- 📌 Create a reservation
- 📖 View reservations
- ✏️ Update reservations
- 🗑 Delete reservations
- 🏠 Get room number by reservation ID

This project uses **JDBC** to interact with a **MySQL database**, providing a seamless reservation experience. 🚀

---

## 🛠 Tech Stack
- **Programming Language:** Java ☕
- **Database:** MySQL 🗄
- **JDBC Driver:** MySQL Connector/J 🔌

---

## 🚀 Features
✅ **Create a Reservation** – Stores guest details and room number in the database.  
✅ **View All Reservations** – Displays all stored reservations in a formatted table.  
✅ **Update a Reservation** – Modify an existing reservation using its ID.  
✅ **Delete a Reservation** – Remove an unwanted reservation.  
✅ **Retrieve Room Number** – Get a room number using guest name & reservation ID.  
✅ **Console-Based Interaction** – User-friendly command-line interface.  

---

## 📌 Database Schema
The **reservations** table is structured as follows:
```sql
CREATE TABLE reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_name VARCHAR(255) NOT NULL,
    room_number INT NOT NULL,
    contact VARCHAR(15) NOT NULL,
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## 🔧 Setup & Installation
Follow these steps to run the project:

### **1️⃣ Install MySQL & Create Database**
```sql
CREATE DATABASE hotel_db;
USE hotel_db;
```
Then, create the **reservations** table using the schema above. ✅

### **2️⃣ Clone the Repository**
```sh
git clone https://github.com/your-username/hotel-reservation-system.git
cd hotel-reservation-system
```

### **3️⃣ Configure the Database Connection**
Update the following details in the **Java file**:
```java
private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";
private static final String username = "root";
private static final String password = "your_password";
```

### **4️⃣ Compile & Run the Application**
```sh
javac HotelReservationSystem.java
java HotelReservationSystem
```

---

## 🖥 Usage Guide
📌 Run the program, and you'll see a menu like this:
```
HOTEL RESERVATION SYSTEM
1. Create Reservation
2. View Reservations
3. Update Reservation(s)
4. Delete Reservation(s)
5. Get Room Number
0. EXIT
Choose an Option:
```

📝 Simply enter the respective **number** to perform an action!

---

## 📸 Demo Screenshot

![image](https://github.com/user-attachments/assets/7112d6f3-bfb4-4d87-9f70-2c98b3a3af3c)


---

## 🤝 Contributing
Feel free to fork this repository and submit **pull requests** if you find improvements! 🔥

---

## 📜 License
This project is **open-source** under the **MIT License**. 📄

---

## 📩 Contact
💡 Have questions or suggestions? Reach out to me at:  
📧 **vishwateja2k4@gmail.com**

Happy Coding! 🚀🎯

