package com.bookBazaar.daoInterface;

import java.util.List;

import com.bookBazaar.entity.Book;

public interface IBookDao {
	List<Book> getAllBooks();
	List<Book> getBooksByCategoryId(int categoryId);
	boolean addBook(Book book);
	Book getBookById(int bookId);
	boolean deleteBook(int bookId);
	boolean updateBook(int bookId, String title, String author, double price, double fileSize, int categoryId);
	List<Book> getBooksByUserId(int userId);
	boolean downloadBook(int userId, int bookId);
}
