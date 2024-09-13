package com.bookBazaar.ExcpetionHandler;

import java.sql.SQLException;

public class ExceptionHandler {

    // Singleton instance
    private static ExceptionHandler instance;

    // Private constructor to prevent instantiation
    private ExceptionHandler() {
    }

    // Global access point for the singleton instance
    public static ExceptionHandler getInstance() {
        if (instance == null) {
            synchronized (ExceptionHandler.class) {
                if (instance == null) {
                    instance = new ExceptionHandler();
                }
            }
        }
        return instance;
    }

    // Method to handle exceptions
    public void handleException(Exception e, String context) {
        logException(e, context);
        displayFriendlyErrorMessage(e);
    }

    // Log exception details to a file or console
    private void logException(Exception e, String context) {
        // You can extend this to log into a file or external logging system
        System.out.println("Exception occurred in: " + context);
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();  // To print stack trace for debugging
    }

    // Display a user-friendly error message
    private void displayFriendlyErrorMessage(Exception e) {
        if (e instanceof SQLException) {
            System.out.println("A database error occurred. Please try again later.");
        } else if (e instanceof IllegalArgumentException) {
            System.out.println("Invalid argument provided. Please check the input.");
        } else if (e instanceof NullPointerException) {
            System.out.println("Unexpected error occurred. Missing data encountered.");
        } else {
            System.out.println("An unexpected error occurred. Please try again.");
        }
    }

    // Example for throwing custom exceptions
//    public void handleCustomException(CustomAppException e, String context) {
//        logException(e, context);
//        System.out.println("Custom Error: " + e.getCustomMessage());
//    }
}
