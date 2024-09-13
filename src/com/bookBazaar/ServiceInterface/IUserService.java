package com.bookBazaar.ServiceInterface;

import com.bookBazaar.entity.User;

public interface IUserService {
	User getUserById(int id);

    User login(String email, String password);

    boolean register(User user);

    void updateCredentials(int userId, String newName, String newEmail, String newPassword);
    
    boolean deleteUsersBookById(int userId, int bookId);
    
    boolean deleteAllUserBooks(int userId);

}
