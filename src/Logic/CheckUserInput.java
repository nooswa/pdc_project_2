
package Logic;

/**
 * @author Larissa Goh 18029695
 * This is abstract class that is a parent class for 3 input validation checks.
 * It defines the structure for validating input and printing error responses.
 */
public abstract class CheckUserInput {
     //Ensures all subclasses use this to check validity. 
    public abstract boolean check(String word);
    
    // Prints this response when an input is invalid. Can be overridden for a specific message.
    public void printResponse() {
        System.out.println("Must be a valid word!");
    }
}
