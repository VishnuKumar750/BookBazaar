# 📚 Book Bazar

**Book Bazar** is a Java-based console application for managing books and users. It includes features for book and user management, as well as download tracking.

---

## 📋 Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [How to Run](#how-to-run)
- [Usage](#usage)
- [CRUD Operations](#crud-operations)
- [Error Handling](#error-handling)
- [Future Enhancements](#future-enhancements)
- [License](#license)
- [Tags](#tags)

---

## ✨ Features

- **Admin Features**
  - Manage Books: Add, update, delete, and view all books.
  - Manage Users: Create, update, delete, and view users.
  - Category Management: Add categories for books.
  - View Download Logs: Track user download history.

- **User Features**
  - Register/Login: Create and manage user accounts.
  - Download Books: Download available books and track downloads.
  - View Download History: Access a list of previously downloaded books.

---

## 💻 Technologies Used

- **Java 8+** for the core application.
- **JDBC** for database interactions.
- **MySQL** for data storage.

---

## 🛠 How to Run

1. Clone the Repository:
   ```bash
   git clone https://github.com/yourusername/book-bazar.git
   cd book-bazar

## 🛠 How to Run

1. **Set Up the Database**
   - Create a MySQL database and tables to store the application's data.
   - Update the `DatabaseUtil.java` file to include your MySQL credentials:
     ```java
     public static Connection getConnection() throws SQLException {
         return DriverManager.getConnection("jdbc:mysql://localhost:3306/book_bazar", "username", "password");
     }
     ```

2. **Compile and Run the Project**
   - Compile the Java files:
     ```bash
     javac Main.java
     ```
   - Run the compiled project:
     ```bash
     java Main
     ```

## 🔍 Usage

### Admin Menu
- **Add Book**: Admins can enter book details such as title, author, price, and category.
- **Update Book**: Admins can modify the information of an existing book.
- **Delete Book**: Remove books from the system; note that deleted books will still appear in users' download history.
- **Manage Users**: Admins can register, update, delete, or view user details.
- **View Download Logs**: Admins can see which users downloaded specific books.

### User Menu
- **Register/Login**: Users must register or log in to access the book catalog.
- **Download Books**: Users can download books, and each download is recorded in the system.
- **View Download History**: Users can view a list of their past downloads.



## 🔄 CRUD Operations

- **📚 Books**
  - **Add Book**: Admins can add new books by providing details such as title, author, price, and category.
  - **Update Book**: Admins can modify details of existing books, such as title, author, or price.
  - **Delete Book**: Admins can remove books from the system. Note: Deleted books remain in user download histories but are no longer available for download.
  - **View All Books**: Users and admins can view a list of all available books.

- **👥 Users**
  - **Register/Login**: Users can create accounts or log in to access the book catalog.
  - **Admin User Management**: Admins can add new users, update existing user details, delete users, or view user information.

- **📥 Downloads**
  - **Download Books**: Users can download books from the catalog, which are recorded in the system.
  - **Download History**: Track the history of downloads, including records of books that have been deleted from the system (users can still see the books they have downloaded).


## ❗ Error Handling

- **Input Validation**: All user inputs, including book price, file size, and category selection, are validated to ensure correctness and prevent invalid data from being processed.
- **SQLException Handling**: SQL exceptions are carefully handled and logged, ensuring that the application remains stable and issues are traceable without causing crashes.
- **Duplicate Entries**: Mechanisms are in place to prevent duplicate entries, such as category names or user registrations, ensuring data integrity.
- **Transaction Management**: Transactions are managed with rollbacks to maintain data consistency in case of errors during database operations.

## 🚀 Future Enhancements

- **Search Functionality**: Implement a search feature to enable users to find books by title, author, or category.
- **Book Wishlist**: Allow users to save books to a wishlist for future downloads, enhancing user engagement.
- **User Reviews**: Introduce a system for users to leave reviews or rate books, providing valuable feedback and enhancing book discovery.
- **Enhanced UI**: Develop a graphical user interface (GUI) to improve user experience and make interactions more intuitive.


📝 License
This project is licensed under the MIT License. See the LICENSE file for details.

📌 Tags
#Java #SQL #BookManagement #ConsoleApp #CRUDOperations #AdminPanel #UserManagement

