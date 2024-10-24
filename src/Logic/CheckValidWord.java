package Logic;

import Logic.CheckUserInput;

/**
 * @author Larissa Goh 18029695
 * This class is a subclass of CheckUserInput and checks if a word is in the list of valid guesses.
 * It validates the word based on the valid words provided by the ValidGuessList.
 */
public class CheckValidWord extends CheckUserInput {
    
    private final ValidGuessList validGuessList; 

    public CheckValidWord(ValidGuessList validGuessList) {
        this.validGuessList =  validGuessList; 
    }
    
    @Override
    public boolean check(String word) {
        // Validate the word by checking if it's in the list of valid words
        boolean isValid = validGuessList.getValidWords().contains(word);
        
        // Prints response if word is not valid. 
        if (!isValid) {
            printResponse();
        }
        
        return isValid;
    }
}

