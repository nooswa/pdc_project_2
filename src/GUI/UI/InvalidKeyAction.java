/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.UI;

/**
 *
 * @author Noor Swadi 22167422
 * Implements KeyAction and handles cases where an invalid key is pressed. 
 * It does not perform any action.
 */
public class InvalidKeyAction implements KeyAction {
    @Override
    public void execute(KeyboardInput keyboardInput) {
        // Do nothing for invalid keys
    }
}
