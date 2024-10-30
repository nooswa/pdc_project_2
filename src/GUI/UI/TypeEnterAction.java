/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.UI;

import GUI.UI.KeyboardInput;

/**
 *
 * @author Noor Swadi 22167422
 * Implements KeyAction to handle the enter action.
 */
public class TypeEnterAction implements KeyAction {
    @Override
    public void execute(KeyboardInput keyboardInput) {
        keyboardInput.typeEnter();
    }
}
