package GUI.UI;

import GUI.model.AssessInput;
import DataBase.PlayerDB;
import DataBase.WordsDB;
import GUI.model.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Responsible for handling keyboard input.
 *
 * @author Larissa Goh 18029695
 */
public class KeyboardInput implements KeyListener, AssessInput {

    private final LetterBox boxes;
    private final WordsDB words;
    private final JFrame parentFrame;
    private final KeyActionFactory keyActionFactory;
    private final Player player;
    private final PlayerDB playerDB;

    public KeyboardInput(LetterBox letterBoxes, WordsDB words, JFrame parentFrame, Player player, PlayerDB playerDB) {
        this.words = words;
        this.parentFrame = parentFrame;
        this.boxes = letterBoxes;
        this.keyActionFactory = new KeyActionFactory();
        this.player = player;
        this.playerDB = playerDB;

        for (KeyListener listener : boxes.getKeyListeners()) {
            if (listener instanceof KeyboardInput) {
                boxes.removeKeyListener(listener);
            }
        }
        boxes.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        KeyAction action = keyActionFactory.getKeyAction(c);
        action.execute(this);
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
            SingleBox box = boxes.getSingleBox(Position.getRow(), Position.getCol());
            box.setText(Character.toString(c));
            Position.setCol(Position.getCol() + 1);
        }
    }

    // Deletes a letter with Backspace
    protected void typeBackspace() {
        if (Position.getCol() > 0) {
            Position.setCol(Position.getCol() - 1);
            SingleBox box = boxes.getSingleBox(Position.getRow(), Position.getCol());
            box.setText(" ");
        }
    }

    // Processes the AssessInput action, checking whether the current word is complete and valid
    protected void typeEnter() {
        if (Position.getCol() == LetterBox.COLS) {
            int result = AssessInput.submit(boxes.getRow(Position.getRow()), words);
            handleSubmissionOutcome(result);
            boxes.clearCurrentRow(Position.getRow());
            Position.setCol(0); // Reset column after clearing
        } else {
            showPopup("Not enough letters", "Close");
            boxes.clearCurrentRow(Position.getRow());
            Position.setCol(0); // Reset column after clearing
        }
    }

    // Handles outcome of word submission
    private void endGame(boolean isWin) {
        player.getStats().incrementGamesPlayed();
        if (isWin) {
            player.getStats().incrementGamesWon();
        }
        playerDB.updateScore(player);
        showResultPopup(isWin);
        resetGame();
    }

    private void handleSubmissionOutcome(int result) {
        if (result == 1) {
            endGame(true);
        } else if (result == 0) {
            moveToNextRowOrEndGame();
        } else {
            showPopup("Not in wordlist", "Close");
            boxes.clearCurrentRow(Position.getRow()); // Clear row for invalid word
            Position.setCol(0); // Reset column
        }
    }

    // Move to the next row or end the game if all rows are filled
    private void moveToNextRowOrEndGame() {
        if (Position.getRow() < LetterBox.ROWS - 1) {
            Position.setRow(Position.getRow() + 1);
        } else {
            endGame(false);
        }
        Position.setCol(0);
    }

    // Resets the game by setting the initial row and column positions and generating a new secret word
    void resetGame() {
        Position.setRow(0);
        Position.setCol(0);
        words.selectRandomWord();
    }

   private void showResultPopup(boolean isWin) {
    if (parentFrame != null && words != null) {
        new PopUpWindow.PopRes(parentFrame, words, isWin, this).setVisible(true);  // Use fully qualified name
    } else {
        JOptionPane.showMessageDialog(null, "Error: Required components are missing.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    // Displays a popup with a custom message and button
    private void showPopup(String message, String buttonText) {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{});
        JDialog dialog = optionPane.createDialog(parentFrame, "Reminder");

        // Add close button to dialog
        JButton closeButton = new JButton(buttonText);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        optionPane.setOptions(new Object[]{closeButton});
        dialog.getRootPane().setDefaultButton(closeButton);

        // Refocuses on letter box after dialog closes
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                boxes.clearCurrentRow(Position.getRow());
                Position.setCol(0);
                boxes.requestFocusInWindow();
            }
        });

        dialog.setVisible(true);
    }

}



