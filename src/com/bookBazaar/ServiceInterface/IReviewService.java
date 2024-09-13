package com.bookBazaar.ServiceInterface;

import java.util.List;

import com.bookBazaar.entity.Review;

public interface IReviewService {
	boolean addReview(int userId, int bookId, int rating, String reviewText);
	List<Review> getAllReviews();
	List<Review> getReviewByBookId(int bookId);
}
