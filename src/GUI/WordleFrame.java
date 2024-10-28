/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
        
import DataBase.WordsDB;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author noooo
 */
public class WordleFrame {
    private WordlePanel wordlePanel;
    private WordsDB wordsDB;
    
    public WordleFrame() {
        wordsDB = new WordsDB();  // Initialize WordsDB for word selection
        String secretWord = wordsDB.getSecretWord();
        
        JFrame frame = new JFrame("Wordle Game");
        wordlePanel = new WordlePanel(secretWord, wordsDB);

        frame.add(wordlePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(WordleFrame::new);
    }
}



