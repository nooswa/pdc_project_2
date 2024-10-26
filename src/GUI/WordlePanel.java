/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author noooo
 * Handles the game logic and user interactions
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

public class WordlePanel extends JPanel {
    private String targetWord;
    private HashSet<String> validWords;
    private String[][] guesses;
    private int currentGuess;
    private JTextField inputField;
    private JButton submitButton;

    private static final int GRID_SIZE = 6;
    private static final int LETTERS_PER_GUESS = 5;

    public WordlePanel(WordleFrame frame) {
        // this.targetWord = frame.getTargetWord().toUpperCase(); // Get target word from frame
        // this.validWords = frame.getValidWord(); // Get valid words from frame
        this.guesses = new String[GRID_SIZE][LETTERS_PER_GUESS];
        this.currentGuess = 0;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 400));

        inputField = new JTextField();
        submitButton = new JButton("Submit");

        add(inputField, BorderLayout.NORTH);
        add(submitButton, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitGuess();
            }
        });

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitGuess();
            }
        });
    }

    private void submitGuess() {
        String guess = inputField.getText().toUpperCase().trim();
        if (isValidGuess(guess)) {
            guesses[currentGuess] = guess.split("");
            currentGuess++;
            inputField.setText("");

            if (currentGuess >= GRID_SIZE) {
                // Handle end of game (e.g., reveal the target word, disable input)
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid guess. Try again.");
        }
    }

    private boolean isValidGuess(String guess) {
        return validWords.contains(guess) && guess.length() == LETTERS_PER_GUESS;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGuesses(g);
    }

    private void drawGuesses(Graphics g) {
        for (int i = 0; i < currentGuess; i++) {
            for (int j = 0; j < LETTERS_PER_GUESS; j++) {
                drawLetter(g, guesses[i][j], i, j);
            }
        }
    }

    private void drawLetter(Graphics g, String letter, int row, int col) {
        int x = col * 70 + 10;
        int y = row * 70 + 100;
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, 60, 60);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 60, 60);
        g.drawString(letter, x + 20, y + 40);
    }
}