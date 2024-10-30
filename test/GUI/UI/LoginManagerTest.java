package GUI.UI;

import DataBase.PlayerDB;
import GUI.model.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * @author Larissa Goh 18029695 Test class for LoginManager. Ensures that the
 * authenticate method functions correctly for logging in.
 */
public class LoginManagerTest {

    private LoginManager loginManager;
    private PlayerDB playerDB;
    private static final String testEmail = "email10@example.com";
    private static final String testPassword = "password123";
    private static final String testName = "Test Name";
    /**
     * Set up method to initialize the LoginManager and PlayerDB instances.
     * Also registers a test user for login validation before each test.
     */
    @Before
    public void setUp() {
        loginManager = new LoginManager();
        playerDB = new PlayerDB();

        if (!playerDB.checkLogin(testEmail, testPassword)) {
            playerDB.registerUser(testName, testEmail, testPassword);
            System.out.println("Test user registered with email: " + testEmail + " and password: " + testPassword);
        }
    }
    /**
     * Tear down method to clean up after each test.
     * Deletes the registered test user after each test.
     */
    @After
    public void tearDown() {
        
        playerDB.deleteUserByEmail(testEmail);
        System.out.println("Cleared test user after testing");
    }
    /**
     * Test of authenticate method with valid credentials.
     * This test should succeed as the test user is registered with valid credentials.
     */
    @Test
    public void testAuthenticate_ValidCredentials() {
        System.out.println("Testing authenticate with valid credentials");
        Player result = loginManager.authenticate(testEmail, testPassword);
        System.out.println("Authentication result: " + result);
        assertTrue("Authentication should succeed with valid credentials", result);
    }
    /**
     * Test of authenticate method with an invalid email.
     * This test should fail as the email does not match any registered user.
     */
    @Test
    public void testAuthenticate_InvalidEmail() {
        System.out.println("Testing authenticate with invalid email");
        String email = "invalid@example.com";
        Player result = loginManager.authenticate(email, testPassword);
        System.out.println("Authentication result: " + result);
        assertFalse("Authentication should fail with an incorrect email", result);
    }
    /**
     * Test of authenticate method with an invalid password.
     * This test should fail as the password does not match the registered user's password.
     */
    @Test
    public void testAuthenticate_InvalidPassword() {
        System.out.println("Testing authenticate with invalid password");
        String email = testEmail;
        String password = "wrongpassword";
        Player result = loginManager.authenticate(email, password);
        System.out.println("Authentication result: " + result);
        assertFalse("Authentication should fail with an incorrect password", result);
    }
    /**
     * Test of authenticate method with both invalid email and password.
     * This test should fail as both credentials are incorrect.
     */
    @Test
    public void testAuthenticate_InvalidEmailAndPassword() {
        System.out.println("Testing authenticate with invalid email and password");
        String email = "invalid@example.com";
        String password = "wrongpassword";
        Player result = loginManager.authenticate(email, password);
        System.out.println("Authentication result: " + result);
        assertFalse("Authentication should fail with both incorrect email and password", result);
    }
}

