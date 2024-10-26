package Logic;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Larissa Goh 18029695
 * Unit test for CheckValidWord class.
 * This test class ensures that words from the ValidGuessList.txt are correctly identified as valid with the check
 * method.
 */
public class CheckValidWordTest {
    
    private static CheckValidWord instance;
    private static ValidGuessList validGuessList;
    private static String randomWord; 

    @BeforeClass  // Sets up shared resources for tests
    public static void setUpClass() {
        validGuessList = new ValidGuessList();
        instance = new CheckValidWord(validGuessList);
        randomWord = validGuessList.getRandomWord();
    }

    /**
     * Test of check method with a valid word. Ensures that the words in ValidGuessList.txt are valid 
     */
    @Test
    public void testCheck_ValidRandomWord() {
        System.out.println("Checks if a random word from ValidGuessList is valid");
        String expectedWord = randomWord;
        
        // Check if the word is valid
        boolean expResult = true;
        boolean result = instance.check(expectedWord);
        
        System.out.println("Random valid word checked: '" + expectedWord + "' Result: " + result);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheck_InvalidWord() {
        System.out.println("Checks if an invalid word is correctly identified as invalid");
        String invalidWord = "azpla"; // using a word not in the list
        boolean result = instance.check(invalidWord);
        System.out.println("Invalid word checked: '" + invalidWord + "' Result: " + result);
         assertFalse("Expected false for an invalid word not in the list", result);
}

}
