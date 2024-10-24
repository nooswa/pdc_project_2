package Logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class reads a file of all possible 5-letter words in order to 
 * verify that the user's guess is a valid word.
 */
public class ValidGuessList {

    private final ArrayList<String> validWords;  // List to store valid guess words.
    private final Random random = new Random();  // Random object to fetch random words

    // Constructor to initialise and load list of valid words.
    public ValidGuessList() { 
        validWords = new ArrayList<>();  
        loadWords();  
    }
    
    // Loads valid words from a text file.
    private void loadWords() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("./resources/Valid_Guess_List.txt"));
            String line;
            while ((line = br.readLine()) != null) { // Since each word is on a line, reads each line from the file.
                validWords.add(line.trim().toUpperCase()); // Adds each word to the list in uppercase.
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValidGuessList.class.getName()).log(Level.SEVERE, "File not found.", ex);
        } catch (IOException ex) {
            Logger.getLogger(ValidGuessList.class.getName()).log(Level.SEVERE, "Failed to read the file.", ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(ValidGuessList.class.getName()).log(Level.SEVERE, "Failed to close the BufferedReader.", ex);
                }
            }
        }
    }
    
    // Method to return a random word from the list
    public String getRandomWord() {
        if (validWords.isEmpty()) {
            return ""; // or handle this case differently if needed
        }
        int randomIndex = random.nextInt(validWords.size());
        return validWords.get(randomIndex);
    }

    // Getter method for getValidWords to be accessed in other classes.
    public List<String> getValidWords() {
        return validWords;  
    }
}
