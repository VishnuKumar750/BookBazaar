package com.bookBazaar.service;

import java.util.List;

import com.bookBazaar.ServiceInterface.IBookService;
import com.bookBazaar.daoImp.BookDao;
import com.bookBazaar.daoInterface.IBookDao;
import com.bookBazaar.entity.Book;


public class BookService implements IBookService {
	private IBookDao bookDao;
	
	public BookService() {
		this.bookDao = new BookDao();
	}
	
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}
	
	public Book getBookbyId(int bookId) {
		return bookDao.getBookById(bookId);
	}
	
	public List<Book> getBooksByUserId(int userId) {
        return bookDao.getBooksByUserId(userId);
    }
	
	public List<Book> getBooksByCategoryId(int categoryId) {
		return bookDao.getBooksByCategoryId(categoryId);
	}
	
	public boolean addBook(String title, String author, double price, double fileSize, int category_id) {
        Book book = new Book(title, author, price, fileSize, category_id);
        return bookDao.addBook(book);
    }
	
	public boolean updateBook(int bookId, String title, String author, double price, double fileSize, int categoryId) {
        return bookDao.updateBook(bookId, title, author, price, fileSize, categoryId);
    }
	
	 public boolean deleteBook(int bookId) {
	    return bookDao.deleteBook(bookId);
	 }
	
	public boolean downloadBook(int userId, int bookId) {
		boolean success = bookDao.downloadBook(userId, bookId);
	    return success;
	}
}
