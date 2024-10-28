/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DataBase.WordsDB;
import javax.swing.*;
import java.awt.event.*;

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

    public KeyboardInput(LetterBox letterBoxes, WordsDB words, JFrame parentFrame) {
        this.words = words;
        this.parentFrame = parentFrame;
        this.boxes = letterBoxes;
        boxes.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        KeyAction action = determineKeyAction(c);
        action.execute(this); // Execute the action
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private KeyAction determineKeyAction(char c) {
        if (c >= 'A' && c <= 'Z') {
            return new TypeLetterAction(c);
        }
        if (c >= 'a' && c <= 'z') {
            return new TypeLetterAction(Character.toUpperCase(c));
        }
        if (c == KeyEvent.VK_BACK_SPACE) {
            return new TypeBackspaceAction();
        }
        if (c == KeyEvent.VK_ENTER) {
            return new TypeEnterAction();
        }
        return new InvalidKeyAction();

    }

    
    protected int validateKey(char c) {
        if (c >= 'A' && c <= 'Z') {
            return 1;
        }
        if (c >= 'a' && c <= 'z') {
            return 2;
        }
        if (c == KeyEvent.VK_BACK_SPACE) {
            return 3;
        }
        if (c == KeyEvent.VK_ENTER) {
            return 4;
        }
        return 0;
    }

   // Types a letter into a box
    protected void typeLetter(char c) {
        if (Position.getCol() < LetterBox.COLS) {
            box = boxes.getSingleBox(Position.getRow(), Position.getCol());
            box.setText(Character.toString(c));
            Position.setCol(Position.getCol() + 1);
        }
    }

    //Deletes a letter with Backspace
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

   
    // Displays a result popup based on the games outcome
    private void showResultPopup(boolean isWin) {
        PopUpWindow pop = new PopRes(parentFrame, words, isWin);
        pop.jb.addActionListener(new ClickRestart(pop, boxes));
        pop.setVisible(true);
    }

    //Displays a popup with a custom message and button
    private void showPopup(String message, String buttonText) {
        PopUpWindow pop = new PopUpWindow(parentFrame, message, buttonText, true, words);
        pop.setVisible(true);
    }
}
