package com.bookBazaar.entity;

import java.util.Date;

public class Review {
    private int id;
    private int userId;
    private int bookId;
    private int rating;
    private String reviewText;
    private Date date;
    
    public Review() {
    	
    }

    // Constructor
    public Review(int id, int userId, int bookId, int rating, String reviewText) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    
    public int getRating() {
    	return rating;
    }
    
    public void setRating(int rating) {
    	this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", reviewText='" + reviewText + '\'' +
                ", date=" + date +
                '}';
    }
}
