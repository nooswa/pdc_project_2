package Logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Larissa Goh 18029695
 * Unit test for CheckLength class.
 * Ensures that the check method correctly validates words with exactly 5 letters.
 */
public class CheckLengthTest {

    private CheckLength instance;

    @Before
    public void setUp() {
        instance = new CheckLength(); // Initialise the CheckLength instance before each test
    }

    /**
     * Test of check method for a word with 5 letters (valid).
     */
    @Test
    public void testCheck_CorrectLength() {
        System.out.println("check with a 5-letter word");
        String word = "apple";
        boolean result = instance.check(word);
        System.out.println("Result: " + result);
        assertTrue("Expected true for a 5-letter word", result);
    }

    /**
     * Test of check method for a word with fewer than 5 letters (invalid).
     */
    @Test
    public void testCheck_ShortWord() {
        System.out.println("check with a short word");
        String word = "cat"; // Tests input with a short word
        boolean result = instance.check(word);
        System.out.println("Result: " + result);
        assertFalse("Expected false for a word shorter than 5 letters", result);
    }

    /**
     * Test of check method for a word with more than 5 letters (invalid).
     */
    @Test
    public void testCheck_LongWord() {
        System.out.println("check with a long word");
        String word = "elephant"; // Tests input with a long word
        boolean result = instance.check(word);
        System.out.println("Result: " + result);
        assertFalse("Expected false for a word longer than 5 letters", result);
    }

    /**
     * Test of check method for an empty string (invalid).
     */
    @Test
    public void testCheck_EmptyString() {
        System.out.println("check with an empty string");
        String word = ""; 
        boolean result = instance.check(word);
        System.out.println("Result: " + result);
        assertFalse("Expected false for an empty string", result);
    }
}
