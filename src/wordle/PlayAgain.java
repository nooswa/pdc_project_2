package wordle;
/**
 * @author Noor Swadi 22167422
 * This class manages the 'play again' command after the game ends.
 * 
 */
import java.util.Scanner;

public class PlayAgain {

    private final Scanner scanner;

    // Constructor
    public PlayAgain() {
        this.scanner = new Scanner(System.in);
    }

    // Asks the player whether they'd like to play again
    public boolean askToPlayAgain() {
        System.out.println("Type 'play again' to play again or 'exit game' to quit.");
        String userInput = scanner.nextLine().trim();

        if (userInput.equalsIgnoreCase("play again")) {
            return true;
        } else if (userInput.equalsIgnoreCase("exit game")) {
            ExitGame.checkForExitCommand(userInput); // Call ExitGame class to handle exiting
            return false;
        } else {
            System.out.println("Invalid input. Please type 'play again' or 'exit game'.");
            return askToPlayAgain(); // Ask until a valid input is received
        }
    }
}