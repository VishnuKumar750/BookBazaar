package com.bookBazaar.entity;

import java.sql.Date;

public class Download {
	private int id;
    private int userId;
    private int bookId;
    private Date download_date;
    
    
	public Download(int id, int userId, int bookId, Date download_date) {
		this.id = id;
		this.userId = userId;
		this.bookId = bookId;
		this.download_date = download_date;
	}
	
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
	public Date getDownload_date() {
		return download_date;
	}
	public void setDownload_date(Date download_date) {
		this.download_date = download_date;
	}
    
    
}
