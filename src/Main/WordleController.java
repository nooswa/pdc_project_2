/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Logic.GameLogic;
import Logic.InputHandler;
import Logic.Rules;
import Logic.Score;
import Logic.ValidGuessList;
import Logic.WordList;

<<<<<<< HEAD


=======
>>>>>>> 53e09875a0bd4473980a305cbbae13d265ef2cd2
/**
 *
 * @author noooo
 * Call all necessary classes here to invoke in main 
 */
public class WordleController {


    private String secretWord;

    
    public WordleController(){
        WordList wordList = new WordList(); // WordList Instance: contains list of words
        wordList.initialiseSecretWord();
        ValidGuessList validGuessList = new ValidGuessList(); // ValidGuessList Instance: Checks whether the player's guess is valid

        Rules rules =  Rules.getInstance();  // Rules instance: to print game rules
        InputHandler inputProcessor = new InputHandler(rules, validGuessList);  // InputHandler Instance: to receive user input
        GameLogic game = new GameLogic(secretWord, inputProcessor);  // GameLogic Instance: to load core game mechanics
        Score score = Score.getInstance(); // Score instance: to manage and track stats   

    }
    
    
    // This method initializes and starts a new game
    public void startNewGame() {
        System.out.println("Welcome to Wordle!\n");

        WordList wordList = new WordList(); // WordList Instance: contains list of words
        wordList.initialiseSecretWord();  // Load and select the secret word / the answer from list of words

        ValidGuessList validGuessList = new ValidGuessList(); // ValidGuessList Instance: Checks whether the player's guess is valid


        Rules rules =  Rules.getInstance();  // Rules instance: to print game rules

        rules.displayRulesFirstRun();  // Display the rules once at the start of the game

        InputHandler inputProcessor = new InputHandler(rules, validGuessList);  // InputHandler Instance: to receive user input

        String secretWord = wordList.getSecretWord(); // Get the secret word / answer from the WordList

        GameLogic game = new GameLogic(secretWord, inputProcessor);  // GameLogic Instance: to load core game mechanics

        game.startGame(6);  // Start the game with a 6 attempts

        Score score = Score.getInstance(); // Score instance: to manage and track stats

        score.recordGame(game); // Update stats after the game ends
    }

    public static void main(String[] args) {
        Wordle wordle = new Wordle();
        wordle.startNewGame(); // Start the game from here
    }
}

