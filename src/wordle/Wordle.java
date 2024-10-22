package wordle;
/**
 * @author Larissa Goh 18029695
 * @author Noor Swadi 22167422
 * Class containing the main method.
 */
public class Wordle {
    public static void main(String[] args) {
        boolean playAgain; 
/*TEST COMMIT*/
       do{ 
        System.out.println("Welcome to Wordle!\n");

        WordList wordList = new WordList(); // WordList Instance: contains list of words
        wordList.initialiseSecretWord();  // Load and select the secret word / the answer from list of words

        ValidGuessList validGuessList = new ValidGuessList(); // ValidGuessList Instance: Checks whether the player's guess is valid


        Rules rules = new Rules();  // Rules instance: to print game rules
        rules.displayRulesFirstRun();  // Display the rules once at the start of the game


        InputHandler inputProcessor = new InputHandler(rules, validGuessList);  // InputHandler Instance: to receive user input 


        String secretWord = wordList.getSecretWord(); // Get the secret word / answer from the WordList


        GameLogic game = new GameLogic(secretWord, inputProcessor);  // GameLogic Instance: to load core game mechanics


        game.startGame(6);  // Start the game with a 6 attempts


        Score score = new Score(); // Score instance: to manage and track stats


        score.recordGame(game); // Update stats after the game ends
        
        
        PlayAgain playAgainInstance = new PlayAgain(); // Initialise PlayAgain Instance
        playAgain = playAgainInstance.askToPlayAgain(); // Check if the Player wants to play again and loop if true
       } while(playAgain);
    }
    
    
}
