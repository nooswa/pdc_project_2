/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.model;

import java.util.regex.Pattern;
/**
 * @author Larissa Goh 18029695
 * This class handles all logic for signing up. including validating user inputs
 * it validates user inputs for to create an account.
 * such as full name, email, and password before registration. */

public class SignUpHandler {

    private String fullName;
    private String email;
    private String password;

    public SignUpHandler(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public boolean validateFullName() { // Checks that full name has at least 2 parts for first and last name
        String trimmedName = fullName.trim();
        return trimmedName.contains(" ") && trimmedName.split(" ").length >= 2;
    }

    public boolean validateEmail() {    // Regex pattern for validating email address creation
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(emailRegex, email.trim());
    }

    public boolean validatePassword() { 
        return password != null && password.length() >= 6; // At least 6 characters
    }

    // Validate all fields
    public boolean validateAll() {
        return validateFullName() && validateEmail() && validatePassword();
    }
}
