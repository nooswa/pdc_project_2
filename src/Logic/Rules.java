
package Logic;

/**
 * @author Larissa Goh 18029695
 * This class displays the rules of the Wordle game. It displays on every first run of the game unless
 * the user requests it to be shown again. Changed to implement a singleton design pattern to prevent multiple 
 * instances of Rules. 
 * 
 */

public class Rules {
    
    private static Rules instance; // For single instance
    public boolean rulesDisplayed = false; // Tracks whether rules have been shown.
    
    public Rules() {}

    // Provide global access to the instance
    public static Rules getInstance() {
        if (instance == null) { // First checks if the instance is created
            instance = new Rules();
        }
        return instance;
    }
    
    // Method to return rules
    public String getRules() {  
        return "RULES:\n" +
                "* Each guess must be a valid five-letter word.\n" +
               "* The colour of a letter will change to show you how close your guess was.\n" +
               "* If the letter turns green, the letter is in the word and in the correct spot.\n" +
               "* If the letter turns yellow, the letter is in the word, but it is not in the correct spot.\n\n" +
                "* If the letter does not change colour, it is not in the word.\n" +
                "* To exit the game at any time, enter \"exit game\".\n" +
               "* To bring up the rules again, enter \"show rules\"\n";
    }
    //  This method ensures the rules are shown on every first run. 
    public void displayRulesFirstRun() {
        if (!rulesDisplayed) {
            System.out.println(getRules());
            rulesDisplayed = true;
        }
    }
  
}
