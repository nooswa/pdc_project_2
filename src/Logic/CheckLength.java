/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

/**
 * @author Larissa Goh 18029695
 * This class is a subclass of CheckUserInput and checks if a given word has the correct length.
 */
public class CheckLength extends CheckUserInput {
    
    @Override // Overrides to print a specific message.
    public void printResponse() {
        System.out.println("\nWord must be 5 letters long.");
    }
    
    @Override 
    public boolean check(String word) {
        // Validate the word by checking if it's in the list of valid words
        boolean isValid = (word.length() == 5);
        
        // If the word is not valid, print the response
        if (!isValid) {
            printResponse();
        }
        
        return isValid;
    }
}
