package Logic;

/**
 * @author Larissa Goh 18029695
 * This class extends CheckUserInput and checks if a word contains only alphabetic characters.
 */
public class CheckCharacters extends CheckUserInput {

   @Override // Overrides to print a specific message.
    public void printResponse() {
        System.out.println("Word can only contain letters.");
    }

    @Override
    public boolean check(String word) {
    // Check if the word is empty
    if (word == null || word.isEmpty()) {
        printResponse();
        return false;
    }

    // Check if all characters are alphabetic
    for (int i = 0; i < word.length(); i++) {
        if (!Character.isAlphabetic(word.charAt(i))) {
            printResponse(); // Only print response if a non-alphabetic character is found
            return false;
        }
    }

    // If no issues found, return true (valid word)
    return true;
}

}
