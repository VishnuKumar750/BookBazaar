package com.bookBazaar.utility;

import java.util.Scanner;

public class InputUtil {
	private static final Scanner scanner = new Scanner(System.in);

    public static int getUserInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextInt();
    }

    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }
}
