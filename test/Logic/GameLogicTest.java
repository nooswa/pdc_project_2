package Logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Larissa Goh 18029695
 * Unit test for GameLogic class.
 * This class tests that the GameLogic class functions correctly by testing when a game is won or lost.
 */
public class GameLogicTest {

    private GameLogic gameLogic;
    private TestInputHandler inputHandler;
    private final String secretWord = "apple";  // Secret word for testing
    private Rules rules;  // For  InputHandler
    private ValidGuessList validGuessList;  // For InputHandler

    /**
     * InputHandler is needed to simulate user input to test.
     * During a test run, TestInputHandler will return inputs one by one, imitating how a user would make guesses.
     */
    private class TestInputHandler extends InputHandler {
        private String[] inputs; 
        private int index;

        public TestInputHandler(Rules rules, ValidGuessList validGuessList, String... inputs) {
            super(rules, validGuessList); // Provide necessary arguments to the superclass constructor
            this.inputs = inputs;
            this.index = 0;
        }

        @Override
        public String getUserInput() {
            if (index < inputs.length) {
                return inputs[index++];
            }
            return ""; // Empty string when there are no inputs left
        }
    }

    /**
     * Sets up the test environment by creating a GameLogic instance 
     * with a custom TestInputHandler to simulate user input.
     */
    @Before
    public void setUp() {
        // Initialise rules and validguesslist
        Rules rules =  Rules.getInstance();
        validGuessList = new ValidGuessList();  
        inputHandler = new TestInputHandler(rules, validGuessList);
        gameLogic = new GameLogic(secretWord, inputHandler);
    }

    /**
     * Test to ensure that a game win is correctly identified,
     */
    @Test
    public void testGame_Won() {
        // Inputhandler to test when word is guessed on the first round
        inputHandler = new TestInputHandler(rules, validGuessList, "apple");
        gameLogic = new GameLogic(secretWord, inputHandler);

        // Start the game 
        gameLogic.startGame(5);
        assertTrue("Expected the game to be won", gameLogic.didWin()); // Confirm game win
    }

    /**
     * Test to ensure that a game loss is correctly identified when a user runs out of guesses
     */
    @Test
    public void testGame_Lost() {
        // Inputhandler to test 5 incorrect guesses
        inputHandler = new TestInputHandler(rules, validGuessList, "wrong", "wrong", "wrong", "wrong", "wrong");
        gameLogic = new GameLogic(secretWord, inputHandler);

        // Start the game 
        gameLogic.startGame(5);
        assertFalse("Expected the game to be lost", gameLogic.didWin()); // Confirm game loss
    }

    
}
