package Main;

import Logic.WordList;
import Logic.Score;
import Logic.InputHandler;
import Logic.ValidGuessList;
import Logic.GameLogic;
import Logic.Rules;

/**
 * @author Larissa Goh 18029695
 * @author Noor Swadi 22167422
 * Class containing the main method.
 */
public class Wordle {

    // This method initializes and starts a new game
    public void startNewGame() {
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
    }

    public static void main(String[] args) {
        Wordle wordle = new Wordle();
        wordle.startNewGame(); // Start the game from here
    }
}

   
