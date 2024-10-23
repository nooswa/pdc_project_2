 package Logic;

/**
 * @author Larissa Goh 18029695
 * @author Noor Swadi 22167422
 * This class contains the games core mechanisms.
 * The game is started with a specified number of max guesses.
 * The game ends if the player either correctly guesses the secret word or runs out of attempts.
 */
public class GameLogic {

    private final String secretWord;
    private final InputHandler inputHandler;  
    private final HintsDisplay hintsDisplay;
    private boolean isGameWon = false;  

    // Get method to access secret word from other classes
    public String getSecretWord() { 
        return secretWord;
    }
    
    // Returns whether the game was won or 
    public boolean didWin() {
        return isGameWon;  
    }
    
    // Constructor
    public GameLogic(String secretWord, InputHandler inputHandler) {  
        this.secretWord = secretWord;
        this.inputHandler = inputHandler;
        this.hintsDisplay = new HintsDisplay();
    }
    
    // Starts the game, allowing the player to make a max number of guesses before the game ends
    public void startGame(int maxGuesses) {
        isGameWon = false; 
       System.out.println("SECRET WORD (for testing): " + secretWord);

        for (int attempt = 1; attempt <= maxGuesses; attempt++) {  
            System.out.println("Attempt " + attempt + " of " + maxGuesses);
            String guess = inputHandler.getUserInput();
           hintsDisplay.saveFeedback(guess, secretWord); 
           hintsDisplay.displayFeedbackHistory(); 

           if (guess.equals(secretWord)) {
                System.out.println("Congratulations! You've guessed the word correctly!");
                isGameWon = true; 
                break;
            }
        }

        if (!isGameWon) {  
            System.out.println("You've run out of guesses! The correct word was: " + secretWord);
        }
    }
}
