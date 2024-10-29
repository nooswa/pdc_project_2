/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.UI;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Noor Swadi 22167422
 * A factory that creates and provides appropriate KeyAction instances based
 * on the key character input.
 */
public class KeyActionFactory {
    private final Map<Character, KeyAction> actionRegistry = new HashMap<>();

    public KeyActionFactory() {
        // Register letter actions (A-Z)
        for (char c = 'A'; c <= 'Z'; c++) {
            actionRegistry.put(c, new TypeLetterAction(c));
        }
        // Register backspace and enter actions
        actionRegistry.put((char) KeyEvent.VK_BACK_SPACE, new TypeBackspaceAction());
        actionRegistry.put((char) KeyEvent.VK_ENTER, new TypeEnterAction());
    }

    public KeyAction getKeyAction(char c) {
        // Convert lowercase letters to uppercase before fetching action
        char key = Character.toUpperCase(c);
        return actionRegistry.getOrDefault(key, new InvalidKeyAction());
    }
}

