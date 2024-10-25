/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author noooo
 */

public class WordsDB extends GameDB {
    private final List<String> validWords;  // List to store valid guess words.
    private String secretWord;  // The secret word for the game
    private Statement statement; // Declare statement variable

    public WordsDB() {
        super(); // Call the constructor of GameDB
        validWords = new ArrayList<>(); // Initialize the validWords list
        try {
            this.statement = getConn().createStatement(); // Initialize the statement
            setupLists(); // Set up the lists when initializing WordsDB
        } catch (SQLException e) {
            Logger.getLogger(WordsDB.class.getName()).log(Level.SEVERE, "Error creating statement", e);
        }
    }

    protected void setupLists() {
        try {
            createValidGuessListTable();
            createWordListTable();
            loadWordsFromFiles(); // Load valid words and words from files
            selectRandomWord(); // Select a secret word after loading
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
    }

    protected void createValidGuessListTable() throws SQLException {
        createTableIfNotExists("ValidGuessList");
    }

    protected void createWordListTable() throws SQLException {
        createTableIfNotExists("WordList");
    }

    // Generic method to create a table if it doesn't exist
    protected void createTableIfNotExists(String tableName) throws SQLException {
        if (!checkTableExisting(tableName)) {
            statement.executeUpdate("CREATE TABLE " + tableName + " (WORD VARCHAR(255))");
            System.out.println(tableName + " table was created.");
        }
    }

    protected boolean checkTableExisting(String tableName) {
        return super.checkTableExisting(tableName); // Use the method from GameDB
    }

    // Load valid guesses and words from their respective files.
    protected void loadWordsFromFiles() {
        loadWordsFromFile("./resources/Valid_Guess_List.txt", "ValidGuessList", true);
        loadWordsFromFile("./resources/Word_List.txt", "WordList", false);
    }

    // Generic method to load words from a file into the specified database table.
    protected void loadWordsFromFile(String filePath, String tableName, boolean isValidGuessList) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (isValidGuessList) {
                    addWordToDatabase(line.trim().toUpperCase(), tableName);
                } else {
                    addMultipleWordsToDatabase(line.trim().split("\\s+"), tableName);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(WordsDB.class.getName()).log(Level.SEVERE, "Failed to read the file: " + filePath, ex);
        } catch (SQLException ex) {
            Logger.getLogger(WordsDB.class.getName()).log(Level.SEVERE, "SQL error while loading words into " + tableName, ex);
        }
    }

    // Add a single word to the database.
    protected void addWordToDatabase(String word, String tableName) throws SQLException {
        validWords.add(word); // Store in validWords list
        statement.executeUpdate("INSERT INTO " + tableName + " (WORD) VALUES ('" + word + "')");
    }

    // Add multiple words to the database.
    protected void addMultipleWordsToDatabase(String[] words, String tableName) {
        for (String word : words) {
            try {
                addWordToDatabase(word.toUpperCase(), tableName);
            } catch (SQLException ex) {
                Logger.getLogger(WordsDB.class.getName()).log(Level.SEVERE, "Failed to insert word: " + word, ex);
            }
        }
    }

    // Select a random word from the word list.
    protected void selectRandomWord() {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./resources/Word_List.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String word : line.trim().split("\\s+")) {
                    words.add(word.toUpperCase());
                }
            }
            if (!words.isEmpty()) {
                secretWord = words.get(new Random().nextInt(words.size()));
            } else {
                Logger.getLogger(WordsDB.class.getName()).log(Level.SEVERE, "The word list is empty.");
            }
        } catch (IOException ex) {
            Logger.getLogger(WordsDB.class.getName()).log(Level.SEVERE, "Failed to read the word list file.", ex);
        }
    }

    // Getter method for valid words.
    public List<String> getValidWords() {
        return validWords;  
    }

    // Getter method for secret word.
    public String getSecretWord() {
        return secretWord;
    }
}
