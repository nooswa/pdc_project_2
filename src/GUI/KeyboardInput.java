/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;


/**
 *
 * @author noooo
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {
    private final LetterBox[] letterBoxes;
    private int letterIndex;

    public KeyboardInput(LetterBox[] letterBoxes) {
        this.letterBoxes = letterBoxes;
        this.letterIndex = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (letterIndex < 5) {
            letterBoxes[letterIndex].setLetter(e.getKeyChar());
            letterIndex++;
        }
    }

    public void reset() {
        letterIndex = 0;
        for (LetterBox box : letterBoxes) {
            box.setLetter(' ');
        }
    }
}



