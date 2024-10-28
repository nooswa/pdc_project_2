/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DataBase.WordsDB;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author noooo
 * Handles the game logic and user interactions
 */

public class WordlePanel extends JPanel {
    private final String secretWord;
    private final WordsDB wordsDB;
    private final LetterBox[][] letterBoxes;
    private int attempt;
    
    public WordlePanel(String secretWord, WordsDB wordsDB) {
        this.secretWord = secretWord;
        this.wordsDB = wordsDB;
        this.letterBoxes = new LetterBox[6][5];
        this.attempt = 0;

        setLayout(new GridLayout(7, 5, 5, 5));
        initLetterBoxes();
        initEnterButton();
    }

    private void initLetterBoxes() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                letterBoxes[i][j] = new LetterBox();
                add(letterBoxes[i][j]);
            }
        }
    }

    private void initEnterButton() {
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new EnterButtonListener());
        add(enterButton);
    }

    private class EnterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (attempt < 6) {
                StringBuilder guess = new StringBuilder();
                for (LetterBox box : letterBoxes[attempt]) {
                    guess.append(box.getLetter());
                }
                
                if (wordsDB.getValidWords().contains(guess.toString())) {
                    checkGuess(guess.toString());
                    attempt++;
                } else {
                    MessageDialog.showMessage("Invalid word. Try again!");
                }
            }
        }
    }

    private void checkGuess(String guess) {
        for (int i = 0; i < 5; i++) {
            char guessedLetter = guess.charAt(i);
            LetterBox box = letterBoxes[attempt][i];
            
            if (guessedLetter == secretWord.charAt(i)) {
                box.setCorrect();
            } else if (secretWord.contains(String.valueOf(guessedLetter))) {
                box.setPartial();
            } else {
                box.setIncorrect();
            }
        }

        if (guess.equals(secretWord)) {
            MessageDialog.showMessage("Congratulations! You've guessed the word!");
        } else if (attempt == 5) {
            MessageDialog.showMessage("Game Over! The word was: " + secretWord);
        }
    }
}


