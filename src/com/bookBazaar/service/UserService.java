package com.bookBazaar.service;

import com.bookBazaar.ServiceInterface.IUserService;
import com.bookBazaar.daoImp.UserDao;
import com.bookBazaar.entity.User;
import com.bookBazaar.daoInterface.IUserDao;

public class UserService implements IUserService {
	private IUserDao userDao;
	
	public UserService() {
		this.userDao = new UserDao();
	}

	@Override
	public User getUserById(int id) {
	    User user = userDao.getUserById(id);
	    
	    if (user != null) {
	        return user;
	    } else {
	        System.out.println("\u001B[33mUser not found for ID: " + id + ". Please try again.\u001B[0m");
	        return null;
	    }
	}


    public User login(String email, String password) {
        User user = userDao.getUserByEmailAndPassword(email, password);
        if (user != null) {
            if (user.getRole() == 1) {
                System.out.println("Admin access granted.");
                // Show admin menu
            } else {
                System.out.println("User access granted.");
                // Show user menu
            }
        } else {
        }
        return user;
    }
    
    public boolean register(User user) {
        // Validate user object
        if (user == null) {
            System.out.println("User object cannot be null.");
            return false;
        }

        // Validate user attributes
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            System.out.println("Name cannot be null or empty.");
            return false;
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            System.out.println("Email cannot be null or empty.");
            return false;
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            System.out.println("Password cannot be null or empty.");
            return false;
        }

        // Optionally, check for existing user with the same email
        if (userDao.getUserByEmail(user.getEmail()) != null) {
            System.out.println("A user with this email already exists.");
            return false;
        }

        // Call the UserDao to register the user in the database
        boolean isRegistered = userDao.register(user);
        
        // Provide feedback based on the result
        if (isRegistered) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Failed to register user. Please try again.");
        }

        return isRegistered;
    }

    
    public void updateCredentials(int userId, String newName, String newEmail, String newPassword) {
    	if (userId <= 0) {
            System.out.println("Invalid user ID. It must be greater than 0.");
            return;
        }
    	// Call the UserDao to update credentials in the database
        boolean isUpdated = userDao.updateCredentials(userId, newName, newEmail, newPassword);
        
        // Provide feedback based on the result
        if (isUpdated) {
            System.out.println("User credentials updated successfully.");
        } else {
            System.out.println("Failed to update user credentials. Please try again.");
        }

    }
    
    
    public boolean deleteUsersBookById(int userId, int bookId) {
        // Check for valid userId and bookId
        if (userId <= 0) {
            System.out.println("Invalid user ID. It must be greater than 0.");
            return false;
        }
        if (bookId <= 0) {
            System.out.println("Invalid book ID. It must be greater than 0.");
            return false;
        }

        // Check if the book exists in the user's list before attempting deletion
        boolean isDeleted = userDao.deleteUsersBookById(userId, bookId);
        if (isDeleted) {
            System.out.println("Book successfully removed from user's list.");
        } else {
            System.out.println("Failed to remove book from user's list. The book might not exist or the user might not have it.");
        }
        return isDeleted;
    }

    
    public boolean deleteAllUserBooks(int userId) {
        // Validate userId
        if (userId <= 0) {
            System.out.println("Invalid user ID. It must be greater than 0.");
            return false;
        }
        
        // Attempt to delete all books for the specified user
        boolean isDeleted = userDao.deleteAllUserBooks(userId);

        if (isDeleted) {
            System.out.println("All books successfully removed from user's list.");
        } else {
            // Provide more detailed error messages if needed
            System.out.println("Failed to remove books from user's list. The user might not have any books or an error occurred.");
        }
        
        
        return isDeleted;
    }

}
