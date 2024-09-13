package com.bookBazaar.daoImp;

import java.sql.Connection;


import com.bookBazaar.utility.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookBazaar.daoInterface.ICategoryDao;
import com.bookBazaar.entity.Category;

public class CategoryDao implements ICategoryDao {

    // Fetch all categories from the database
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM Category";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving categories: " + e.getMessage());
        }
        
        return categories;
    }
    

    // Add a new category to the database
    public boolean addCategory(Category category) {
        // Check if the category already exists
        if (isCategoryExists(category.getName())) {
            System.out.println("Category already exists. Cannot add duplicate.");
            return false;  // Return false if the category already exists
        }

        String sql = "INSERT INTO Category (name) VALUES (?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, category.getName());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error adding category: " + e.getMessage());
        }
        return false;
    }

    public boolean isCategoryExists(String categoryName) {
        String sql = "SELECT COUNT(*) FROM Category WHERE name = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, categoryName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;  // Return true if category exists
            }
        } catch (SQLException e) {
            System.out.println("Error checking category existence: " + e.getMessage());
        }

        return false;  // Return false if there's any issue or no category exists
    }
    
    // Get a category by its ID
    public Category getCategoryById(int categoryId) {
        Category category = null;
        String query = "SELECT * FROM Category WHERE id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving category by ID: " + e.getMessage());
        }
        
        return category;
    }

    // Delete a category by its ID
//    public boolean deleteCategory(int categoryId) {
//        String sql = "DELETE FROM Category WHERE id = ?";
//        try (Connection conn = DatabaseUtil.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//             
//            pstmt.setInt(1, categoryId);
//            int rowsAffected = pstmt.executeUpdate();
//            return rowsAffected > 0;
//            
//        } catch (SQLException e) {
//            System.out.println("Error deleting category: " + e.getMessage());
//        }
//        return false;
//    }

    // Update an existing category in the database
    public boolean updateCategory(int categoryId, String name) {
        String sql = "UPDATE Category SET name = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, name);
            pstmt.setInt(2, categoryId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro");
        }
        return false;
    }
}
