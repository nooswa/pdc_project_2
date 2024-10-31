package GUI.UI;

import GUI.model.SignUpHandler;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for SignUpHandler. 
 * @author Larissa Goh 18029695
 */
public class SignUpHandlerTest {
    
    private SignUpHandler signUpHandler;

    public SignUpHandlerTest() {
    }

    /**
     * Test of validateFullName method, of class SignUpHandler.
     */
    @Test
    public void testValidateFullName() {
        System.out.println("Running test with valid full name");
        signUpHandler = new SignUpHandler("Test Name", "testname@example.com", "password123");
        boolean result = signUpHandler.validateFullName();
        assertTrue("Full name validation should succeed with a valid full name", result);
        System.out.println("testValidateFullName completed.");
    }

    /**
     * Test of validateEmail method, of class SignUpHandler.
     */
    @Test
    public void testValidateEmail() {
        System.out.println("Running test with valid email");
        signUpHandler = new SignUpHandler("Test Name", "testname@example.com", "password123");
        boolean result = signUpHandler.validateEmail();
        assertTrue("Email validation should succeed with a valid email", result);
        System.out.println("testValidateEmail completed.");
    }

    /**
     * Test of validatePassword method, of class SignUpHandler.
     */
    @Test
    public void testValidatePassword() {
        System.out.println("Running test with valid password");
        signUpHandler = new SignUpHandler("Test Name", "testname@example.com", "password123");
        boolean result = signUpHandler.validatePassword();
        assertTrue("Password validation should succeed with a valid password", result);
        System.out.println("testValidatePassword completed.");
    }

    /**
     * Test of validateAll method, of class SignUpHandler with all valid inputs.
     */
    @Test
    public void testValidateAll_ValidInputs() {
        System.out.println("Running test with all valid inputs");
        signUpHandler = new SignUpHandler("Test Name", "testname@example.com", "password123");
        boolean result = signUpHandler.validateAll();
        assertTrue("All validations should pass with valid inputs", result);
        System.out.println("testValidateAll_ValidInputs completed.");
    }

    /**
     * Test of validateAll method with invalid email.
     */
    @Test
    public void testValidateAll_InvalidEmail() {
        System.out.println("Running test with invalid emaill");
        signUpHandler = new SignUpHandler("Test Name", "testnameexample.com", "password123");
        boolean result = signUpHandler.validateAll();
        assertFalse("Validation should fail due to invalid email", result);
        System.out.println("testValidateAll_InvalidEmail completed.");
    }

    /**
     * Test of validateAll method with invalid password.
     */
    @Test
    public void testValidateAll_InvalidPassword() {
        System.out.println("Running test with invalid password");
        signUpHandler = new SignUpHandler("Test Name", "testname@example.com", "123");
        boolean result = signUpHandler.validateAll();
        assertFalse("Validation should fail due to invalid password", result);
        System.out.println("testValidateAll_InvalidPassword completed.");
    }

    /**
     * Test of validateAll method with invalid full name.
     */
    @Test
    public void testValidateAll_InvalidFullName() {
        System.out.println("Running test with invalid full name");
        signUpHandler = new SignUpHandler("Test", "testname@example.com", "password123");
        boolean result = signUpHandler.validateAll();
        assertFalse("Validation should fail due to invalid full name", result);
        System.out.println("testValidateAll_InvalidFullName completed.");
    }

    /**
     * Test of validateAll method with all invalid inputs.
     */
    @Test
    public void testValidateAll_AllInvalid() {
        System.out.println("Running test with all invalid inputs");
        signUpHandler = new SignUpHandler("Test", "testnameexample.com", "123");
        boolean result = signUpHandler.validateAll();
        assertFalse("Validation should fail due to all invalid inputs", result);
        System.out.println("testValidateAll_AllInvalid completed.");
    }
}
