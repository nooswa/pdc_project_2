package GUI.UI;

import DataBase.PlayerDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Larissa Goh 18029695
 * Test class for LoginManager.
 * Ensures that the authenticate method functions correctly for logging in.
 */
public class LoginManagerTest {
    
    private LoginManager loginManager;
    private PlayerDB playerDB;

    @Before
    public void setUp() {
        loginManager = new LoginManager();
        playerDB = new PlayerDB();
        playerDB.clearUsers();  // Clear users before each test
        System.out.println("Cleared users before testing");
        
        // Register a test user for login validation
        playerDB.registerUser("Test Name", "email@example.com", "password123");
        System.out.println("Test user registered with email: email@example.com and password: password123");
    }
    
    @After
    public void tearDown() {
        playerDB.clearUsers(); // Clears users after each test
        System.out.println("Cleared users after testing");
    }

    /**
     * Test of authenticate method with valid credentials.
     */
    @Test
    public void testAuthenticate_ValidCredentials() {
        System.out.println("Testing authenticate with valid credentials");
        String email = "email@example.com";  // corrected email to match the registered user
        String password = "password123";
        boolean result = loginManager.authenticate(email, password);
        assertTrue("Authentication should succeed with valid credentials", result);
    }

    /**
     * Test of authenticate method with invalid email.
     */
    @Test
    public void testAuthenticate_InvalidEmail() {
        System.out.println("Testing authenticate with invalid email");
        String email = "invalid@example.com";
        String password = "password123";
        boolean result = loginManager.authenticate(email, password);
        assertFalse("Authentication should fail with an incorrect email", result);
    }

    /**
     * Test of authenticate method with invalid password.
     */
    @Test
    public void testAuthenticate_InvalidPassword() {
        System.out.println("Testing authenticate with invalid password");
        String email = "email@example.com"; // corrected email to match the registered user
        String password = "wrongpassword";
        boolean result = loginManager.authenticate(email, password);
        assertFalse("Authentication should fail with an incorrect password", result);
    }

    /**
     * Test of authenticate method with both invalid email and password.
     */
    @Test
    public void testAuthenticate_InvalidEmailAndPassword() {
        System.out.println("Testing authenticate with invalid email and password");
        String email = "invalid@example.com";
        String password = "wrongpassword";
        boolean result = loginManager.authenticate(email, password);
        assertFalse("Authentication should fail with both incorrect email and password", result);
    }
}