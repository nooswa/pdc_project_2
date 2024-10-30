/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.UI;

/**
 *
 * @author Noor Swadi 22167422
 * mplements KeyAction to handle the backspace action.
 */
public class TypeBackspaceAction implements KeyAction {
    @Override
    public void execute(KeyboardInput keyboardInput) {
        keyboardInput.typeBackspace();
    }
}
