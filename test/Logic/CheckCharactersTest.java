package Logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Larissa Goh 18029695
 * Unit test for CheckCharacters class.
 */
public class CheckCharactersTest {
    
    private CheckCharacters instance; // Declare instance

    @Before // Initalises before each test
    public void setUp() {
        System.out.println("setUp: Initializing instance");
        instance = new CheckCharacters();
    }
    /**
     * Test of check method for valid word containing only alphabetic characters.
     */
    @Test
    public void testCheck_Alphabetic() {
        String word = "Words";
        System.out.println("Testing check with valid word: '" + word + "', Length: " + word.length());
        boolean result = instance.check(word); 
        System.out.println("= " + result);
        assertTrue("Expected = TRUE for word with only alphabetic characters", result);
    }

    /**
     * Test of check method for word containing numbers.
     */
    @Test
    public void testCheck_InvalidWithNumbers() {
        String word = "Word123";
        System.out.print("Testing check with word containing numbers: '" + word + "' ");
        boolean result = instance.check(word);
        System.out.println("= " + result);
        assertFalse("Expected = FALSE for word with numbers", result);
    }

    /**
     * Test of check method for word containing special characters.
     */
    @Test
    public void testCheck_InvalidWordWithSpecialCharacters() {
        String word = "Word@!#";
        System.out.print("Testing check with word containing special characters: '" + word + "' ");
        boolean result = instance.check(word);
        System.out.println("= " + result);
        assertFalse("Expected = FALSE for word with special characters", result);
    }

    /**
     * Test of check method for empty string.
     */
    @Test
    public void testCheck_EmptyString() {
        String word = "";
        System.out.print("Testing check with empty string: '" + word + "' ");
        boolean result = instance.check(word);
        System.out.println("= " + result);
        assertFalse("Expected = FALSE for an empty string", result);
    }

    /**
     * Test of check method for word with space.
     */
    @Test
    public void testCheck_WordWithSpace() {
        String word = "Word Word";
        System.out.print("Testing check with word containing space: '" + word + "' ");
        boolean result = instance.check(word);
        System.out.println("= " + result);
        assertFalse("Expected FALSE for word with space", result);
    }
}
