/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

/**
 *
 * @author noooo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {

    public static void main(String[] args) {
        // Connect to the database and create a statement
        try {
            // Get your database connection here (update with your connection details)
            Connection connection = DriverManager.getConnection("jdbc:derby:wordleDB;create=true", "wordle2", "wordle2");
            Statement statement = connection.createStatement(); // Fixed statement creation

            // Create an instance of WordleLists with the statement
            WordleLists wordleLists = new WordleLists(statement);
            
            // Setup the lists (this includes table creation)
            wordleLists.setupLists();
            
            // Load words from files (make this method public)
            wordleLists.loadWordsFromFiles(); 
            
            // Test selecting a random word
            String secretWord = wordleLists.getSecretWord();
            if (secretWord != null) {
                System.out.println("Secret word selected: " + secretWord);
            } else {
                System.out.println("Error: No secret word selected.");
            }

            // Display valid words loaded
            System.out.println("Valid words loaded: " + wordleLists.getValidWords().size());
            for (String word : wordleLists.getValidWords()) {
                System.out.println(word);
            } // Close for loop
            
            // Close statement and connection
            statement.close();
            connection.close(); // Ensure to close the connection

        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}

