package com.bookBazaar.ServiceInterface;

import java.util.List;

import com.bookBazaar.entity.Book;

public interface IBookService {

	List<Book> getAllBooks();
	Book getBookbyId(int bookId);
	List<Book> getBooksByUserId(int userId);
	List<Book> getBooksByCategoryId(int categoryId);
	boolean addBook(String title, String author, double price, double fileSize, int category_id);
	boolean updateBook(int bookId, String title, String author, double price, double fileSize, int categoryId);
	boolean deleteBook(int bookId);
	boolean downloadBook(int userId, int bookId);
}
