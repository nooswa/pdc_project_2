package Logic;

import Logic.Rules;
import java.util.Scanner;

/**
 * @author Larissa Goh 18029695
 * @author Noor Swadi 22167422
 * This class manages user input throughout the game. It ensures the game continues by validating
 * each input through the CheckUserInput class or processing special commands.
 */

public class InputHandler {

    private final Rules rules;
    private final Scanner scanner;
    private final CheckUserInput checkValidWord;
    private final CheckUserInput checkLength;
    private final CheckUserInput checkCharacters;
    
    // Constructor to sets up the class with the rules and input validation checks.
    public InputHandler(Rules rules, ValidGuessList validGuessList) {
        this.rules = rules;
        this.scanner = new Scanner(System.in);
         // Using createValidator in CheckuserInput to create objects
        this.checkValidWord = CheckUserInput.createValidator("validWord", validGuessList);
        this.checkLength = CheckUserInput.createValidator("length", validGuessList);
        this.checkCharacters = CheckUserInput.createValidator("characters", validGuessList);
    }
    
    // Constantly promps a user for a guess.
    public String getUserInput() {
        String input;
        do {
            System.out.println("Enter Your Guess:");
            input = scanner.nextLine().trim().toUpperCase();  // Read input and convert to uppercase.
        } while (handleSpecialCommands(input) || !isValidInput(input));
        
        return input;
    }

    private boolean handleSpecialCommands(String input) {
        if (input.equalsIgnoreCase("show rules")) { // Displays the rules if this is entered.
            System.out.println(rules.getRules());  
            return true;  
        }

        if (input.equalsIgnoreCase("exit game")) {
            ExitGame.checkForExitCommand(input); // Exits game and program.
            return true;  
        }

        return false;  // No special command was entered.
    }

    private boolean isValidInput(String input) {
        // Use all validation checks.
        boolean isLengthValid = checkLength.check(input);
        boolean isCharactersValid = checkCharacters.check(input);
        boolean isWordValid = checkValidWord.check(input);

        return isLengthValid && isCharactersValid && isWordValid;
    }
}

