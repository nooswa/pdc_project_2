/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.UI;

import GUI.model.Position;
import GUI.UI.LetterBox;
import GUI.model.SingleBox;
import DataBase.WordsDB;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Noor Swadi 22167422
 * Responsible for handling keyboard input.
 */
public class KeyboardInput implements KeyListener, Enter {

    private LetterBox boxes;
    private SingleBox box;
    private WordsDB words;
    private JFrame parentFrame;
    private final KeyActionFactory keyActionFactory;

    public KeyboardInput(LetterBox letterBoxes, WordsDB words, JFrame parentFrame) {
        this.words = words;
        this.parentFrame = parentFrame;
        this.boxes = letterBoxes;
        this.keyActionFactory = new KeyActionFactory(); // Use factory
        boxes.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        KeyAction action = keyActionFactory.getKeyAction(c); // Use factory directly
        action.execute(this); // Execute the action
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    // Types a letter into a box
    protected void typeLetter(char c) {
        if (Position.getCol() < LetterBox.COLS) {
            box = boxes.getSingleBox(Position.getRow(), Position.getCol());
            box.setText(Character.toString(c));
            Position.setCol(Position.getCol() + 1);
        }
    }

    // Deletes a letter with Backspace
    protected void typeBackspace() {
        if (Position.getCol() > 0) {
            Position.setCol(Position.getCol() - 1);
            box = boxes.getSingleBox(Position.getRow(), Position.getCol());
            box.setText(" ");
        }
    }

    // Processes the Enter action, checking whether the current word is complete and valid
    protected void typeEnter() {
        if (Position.getCol() == LetterBox.COLS) {
            int result = Enter.submit(boxes.getRow(Position.getRow()), words);
            handleSubmissionOutcome(result);
        } else {
            showPopup("Not enough letters", "Close");
        }
    }

    // Handles outcome of word submissions
    private void handleSubmissionOutcome(int result) {
        if (result == 1) {
            showResultPopup(true);
            resetGame();
        } else if (result == 0) {
            moveToNextRowOrEndGame();
        } else {
            showPopup("Not in wordlist", "Close");
        }
    }

    // Move to the next row or end the game if all rows are filled
    private void moveToNextRowOrEndGame() {
        if (Position.getRow() < LetterBox.ROWS) {
            if (Position.getRow() == 5) {
                showResultPopup(false);
            } else {
                Position.setRow(Position.getRow() + 1);
                Position.setCol(0);
            }
        }
    }

    // Resets the game by setting the initial row and column positions and generating a new secret word
    private void resetGame() {
        Position.setRow(0);
        Position.setCol(0);
        words.getSecretWord();
    }

    // Displays a result popup based on the game's outcome
    private void showResultPopup(boolean isWin) {
        PopUpWindow pop = new PopRes(parentFrame, words, isWin);
        pop.jb.addActionListener(new ClickRestart(pop, boxes));
        pop.setVisible(true);
    }

    // Displays a popup with a custom message and button
    private void showPopup(String message, String buttonText) {
        PopUpWindow pop = new PopUpWindow(parentFrame, message, buttonText, true, words);
        pop.setVisible(true);
    }
}
