package DataBase;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

/**@author Larissa Goh 18029695
 * @author Noor Swadi 22167422
 * This test class tests the database connection and that our ValidWords load correctly.
 * 
 */
public class WordsDBTest {

    private WordsDB wordsDB;

    /**
     *  // Test the database connection (ensure it's working)
     */
    @Before
    public void setUp() {
        wordsDB = new WordsDB();
        System.out.println("Testing database connection...");
    }

    /**
     * Test to retrieve valid words from the database
     */
    @Test
    public void testGetValidWords() {
        List<String> validWords = wordsDB.getValidWords();
        assertNotNull("Valid words list should not be null", validWords);
        assertFalse("Valid words list should contain words", validWords.isEmpty());
        System.out.println("Valid Words Loaded: " + validWords);
    }

    /**
     * Test to ensure a secret word is selected correctly. 
     * Also ensures not null and correct length.
     */
    @Test
    public void testGetSecretWord() {
        String secretWord = wordsDB.getSecretWord();
        assertNotNull("Secret word should not be null", secretWord);
        assertEquals("Secret word should be 5 letters long", 5, secretWord.length());
        System.out.println("Secret Word Selected: " + secretWord);
    }

    /**
     * Test to display contents of the valid guess list for logging purposes.
     */
    @Test
    public void testDisplayValidGuessList() {
        List<String> validWords = wordsDB.getValidWords();
        assertNotNull("Valid guess list should not be null", validWords);
        assertFalse("Valid guess list should contain words", validWords.isEmpty());
        
        System.out.println("Contents of Valid Guess List:");
        for (String word : validWords) {
            System.out.println(word);
        }
    }
}
