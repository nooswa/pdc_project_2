package Logic;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Noor Swadi 22167422
 * This class reads a file with all possible Wordle answers and selects a random word 
 * to be the secret word for the user to guess.
 */

public class WordList {

    private String secretWord;  
    
    //Getter method for secretWord to be accessed by other classes.
    public String getSecretWord() {
        return secretWord;
    }

    //Method to select random word from a file of possible answers. 
    public void selectRandomWord() {
        BufferedReader br = null;
        ArrayList<String> words = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader("./resources/Word_List.txt"));
            String line;
            while ((line = br.readLine()) != null) { 
                 //** As there are multiple  words in each line in the file, this reads and splits words based on spaces.
                String[] arrOfStr = line.split(" ");
                words.addAll(Arrays.asList(arrOfStr)); 
            }

            Random random = new Random();
            if (!words.isEmpty()) {
                secretWord = words.get(random.nextInt(words.size()));
            } else {
                Logger.getLogger(WordList.class.getName()).log(Level.SEVERE, "The word list is empty.");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordList.class.getName()).log(Level.SEVERE, "File not found.", ex);
        } catch (IOException ex) {
            Logger.getLogger(WordList.class.getName()).log(Level.SEVERE, "Failed to read the file.", ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(WordList.class.getName()).log(Level.SEVERE, "Failed to close buffer.", ex);
                }
            }
        }
    }
    // Method to initialise and select a random word.
    public void initialiseSecretWord() {
     selectRandomWord(); // Sets secret  word.
    }
    
    public void setSecretWord(String word) {
    this.secretWord = word;
}
}

    
  



 
        
    


