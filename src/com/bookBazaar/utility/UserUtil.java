package com.bookBazaar.utility;

import com.bookBazaar.entity.User;
import com.bookBazaar.service.UserService;

public class UserUtil {
	 private static final UserService userService = new UserService();

    public static User login(String email, String password) {
        return userService.login(email, password);
    }

    public static boolean register(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        
    	return userService.Register(user);
    }
}
