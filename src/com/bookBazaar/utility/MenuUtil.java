package com.bookBazaar.utility;

import java.util.List;



import java.util.Scanner;

import com.bookBazaar.entity.Book;
import com.bookBazaar.entity.Category;
import com.bookBazaar.entity.Download;
import com.bookBazaar.entity.Review;
import com.bookBazaar.entity.User;
import com.bookBazaar.service.*;
import com.bookBazaar.ServiceInterface.*;

public class MenuUtil {
    private static final Scanner scanner = new Scanner(System.in);
    private static final IBookService bookService = new BookService();
    private static final IUserService userService = new UserService();
    private static final IReviewService reviewService = new ReviewService();
    private static final ICategoryService categoryService = new CategoryService();
    private static final IDownloadService downloadService = new DownloadService();
    
    public static void displayMainMenu() {
        while (true) {
            System.out.println("\nWelcome to Book Bazaar!\n");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");

            System.out.println("Enter the choice: ");
            
            int choice = InputUtil.getUserInput();
            switch (choice) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    handleRegister();
                    break;
                case 3:
                    System.out.println("Thank you for using Book Bazaar! Goodbye.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleLogin() {
        String email = InputUtil.getStringInput("Enter email: ");
        String password = InputUtil.getStringInput("Enter password: ");
        User user = userService.login(email, password);
        
        if (user != null) {
            System.out.println("Login successful. Welcome, " + user.getName() + "!");
            if (user.getRole() == 1) {
                showAdminMenu(user.getId());
            } else {
                showUserMenu(user.getId());
            }
        } else {
        	
        }
    }

    private static void handleRegister() {
        String name = InputUtil.getStringInput("Enter name: ");
        String email = InputUtil.getStringInput("Enter email: ");
        String password = InputUtil.getStringInput("Enter password: ");
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        
        boolean success = userService.register(user);
        if (success) {
            System.out.println("Registration successful! You can now log in.");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }

    public static void showUserMenu(int userId) {
        while (true) {
            System.out.println("\nUser Menu");
            System.out.println("1. View All Books");
            System.out.println("2. View Your Downloaded Books");
            System.out.println("3. Search by Category");
            System.out.println("4. Update Your Credentials");
            System.out.println("5. Logout");

            System.out.println("Enter your choice: ");
            int choice = InputUtil.getUserInput();
            switch (choice) {
                case 1:
                    viewAllBooks(userId, false);
                    break;
                case 2:
                    viewUserBooks(userId);
                    break;
                case 3:
                    searchBooksByCategory(userId);
                    break;
                case 4:
                    updateUserCredentials(userId);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    displayMainMenu();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void showAdminMenu(int userId) {
        while (true) {
            System.out.println("\nAdmin Menu");
            System.out.println("1. View All Books");
            System.out.println("2. Manage Books");
            System.out.println("3. Manage Users");
            System.out.println("4. View Download Logs");
            System.out.println("5. Logout");

            System.out.println("Enter your choice: ");
            int choice = InputUtil.getUserInput();
            switch (choice) {
                case 1:
                    viewAllBooks(userId, true);
                    break;
                case 2:
                    manageBooks(userId, true);
                    break;
                case 3:
                	displayUserDetails(userId);
                    break;
                case 4:
                	displayDownloadLogs();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    displayMainMenu();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    public static void displayUserDetails(int userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Role: " + (user.getRole() == 1 ? "Admin" : "User"));
        } else {
            System.out.println("User not found.");
        }
    }
    
    public static void displayDownloadLogs() {
        List<Download> logs = downloadService.getAllDownloadLogs();
        if (logs.isEmpty()) {
            System.out.println("No download logs found.");
        } else {
            for (Download log : logs) {
                System.out.println("ID: " + log.getId() + ", User ID: " + log.getUserId() + ", Book ID: " + log.getBookId() + ", Date: " + log.getDownload_date());
            }
        }
    }

    // Functionality for managing books, users, viewing all books, etc.
    private static void viewAllBooks(int userId, boolean isAdmin) {
        List<Book> books = bookService.getAllBooks();
        
        if(books.isEmpty()) {
    		System.out.println("No books available.");
    		showUserMenu(userId);
    		return;
    	}
        
        displayBooks(books);

        if (!isAdmin) {
            handleBookSelection(userId, books);
        } else {
            System.out.println("As an admin, you can manage books from the Manage Books menu.");
        }
    }
    
    private static void handleBookSelection(int userId, List<Book> books) {
    	System.out.println("Enter the book you want to see (0 for go back to menu): ");
    	int choice = InputUtil.getUserInput();
    	
    	if(choice == 0) {
    		showUserMenu(userId);
    		return;
    	}
    	
    	
    	if(choice > 0 && choice <= books.size()) {
    		Book book = books.get(choice - 1);
    		System.out.println(choice + ". " + book.getTitle() + " by " + book.getAuthor());
            System.out.println("   Price: $" + book.getPrice() + " | File Size: " + book.getFileSize() + " MB");
            System.out.println();
            System.out.println("1. write a review.");
            System.out.println("2. view all reviews.");
            System.out.println("3. Download the book.");
            System.out.println("4. Back to Menu.");
            System.out.println("Enter the choice: ");
            int op = InputUtil.getUserInput();
            
            switch(op) {
            	case 1: 
            		System.out.println("write review: ");
            		addReview(userId, book.getId());
            		break;
            	case 2:
            		viewBookReviews(userId, book.getId());
            		break;
            	case 3: 
            		downloadBook(userId, book.getId());
            		break;
            	case 4: 
            		showUserMenu(userId);
            		break;
            	default:
            		System.out.println("Invalid Input. try again!");
            		break;
            }
    	}
    }
    
    private static void deleteBooks(int userId, List<Book> books) {
        System.out.println("1. Delete a single book.");
        System.out.println("2. Delete all the books.");
        System.out.println("3. Back to menu.");
        
        int op = InputUtil.getUserInput();
        boolean success = false;
        
        switch (op) {
            case 1:
                System.out.println("Enter the bookId to delete.");
                int bookId = InputUtil.getUserInput();
                
                // Validate bookId
                if (bookId <= 0) {
                    System.out.println("Invalid book ID. Book ID must be greater than 0.");
                    deleteBooks(userId, books);
                    return;
                }
                
                boolean bookExists = books.stream().anyMatch(book -> book.getId() == bookId);
                if (!bookExists) {
                    System.out.println("The book with the provided ID does not exist.");
                    deleteBooks(userId, books);
                    return;
                }
                
                success = userService.deleteUsersBookById(userId, bookId);
                break;
                
            case 2:
                // Confirm deletion
                String confirmation = InputUtil.getStringInput("Are you sure you want to delete all books? (yes/no)");
                if ("yes".equalsIgnoreCase(confirmation)) {
                    success = userService.deleteAllUserBooks(userId);
                } else {
                    System.out.println("Operation canceled.");
                    showUserMenu(userId);
                    return;
                }
                break;
                
            case 3:
                showUserMenu(userId);
                return;
                
            default:
                System.out.println("Invalid option. Please try again!");
                deleteBooks(userId, books);
                return;
        }
        
        if (success) {
            System.out.println("Operation completed successfully.");
        } else {
            System.out.println("Something went wrong!");
        }
        
        showUserMenu(userId);
    }

    
    private static void viewBookReviews(int userId, int bookId) {
        List<Review> reviews = reviewService.getReviewByBookId(bookId);
        
        if (reviews.isEmpty()) {
            System.out.println("\u001B[33mNo reviews found for this book.\u001B[0m");
            return;
        }

        for (Review review : reviews) {
            User user = userService.getUserById(review.getUserId());  // Fetch user details
            String userName = (user != null) ? user.getName() : "Unknown";

            System.out.println("User Name: " + userName);
            System.out.println("Book ID: " + review.getBookId());
            System.out.println("Rating: " + review.getRating());
            System.out.println("Comment: " + review.getReviewText());
            System.out.println("----------------------------------------------------");
        }
        System.out.println();
    }

    private static void searchBooksByCategory(int userId) {
    List<Category> categories = categoryService.getAllCategories();
    
    if (categories.isEmpty()) {
        System.out.println("No categories available.");
        showUserMenu(userId);
        return;
    }

    System.out.println("Available Categories:");
    for (int i = 0; i < categories.size(); i++) {
        System.out.println((i + 1) + ". " + categories.get(i).getName());
    }

    System.out.println("Select a category by number (or enter 0 to return to menu): ");
    int categoryIndex = InputUtil.getUserInput();

    if (categoryIndex == 0) {
        showUserMenu(userId);
        return;
    }
    
    if (categoryIndex < 1 || categoryIndex > categories.size()) {
        System.out.println("Invalid category selection. Please try again.");
        searchBooksByCategory(userId);
        return;
    }

    int categoryId = categories.get(categoryIndex - 1).getId(); // Get the selected category ID
    List<Book> books = bookService.getBooksByCategoryId(categoryId);

    if (books.isEmpty()) {
        System.out.println("No books available for this category.");
        searchBooksByCategory(userId);
        return;
    }

    displayBooks(books);

    System.out.println("Enter the number of the book you want to see (Enter 0 to go back to the menu):");
    int bookChoice = InputUtil.getUserInput();

    if (bookChoice == 0) {
        showUserMenu(userId);
        return;
    }

    if (bookChoice < 1 || bookChoice > books.size()) {
        System.out.println("Invalid book selection. Please try again.");
        searchBooksByCategory(userId);
        return;
    }

    // Display details for the selected book
    Book book = books.get(bookChoice - 1);
    System.out.println(bookChoice + ". " + book.getTitle() + " by " + book.getAuthor());
    System.out.println("   Price: $" + book.getPrice() + " | File Size: " + book.getFileSize() + " MB");
    System.out.println();

    // Book interaction options
    displayBookOptions(userId, book);
}

    private static void displayBookOptions(int userId, Book book) {
    System.out.println("1. Write a review.");
    System.out.println("2. View all reviews.");
    System.out.println("3. Download the book.");
    System.out.println("4. Back to Menu.");
    System.out.println("Enter your choice: ");
    
    int op = InputUtil.getUserInput();

    switch (op) {
        case 1:
            addReview(userId, book.getId());
            break;
        case 2:
            viewBookReviews(userId, book.getId());
            break;
        case 3:
            downloadBook(userId, book.getId());
            break;
        case 4:
            showUserMenu(userId);
            break;
        default:
            System.out.println("Invalid input. Please try again.");
            displayBookOptions(userId, book);
            break;
    }
}

    private static void viewUserBooks(int userId) {
        List<Book> userBooks = bookService.getBooksByUserId(userId);
        
        if (userBooks.isEmpty()) {
            System.out.println("You have not downloaded any books yet.");
            showUserMenu(userId); // Automatically take user back to menu if no books
            return;
        }

        displayBooks(userBooks);
        
        System.out.println("1. Delete the books.");
        System.out.println("2. Back to menu.");
        System.out.println("Enter your choice: ");
        
        int choice = InputUtil.getUserInput();
        
        switch (choice) {
            case 1:
                deleteBooks(userId, userBooks);
                break;
            case 2:
                showUserMenu(userId);
                break;
            default:
                System.out.println("Invalid selection! Please try again.");
                viewUserBooks(userId); // Rerun this method in case of invalid input
                break;
        }
    }

    // Additional methods for handling book reviews, downloads, admin management of users/books
    private static void displayBooks(List<Book> books) {
        System.out.println("\nAvailable Books:");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println("booksId: " + book.getId() + " | " + book.getTitle() + " by " + book.getAuthor());
            System.out.println("Price: $" + book.getPrice() + " | File Size: " + book.getFileSize() + " MB");
        }
    }

    private static void updateUserCredentials(int userId) {
        // Prompt for new credentials
    	System.out.print("Enter new name (press Enter to skip): ");
        String newName = scanner.nextLine();
        System.out.print("Enter new email (press Enter to skip): ");
        String newEmail = scanner.nextLine();
        System.out.print("Enter new password (press Enter to skip): ");
        String newPassword = scanner.nextLine();
        System.out.println();
        
        // Check if the user entered any new data
        boolean hasChanges = !newName.isEmpty() || !newEmail.isEmpty() || !newPassword.isEmpty();

        if (!hasChanges) {
            System.out.println("No changes made.");
            return;
        }

        // Update credentials in userService
        userService.updateCredentials(userId, newName, newEmail, newPassword);
    }

    private static void addReview(int userId, int bookId) {
    	String reviewText = scanner.nextLine();
    	
    	if(reviewText.isEmpty()) {
    		System.out.println("Review is blank. Please write something!");
    		addReview(userId, bookId);
    	}
    	
    	boolean success = reviewService.addReview(userId, bookId, 4, reviewText);
    	System.out.println(success ? "Review added Sucessfully" : "Something went wrong!");
    }
    
//    download the book
    private static void downloadBook(int userId, int bookId) {
        boolean success = bookService.downloadBook(userId, bookId);
        
        if (success) {
            System.out.println("Book downloaded successfully.");
        } else {
            System.out.println("Failed to download the book. It might already be downloaded or an error occurred.");
        }
    }
    
    private static void manageBooks(int userId, boolean isAdmin) {
        while (true) {  // Loop until the admin chooses to go back
            System.out.println("\nManage Books:");
            System.out.println("1. Add Book");
            System.out.println("2. Add Category");
            System.out.println("3. Update Book");
            System.out.println("4. View All Books");
            System.out.println("5. Back to Admin Menu");

            System.out.print("Enter your choice: ");
            int choice = InputUtil.getUserInput(); // Assuming a utility to capture user input

            switch (choice) {
                case 1:
                    addBook(userId, isAdmin);
                    break;
                case 2: 
                    addCategory(userId, isAdmin); // Call the method to add a category
                    break;
                case 3:
                    updateBook(userId, isAdmin); // Call the method to update a book
                    break;
                case 4:
                    viewAllBooks(userId, isAdmin); // Call the method to view all books
                    break;
                case 5:
                    System.out.println("Returning to Admin Menu...");
                    showAdminMenu(userId); // Exit back to the Admin Menu
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }


    private static void addCategory(int userId, boolean isAdmin) {
        String categoryName = InputUtil.getStringInput("Enter Category title: ").trim(); // Get user input for category title

        // Validation for empty or too short category name
        if (categoryName.isEmpty()) {
            System.out.println("Category title cannot be empty.");
            return;  // Return early if the input is invalid
        } else if (categoryName.length() < 3) {
            System.out.println("Category title must be at least 3 characters long.");
            return;  // Return early for invalid length
        }

        Category category = new Category();
        category.setName(categoryName);

        // Assuming we have a CategoryService that interacts with the DAO
        CategoryService categoryService = new CategoryService();
        
        boolean success = categoryService.addCategory(category);

        if (success) {
            System.out.println("Category added successfully.");
        } else {
            System.out.println("Failed to add category. It might already exist.");
        }
    }

    private static void addBook(int userId, boolean isAdmin) {
    	// Input and validation for book title
        System.out.println("Enter book title:");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty. Please try again.");
            addBook(userId, isAdmin); // Retry
            return;
        }

        // Input and validation for book author
        System.out.println("Enter book author:");
        String author = scanner.nextLine().trim();
        if (author.isEmpty()) {
            System.out.println("Author cannot be empty. Please try again.");
            addBook(userId, isAdmin); // Retry
            return;
        }

        // Input and validation for book price
        double price = getValidDouble("Enter book price:");
        if (price <= 0) {
            System.out.println("Price must be a positive number. Please try again.");
            addBook(userId, isAdmin); // Retry
            return;
        }

        // Input and validation for book file size
        double fileSize = getValidDouble("Enter book file size (in MB):");
        if (fileSize <= 0) {
            System.out.println("File size must be a positive number. Please try again.");
            addBook(userId, isAdmin); // Retry
            return;
        }

        // Input and validation for book category ID
        int categoryId = getValidInteger("Enter book category ID:");
        if (categoryId <= 0 || !categoryService.isValidCategoryId(categoryId)) {
            System.out.println("Invalid category ID. Please try again.");
            addBook(userId, isAdmin); // Retry
            return;
        }

        // Adding the book
        boolean success = bookService.addBook(title, author, price, fileSize, categoryId);

        if (success) {
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Failed to add the book. Please try again.");
        }

        manageBooks(userId, isAdmin); // Return to the manage books menu
    }

    
    private static double getValidDouble(String prompt) {
        double value = -1;
        while (true) {
            System.out.println(prompt);
            try {
                value = Double.parseDouble(scanner.nextLine().trim());
                if (value >= 0) break;
                else System.out.println("Value cannot be negative. Please enter a valid value.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
        return value;
    }

    // Helper method for getting a valid integer input
    private static int getValidInteger(String prompt) {
        int value = -1;
        while (true) {
            System.out.println(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine().trim());
                if (value > 0) break;
                else System.out.println("ID must be positive. Please enter a valid ID.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
        return value;
    }
    
    private static void updateBook(int userId, boolean role) {
        System.out.println("Enter the ID of the book you want to update:");
        int bookId = InputUtil.getUserInput();

        // Fetch the existing book details
        Book existingBook = bookService.getBookbyId(bookId);
        if (existingBook == null) {
            System.out.println("Book not found.");
            manageBooks(userId, role); // Return to manage books menu
            return;
        }

        System.out.println("Current details of the book:");
        System.out.println("Title: " + existingBook.getTitle());
        System.out.println("Author: " + existingBook.getAuthor());
        System.out.println("Price: $" + existingBook.getPrice());
        System.out.println("File Size: " + existingBook.getFileSize() + " MB");
        System.out.println("Category ID: " + existingBook.getCategoryId());
        
        // Prompt for new details
        System.out.println("Enter new title (press Enter to keep current title):");
        String title = scanner.nextLine();
        if (title.isEmpty()) title = existingBook.getTitle();
        
        System.out.println("Enter new author (press Enter to keep current author):");
        String author = scanner.nextLine();
        if (author.isEmpty()) author = existingBook.getAuthor();
        
        System.out.println("Enter new price (press Enter to keep current price):");
        String priceInput = scanner.nextLine();
        double price = existingBook.getPrice();
        if (!priceInput.isEmpty()) {
            price = Double.parseDouble(priceInput);
        }

        System.out.println("Enter new file size (in MB, press Enter to keep current file size):");
        String fileSizeInput = scanner.nextLine();
        double fileSize = existingBook.getFileSize();
        if (!fileSizeInput.isEmpty()) {
            fileSize = Double.parseDouble(fileSizeInput);
        }
        
        System.out.println("Enter new category ID (press Enter to keep current category ID):");
        String categoryIdInput = scanner.nextLine();
        int categoryId = existingBook.getCategoryId();
        if (!categoryIdInput.isEmpty()) {
            categoryId = Integer.parseInt(categoryIdInput);
        }

        // Update the book using the BookService
        boolean success = bookService.updateBook(bookId, title, author, price, fileSize, categoryId);

        if (success) {
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Failed to update the book. Please try again.");
        }

        manageBooks(userId, role); // Return to manage books menu
    }
    
}