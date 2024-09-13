package com.bookBazaar.daoImp;

import java.sql.Connection;

import com.bookBazaar.utility.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookBazaar.daoInterface.IReviewDao;
import com.bookBazaar.entity.Review;

public class ReviewDao implements IReviewDao {
	public boolean addReview(int userId, int bookId, int rating, String reviewText) {
        String sql = "INSERT INTO review (user_id, book_id, rating, comment, review_date) VALUES (?, ?, ?, ?, NOW())";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, bookId);
            statement.setInt(3, rating);
            statement.setString(4, reviewText);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
        	 System.err.println("\u001B[31mError: " + e.getMessage() + "\u001B[0m");
            return false;
        }
    }
	
	public List<Review> getAllReview() {
		List<Review> reviews = new ArrayList<>();
		String query = "SELECT * FROM REVIEW";
		
		try(Connection conn = DatabaseUtil.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery()) {
			
			while(rs.next()) {
				Review review = new Review(rs.getInt("id"), rs.getInt("user_Id"), rs.getInt("book_Id"), rs.getInt("rating"), rs.getString("comment"));
				reviews.add(review);
			}
			
		} catch(Exception e) {
			
		}
		return reviews;
	}
	
	public List<Review> getReviewsByBookId(int bookId) {
        List<Review> reviews = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection()) {
        	String query = "SELECT * FROM REVIEW WHERE book_id = ?";
        	PreparedStatement stmt = connection.prepareStatement(query);
        	stmt.setInt(1, bookId);
             
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Review review = new Review();
                review.setId(resultSet.getInt("id"));
                review.setUserId(resultSet.getInt("user_id"));
                review.setBookId(resultSet.getInt("book_id"));
                review.setRating(resultSet.getInt("rating"));
                review.setReviewText(resultSet.getString("comment"));
                reviews.add(review);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }
}
