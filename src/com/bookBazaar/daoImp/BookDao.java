package com.bookBazaar.daoImp;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookBazaar.utility.*;
import com.bookBazaar.entity.Book;
import com.bookBazaar.daoInterface.IBookDao;

public class BookDao implements IBookDao {
	@Override
    public List<Book> getBooksByCategoryId(int categoryId) {
    	List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM Book WHERE category_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getDouble("price"));
                book.setFileSize(rs.getDouble("fileSize"));
                book.setCategoryId(rs.getInt("category_id"));
                books.add(book);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving books by category: " + e.getMessage());
        }

        return books;
    }

	@Override
    public Book getBookById(int bookId) {
        Book book = null;
        String query = "SELECT * FROM Book WHERE id = ?";
        
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                double price = resultSet.getDouble("price");
                double fileSize = resultSet.getDouble("fileSize");
                int categoryId = resultSet.getInt("category_id");
                
                book = new Book(title, author, price, fileSize, categoryId);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving book by ID: " + e.getMessage());
        }
        
        return book;
    }

	
	
	@Override
    public boolean updateBook(int bookId, String title, String author, double price, double fileSize, int categoryId) {
        String query = "UPDATE Book SET title = ?, author = ?, price = ?, fileSize = ?, category_id = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, title);
            statement.setString(2, author);
            statement.setDouble(3, price);
            statement.setDouble(4, fileSize);
            statement.setInt(5, categoryId);
            statement.setInt(6, bookId);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0; // Return true if at least one row was updated
        } catch (SQLException e) {
            System.out.println("Error updating the book: " + e.getMessage());
        }
        return false;
    }

	@Override
    public List<Book> getBooksByUserId(int userId) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.id, b.title, b.author, b.price, b.fileSize " +
                     "FROM Book b " +
                     "JOIN Download d ON b.id = d.book_id " +
                     "WHERE d.user_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getDouble("price"));
                book.setFileSize(rs.getDouble("fileSize"));
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving books for user: " + e.getMessage());
        }

        return books;
    }

	@Override
	public boolean downloadBook(int userId, int bookId) {
	    // Check if the user has already downloaded the book
	    if (isBookAlreadyDownloaded(userId, bookId)) {
	        System.out.println("You have already downloaded this book.");
	        return false;
	    }

	    String sql = "INSERT INTO Download (user_id, book_id, download_date) VALUES (?, ?, NOW())";
	    try (Connection conn = DatabaseUtil.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setInt(1, userId);
	        pstmt.setInt(2, bookId);

	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0; // Return true if the download was successful

	    } catch (SQLException e) {
	        System.out.println("Error downloading the book: " + e.getMessage());
	    }
	    return false;
	}
	
	private boolean isBookAlreadyDownloaded(int userId, int bookId) {
	    String query = "SELECT * FROM Download WHERE user_id = ? AND book_id = ?";
	    try (Connection conn = DatabaseUtil.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query)) {

	        pstmt.setInt(1, userId);
	        pstmt.setInt(2, bookId);
	        ResultSet rs = pstmt.executeQuery();

	        return rs.next(); // If a record exists, the book is already downloaded

	    } catch (SQLException e) {
	        System.out.println("Error checking download status: " + e.getMessage());
	    }
	    return false;
	}
	
	    // Add Book
	    public boolean addBook(Book book) {
	        String query = "INSERT INTO Book (title, author, price, fileSize, category_id) VALUES (?, ?, ?, ?, ?)";
	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setString(1, book.getTitle());
	            stmt.setString(2, book.getAuthor());
	            stmt.setDouble(3, book.getPrice());
	            stmt.setDouble(4, book.getFileSize());
	            stmt.setInt(5, book.getCategoryId());

	            int rowsAffected = stmt.executeUpdate();
	            return rowsAffected > 0;

	        } catch (SQLException e) {
	            System.out.println("Error adding book: " + e.getMessage());
	        }
	        return false;
	    }

	    // Update Book
	    public boolean updateBook(Book book) {
	        String query = "UPDATE Book SET title = ?, author = ?, price = ?, fileSize = ?, category_id = ? WHERE id = ?";
	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setString(1, book.getTitle());
	            stmt.setString(2, book.getAuthor());
	            stmt.setDouble(3, book.getPrice());
	            stmt.setDouble(4, book.getFileSize());
	            stmt.setInt(5, book.getCategoryId());
	            stmt.setInt(6, book.getId());

	            int rowsAffected = stmt.executeUpdate();
	            return rowsAffected > 0;

	        } catch (SQLException e) {
	            System.out.println("Error updating book: " + e.getMessage());
	        }
	        return false;
	    }

	    // View All Books
	    public List<Book> getAllBooks() {
	        List<Book> books = new ArrayList<>();
	        String query = "SELECT * FROM Book";
	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                Book book = new Book();
	                 book.setId(rs.getInt("id"));
	                 book.setTitle(rs.getString("title"));
	                 book.setAuthor(rs.getString("author"));
	                 book.setPrice(rs.getDouble("price"));
	                 book.setFileSize(rs.getDouble("fileSize"));
	                 book.setCategoryId(rs.getInt("category_id"));
	              
	                books.add(book);
	            }

	        } catch (SQLException e) {
	            System.out.println("Error fetching books: " + e.getMessage());
	        }
	        return books;
	    }

	    // Delete Book
	    public boolean deleteBook(int bookId) {
	        String query = "DELETE FROM Book WHERE id = ?";
	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, bookId);
	            int rowsAffected = stmt.executeUpdate();
	            return rowsAffected > 0;

	        } catch (SQLException e) {
	            System.out.println("Error deleting book: " + e.getMessage());
	        }
	        return false;
	    }
}
