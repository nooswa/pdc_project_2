/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordle;

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
        // Validate the word by checking that every character is an alphabet letter.
        boolean isValid = true;
        
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isAlphabetic(word.charAt(i))){
                isValid = false;
            break;
            }
        }
        
        // If the word is not valid, print the response.
        if (!isValid) {
            printResponse();
        }
        
        return isValid;
    }
    
}
