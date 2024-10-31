/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.UI;
/**
 *
 * @author Noor Swadi 22167422
 * Implements KeyAction to handle typing a letter.
 */
public class TypeLetterAction implements KeyAction {
    private final char letter;

    public TypeLetterAction(char letter) {
        this.letter = letter;
    }

    @Override
    public void execute(KeyboardInput keyboardInput) {
        keyboardInput.typeLetter(letter);
    }
}



