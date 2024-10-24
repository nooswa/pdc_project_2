package Logic;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Larissa Goh 18029695
 * Unit test for CheckValidWord class.
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
}
