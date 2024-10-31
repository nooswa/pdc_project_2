/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.UI;

import GUI.model.SignUpHandler;
import DataBase.PlayerDB;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 
 * SignUpFrame provides a sign-up interface for new users.
 * It extends JFrame and provides text fields for user details, a sign-up button,
 * and a button to navigate back to the SignIn screen.
 * @author Larissa Goh 18029695
 */
public class SignUpFrame extends JFrame {

    private final PlayerDB playerDB;
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField passwordField;
    private JButton signUpButton;
    private JButton existingAccountSignInButton;

    public SignUpFrame() {
        playerDB = new PlayerDB();
        setTitle("Sign Up");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setLayout(new GridBagLayout());
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        // Main welcome label at the top
        JLabel welcomeLabel = new JLabel("Welcome to Wordle!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(welcomeLabel, gbc);

        gbc.gridy++;
        JLabel signUpLabel = new JLabel("Sign Up or Sign In", JLabel.CENTER);
        signUpLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        add(signUpLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel fullNameLabel = new JLabel("Full Name");
        fullNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(fullNameLabel, gbc);

        gbc.gridx = 1;
        fullNameField = new JTextField(15);
        add(fullNameField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(emailLabel, gbc);

        gbc.gridx = 1;
        emailField = new JTextField(15);
        add(emailField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        passwordField = new JTextField(15);
        add(passwordField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        // Sign up button
        signUpButton = new JButton("Sign Up");
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                handleSignUp();
            }
        });
        add(signUpButton, gbc);

        gbc.gridy++;

        // Existing account sign-in button
        existingAccountSignInButton = new JButton("Already signed up? Sign in here");
        existingAccountSignInButton.setBorderPainted(false);
        existingAccountSignInButton.setContentAreaFilled(false);
        existingAccountSignInButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        existingAccountSignInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Start.showSignInFrame(); // Navigate to SignInFrame
            }
        });
        add(existingAccountSignInButton, gbc);
    }

    private void handleSignUp() {
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        // Use SignUpHandler to validate inputs
        SignUpHandler signUpHandler = new SignUpHandler(fullName, email, password);
        if (!signUpHandler.validateFullName()) {
            JOptionPane.showMessageDialog(this, "Please enter your first and last name.");
            return;
        }
        if (!signUpHandler.validateEmail()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.");
            return;
        }
        if (!signUpHandler.validatePassword()) {
            JOptionPane.showMessageDialog(this, "Password must be at least 6 characters long.");
            return;
        }

        // Register the user
        boolean success = playerDB.registerUser(fullName, email, password);
        if (success) {
            JOptionPane.showMessageDialog(this, "Account successfully created!");
            Start.showSignInFrame(); // Transition to SignInFrame on success
        } else {
            JOptionPane.showMessageDialog(this, "User already exists or registration failed.");
        }
    }
}


