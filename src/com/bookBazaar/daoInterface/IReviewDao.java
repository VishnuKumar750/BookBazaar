package com.bookBazaar.daoInterface;

import java.util.List;

import com.bookBazaar.entity.Review;

public interface IReviewDao {
	boolean addReview(int userId, int bookId, int rating, String reviewText);
	List<Review> getReviewsByBookId(int bookId);
	List<Review> getAllReview();
}
