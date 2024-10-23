
package Logic;
/**
 * @author Noor Swadi 22167422
 * This class manages the exit game command.
 * 
 */
public class ExitGame {
    
    //Check for the 'exit game' command and print confirmation message
    public static void checkForExitCommand(String input) {
        if (input.equalsIgnoreCase("exit game")) {
            System.out.println("Exiting the game. Thanks for playing!");
            System.exit(0);
        }
    }
}
