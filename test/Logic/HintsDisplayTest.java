package Logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

/**
 * @author Larissa Goh 18029695
 * Unit test for HintsDisplay class.
 * 
 * This class tests whether the HintsDisplayClass generates correct feedback when comparing the user's 
 * guess to the secret word. It also ensures the feedback display of previous guesses is functioning correctly.
 */
public class HintsDisplayTest {

    private HintsDisplay hintsDisplay;

    @Before // Initialise instance before each test
    public void setUp() {
        hintsDisplay = new HintsDisplay();
    }

    /**
     * Test for exact word match (every letter in the correct position) 
     * Ensures that when the user's guess matches the secret word exactly, all letters are green.
     */
    @Test
public void testSaveFeedback_ExactMatches() {
    System.out.println("Check for exact matches:");
    
    // Defines both guessed word and secret word, same for this test.
    String guess = "apple";
    String secretWord = "apple";
    hintsDisplay.saveFeedback(guess, secretWord);

    List<String> feedbackHistory = hintsDisplay.getFeedbackHistory(); // Retrieve feedback history
    assertEquals(1, feedbackHistory.size());
    System.out.println("Feedback received: " + feedbackHistory.get(0));
    assertTrue(feedbackHistory.get(0).contains("\u001b[32ma\u001b[0m")); // verifies that all letter are green.
}

    /**
      * Test for mismatched letters (letter in word but in incorrect position)
      * Ensures that when a letter from the guessed word is in the secret word but in a different position, it's 
      * marked as yellow.
      */
@Test
public void testSaveFeedback_MisplacedMatches() {
    System.out.println("Check for misplaced letter matches:");
    
    // Defines both guessed and secret word. Letter 'p' matches but is in a different position.
    String guess = "pinks";
    String secretWord = "apple";
    hintsDisplay.saveFeedback(guess, secretWord);

    List<String> feedbackHistory = hintsDisplay.getFeedbackHistory();
    assertEquals(1, feedbackHistory.size());
    System.out.println("Feedback received: " + feedbackHistory.get(0));
    assertTrue("Expected yellow 'p' for misplaced match", feedbackHistory.get(0).contains("\u001b[33mp\u001b[0m"));
}

    /**
     * Test for 0 letter matches (no letters in the word)
     * Ensures that if the guessed word has no matching letters, all letters are marked as incorrect (gray)
     */
@Test
public void testSaveFeedback_NoMatches() {
    System.out.println("Check for no matches:");
    
    // Defines both guessed and secret word with no matches
    String guess = "round";
    String secretWord = "apple";
    hintsDisplay.saveFeedback(guess, secretWord);

    List<String> feedbackHistory = hintsDisplay.getFeedbackHistory();
    assertEquals(1, feedbackHistory.size());
    System.out.println("Feedback received: " + feedbackHistory.get(0));
    assertFalse(feedbackHistory.get(0).contains("\u001b[32mb\u001b[0m")); 
}

    /**
     * Tests feedback display is displaying previous guesses correctly
     */
@Test
public void testDisplayFeedbackHistory() {
    System.out.println("Display feedback history test");
    
    // Storing 4 guesses to feedback
    hintsDisplay.saveFeedback("apple", "apple");
    hintsDisplay.saveFeedback("plead", "apple");
    hintsDisplay.saveFeedback("pinks", "apple");  
    hintsDisplay.saveFeedback("crave", "apple");  

    List<String> feedbackHistory = hintsDisplay.getFeedbackHistory();
    assertEquals(4, feedbackHistory.size()); // show 4 recent guesses

    System.out.println("Feedback history:");
    for (String feedback : feedbackHistory) {
        System.out.println(feedback);
    }
}

}
