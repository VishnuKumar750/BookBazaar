package com.bookBazaar.entity;

import java.sql.Date;

public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private double fileSize;
    private int categoryId;
    private Date bookDate;
    
    public Book() {}

    public Book(String title, String author, double price, double fileSize, int categoryId) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.fileSize = fileSize;
		this.categoryId = categoryId;
	}

	// Getters and setters for all fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }
}
