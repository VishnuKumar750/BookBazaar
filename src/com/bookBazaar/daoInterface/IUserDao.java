package com.bookBazaar.daoInterface;

import com.bookBazaar.entity.User;

public interface IUserDao {
	User getUserByEmailAndPassword(String email, String password);

	boolean register(User user);

    boolean updateCredentials(int userId, String newName, String newEmail, String newPassword);

    User getUserById(int id);
    
    boolean deleteUsersBookById(int userId, int bookId);
    
    boolean deleteAllUserBooks(int userId);
    
    User getUserByEmail(String email);
}
