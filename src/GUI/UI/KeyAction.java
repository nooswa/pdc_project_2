/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.UI;

import GUI.UI.KeyboardInput;

/**
 *
 * @author Noor Swadi 22167422
 * KeyAction interface defines the contract for all key actions.
 * Each key action should implement the execute method to define its behavior.
 */
public interface KeyAction {
    void execute(KeyboardInput keyboardInput);
}

