/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

/**
 *
 * @author Noor Swadi 22167422
 * TEMPORARY CLASS FOR TESTING
 */
import java.util.List;

public class DBTest {
    public static void main(String[] args) {
        // Create an instance of WordsDB
        WordsDB wordsDB = new WordsDB();

        // Test the database connection (ensure it's working)
        System.out.println("Testing database connection...");

        // Retrieve valid words from the database
        List<String> validWords = wordsDB.getValidWords();
        System.out.println("Valid Words Loaded: " + validWords);

        // Test selecting a secret word
        String secretWord = wordsDB.getSecretWord();
        System.out.println("Secret Word Selected: " + secretWord);

        // Display the contents of the valid guess list
        System.out.println("Contents of Valid Guess List:");
        for (String word : validWords) {
            System.out.println(word);
        }

        // Optionally, you could call other methods to test various functionalities
        // For example, if you want to add words to the database, you can create a method for that
    }
}
