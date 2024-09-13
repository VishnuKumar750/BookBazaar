package com.bookBazaar.service;

import java.util.List;

import com.bookBazaar.ServiceInterface.IReviewService;
import com.bookBazaar.daoImp.ReviewDao;
import com.bookBazaar.daoInterface.IReviewDao;
import com.bookBazaar.entity.Review;

public class ReviewService implements IReviewService {
	private IReviewDao reviewDao;
	
	public ReviewService() {
		this.reviewDao = new ReviewDao();
	}

	public boolean addReview(int userId, int bookId, int rating, String reviewText) {
        return reviewDao.addReview(userId, bookId, rating,  reviewText);
    }
	
	public List<Review> getAllReviews() {
		return reviewDao.getAllReview();
	}

	@Override
	public List<Review> getReviewByBookId(int bookId) {
		List<Review> reviews = reviewDao.getReviewsByBookId(bookId);
		
		return reviews;
	}
	
//	private static void getReviewsByBookId(int bookId) {
//	    List<Review> reviews = reviewDao.getReviewsByBookId(bookId);
//	    if (reviews.isEmpty()) {
//	        System.out.println("No reviews for this book.");
//	    } else {
//	        System.out.println("Reviews:");
//	        for (Review review : reviews) {
//	            System.out.println("User ID: " + review.getUserId());
//	            System.out.println("Rating: " + review.getRating());
//	            System.out.println("Review: " + review.getReviewText());
//	            System.out.println("Date: " + review.getDate());
//	            System.out.println("-----------------------------");
//	        }
//	    }
//	}

}
