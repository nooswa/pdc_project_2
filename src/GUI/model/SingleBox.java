/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.model;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 *
 * @author Noor Swadi 22167422 This class serves as a visual unit for displaying
 * letters in the game. It includes logic for changing appearance based on the
 * correctness of the letter.
 */
public class SingleBox extends JLabel {

    private final static Color YELLOW = new Color(198, 180, 102);
    private final static Color GREY = new Color(121, 124, 126);
    private final static Color GREEN = new Color(121, 167, 107);
    private final static Color BORDER_GREY = new Color(212, 214, 218);

    private Border border = null;
    // Border of a single box

    //Initialise a SingleBox instance to an uncheck state.
    public SingleBox() {
        border = BorderFactory.createLineBorder(BORDER_GREY, 4);
        this.setBorder(border);
        this.setText(" ");
        this.setFont(new Font("SansSerif", Font.BOLD, 30));
        this.setSize(63, 63);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setForeground(Color.BLACK);
    }

    // Refreshes the box with a new letter and updates its colour based on the type
    public void refresh(int type, String s) {
        this.setText(s);
        this.setColour(type);
    }

    // Refreshes the box's colour solely based on the type
    public void refresh(int type) {
        this.setColour(type);
    }

    //Sets the colour of the box based on the type
    private void setColour(int type) {
        this.setForeground(Color.WHITE);
        if (type == 1) {     // Incorrect = GREY
            this.border = BorderFactory.createLineBorder(GREY, 4);
            this.setBorder(border);
            this.setBackground(GREY);
        } else if (type == 2) {   // Not in the right position = YELLOW
            this.border = BorderFactory.createLineBorder(YELLOW, 4);
            this.setBorder(border);
            this.setBackground(YELLOW);
        } else if (type == 3) {      // Correct = GREEN
            this.border = BorderFactory.createLineBorder(GREEN, 4);
            this.setBorder(border);
            this.setBackground(GREEN);
        } else {
            this.border = BorderFactory.createLineBorder(BORDER_GREY, 4);
            this.setBorder(border);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }
    }
}
