
package Logic;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Larissa Goh 18029695
 * This is abstract class that is a parent class for 3 input validation checks.
 * It defines the structure for validating input and printing error responses.
 * This class now implements a registry based factory method to manage the different validators, and maps
 * validator types to their creation logic.
 * 
 */
public abstract class CheckUserInput {
    
    // Factory registry for validator creation based on type
    private static final Map<String, Function<ValidGuessList, CheckUserInput>> registry = new HashMap<>();

    // METHOD CREATED BY CHATGPT. Initialise registry, each type is registered in the registry
    static {
        registry.put("length", validGuessList -> new CheckLength());
        registry.put("characters", validGuessList -> new CheckCharacters());
        registry.put("validWord", CheckValidWord::new);
    }

    //  METHOD CREATED BY CHATGPT Factory method to create a validator based on type
    public static CheckUserInput createValidator(String type, ValidGuessList validGuessList) {
        Function<ValidGuessList, CheckUserInput> creator = registry.get(type);
        if (creator == null) {
            throw new IllegalArgumentException("Invalid validator type: " + type);
        }
        return creator.apply(validGuessList);
    }
     // Ensures all subclasses use this to check validity. 
    public abstract boolean check(String word);
    
    // Prints this response when an input is invalid. Can be overridden for a specific message.
    public void printResponse() {
        System.out.println("Must be a valid word!");
    }
}
