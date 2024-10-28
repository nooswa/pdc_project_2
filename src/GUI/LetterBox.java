package GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author noooo
 */
import javax.swing.*;
import java.awt.*;

public class LetterBox extends JPanel {
    private final JLabel label;

    public LetterBox() {
        label = new JLabel("", SwingConstants.CENTER);
        setBackground(Color.LIGHT_GRAY);
        add(label);
        setPreferredSize(new Dimension(50, 50));
    }

    public void setLetter(char letter) {
        label.setText(String.valueOf(letter).toUpperCase());
    }

    public String getLetter() {
        return label.getText();
    }

    public void setCorrect() {
        setBackground(Color.GREEN);
    }

    public void setPartial() {
        setBackground(Color.YELLOW);
    }

    public void setIncorrect() {
        setBackground(Color.GRAY);
    }
}

