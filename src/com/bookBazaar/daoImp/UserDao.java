package com.bookBazaar.daoImp;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bookBazaar.entity.User;
import com.bookBazaar.utility.*;
import com.bookBazaar.daoInterface.IUserDao;

public class UserDao implements IUserDao {

    // Fetch user by email and password
	@Override
	public User getUserByEmailAndPassword(String email, String password) {
	    String query = "SELECT * FROM User WHERE email = ? AND password = ?";
	    
	    try (Connection conn = DatabaseUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setString(1, email);
	        stmt.setString(2, password);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            // Constructing User object from the result set
	            User user = new User();
	            user.setId(rs.getInt("id"));
	            user.setName(rs.getString("name"));
	            user.setEmail(rs.getString("email"));
	            user.setRole(rs.getInt("role"));
	            return user;
	        } else {
	            System.out.println("\u001B[33mInvalid email or password. Please try again.\u001B[0m");
	        }
	    } catch (SQLException e) {
	        // More specific exception handling for SQL issues
	        System.err.println("\u001B[31mSQL Error: Unable to fetch user. " + e.getMessage() + "\u001B[0m");
	    } catch (Exception e) {
	        // General exception handling
	        System.err.println("\u001B[31mError: Unexpected issue occurred while fetching user. " + e.getMessage() + "\u001B[0m");
	    }
	    return null;
	}


    // Register new user
	@Override
    public boolean register(User user) {
        boolean result = false;
        String query = "INSERT INTO User (name, email, password, user_date) VALUES (?, ?, ?, CURRENT_DATE)";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            // Set parameters
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            
            // Execute the insert statement
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                result = true;
            }
        } catch (SQLException e) {
            System.err.println("\u001B[31mError: Unable to register user. Please try again.\u001B[0m");
        }
        return result;
    }

    // Update user credentials
	@Override
    public boolean updateCredentials(int userId, String newName, String newEmail, String newPassword) {
        StringBuilder sql = new StringBuilder("UPDATE User SET ");
        boolean hasNewName = !newName.trim().isEmpty();
        boolean hasNewEmail = !newEmail.trim().isEmpty();
        boolean hasNewPassword = !newPassword.trim().isEmpty();
        
        if (hasNewName) {
            sql.append("name = ?, ");
        }
        if (hasNewEmail) {
            sql.append("email = ?, ");
        }
        if (hasNewPassword) {
            sql.append("password = ?, ");
        }
        
        if (sql.toString().endsWith(", ")) {
            sql.setLength(sql.length() - 2);
        }
        sql.append(" WHERE id = ?");

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            int index = 1;
            if (hasNewName) {
                pstmt.setString(index++, newName);
            }
            if (hasNewEmail) {
                pstmt.setString(index++, newEmail);
            }
            if (hasNewPassword) {
                pstmt.setString(index++, newPassword);
            }
            pstmt.setInt(index, userId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("\u001B[31mError: Unable to update credentials. Please verify your input.\u001B[0m");
        }
        return false;
    }

    // Fetch user by ID
	@Override
    public User getUserById(int id) {
        String query = "SELECT * FROM User WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("role")
                );
            } else {
                System.out.println("\u001B[33mUser not found with the provided ID.\u001B[0m");
            }
        } catch (SQLException e) {
            System.err.println("\u001B[31mError: Unable to fetch user by ID. Please try again.\u001B[0m");
        }
        return null;
    }
	
	@Override
	public User getUserByEmail(String email) {
	    User user = null;
	    String query = "SELECT * FROM User WHERE email = ?";

	    try (Connection conn = DatabaseUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        // Set the email parameter in the query
	        stmt.setString(1, email);

	        // Execute the query
	        ResultSet rs = stmt.executeQuery();

	        // Check if the result set has any data
	        if (rs.next()) {
	            // Create and populate the User object
	            user = new User();
	            user.setId(rs.getInt("id"));
	            user.setName(rs.getString("name"));
	            user.setEmail(rs.getString("email"));
	            user.setPassword(rs.getString("password"));
	            user.setRole(rs.getInt("role")); // Assuming you have a role field
	        }

	    } catch (SQLException e) {
	        System.err.println("Error fetching user by email: " + e.getMessage());
	    }

	    return user;
	}

	
	@Override 
	public boolean deleteUsersBookById(int userId, int bookId) {
        String query = "DELETE FROM DOWNLOAD WHERE user_id = ? AND book_id = ?";
        boolean isDeleted = false;

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, userId);
            statement.setInt(2, bookId);
            
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return isDeleted;
    }
	
	@Override 
	public boolean deleteAllUserBooks(int userId) {
        String query = "DELETE FROM DOWNLOAD WHERE user_id = ?";
        boolean isDeleted = false;

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                isDeleted = true;
                System.out.println("All books for user ID " + userId + " have been successfully deleted.");
            } else {
                System.out.println("No books found for user ID " + userId + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDeleted;
    }
}
