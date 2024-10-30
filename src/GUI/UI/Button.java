/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.UI;


import GUI.model.LetterBox;
import DataBase.WordsDB;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Noor Swadi 22167422
 * 
 */
public class Button implements MouseListener, AssessInput{
    JButton rules = null;
    int rules_hash = 0;

    LetterBox boxes = null;
    WordsDB words = null;
    JFrame fatherFrame = null;

  
    public Button(JButton [] jbArray, LetterBox letterBoxes, WordsDB Words, JFrame fatherFrame){
        this.rules = jbArray[0];
        rules_hash = jbArray[0].hashCode();
        rules.addMouseListener(this);

        this.boxes = letterBoxes;
        this.words = words;
        this.fatherFrame = fatherFrame;

    }

 
    @Override
    public void mouseClicked(MouseEvent e) {
        int src = e.getSource().hashCode();
        if (src == rules_hash){
            pressRules();
        }
        boxes.requestFocusInWindow();
    }

   /*WIP PLZ UPDATE*/
    // Private method to display game rules
     private void pressRules() {
        String rulesText = "<html><body style='width: 300px;'>"
                         + "<h2>Wordle Rules</h2>"
                         + "<ul>"
                         + "<li>Guess the Wordle in six tries.</li>"
                         + "<li>Each guess must be a valid five-letter word.</li>"
                         + "<li>The color of the tiles will change to show how close your guess was.</li>"
                         + "<li>Green = Correct letter in the correct position.</li>"
                         + "<li>Yellow = Correct letter in the wrong position.</li>"
                         + "<li>Gray = Letter is not in the word.</li>"
                         + "</ul>"
                         + "</body></html>";

        JOptionPane.showMessageDialog(
            fatherFrame,
            rulesText,
            "Wordle Rules",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

 
    @Override
    public void mouseEntered(MouseEvent e) {
    }

 
    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}



