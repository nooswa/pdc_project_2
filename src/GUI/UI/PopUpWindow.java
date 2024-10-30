/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.UI;

import GUI.model.LetterBox;
import DataBase.WordsDB;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author Noor Swadi 22167422 This class displays a reminder pop-up with
 * customisable text, button, and dimensions.
 */
public class PopUpWindow extends JDialog {

    JButton jb; // Instance of the button in the PopUpWindow. Mainly used for adding an MouseListener outside the class
    private WordsDB wordsDB; // Instance of WordsDB

    /**
     * Constructs a PopUpWindow with default dimensions and a specified button
     * and message.
     *
     * @param jFrame the parent window
     * @param text message to display
     * @param buttonText text for the button
     * @param useClickClose if true, adds a ClickClose listener to the button
     */
    public PopUpWindow(JFrame jFrame, String text, String buttonText, boolean useClickClose, WordsDB wordsDB) {
        super(jFrame, "Reminder", true);
        this.wordsDB = wordsDB;
        this.setLayout(null);
        Container c = this.getContentPane();
        _initialize(251, 200, jFrame);
        JLabel label = MainFrame.makeLabel(text, "Segoe UI", Font.BOLD, 25);
        label.setBounds(25, 25, 201, 30);
        c.add(label);

        jb = new JButton(buttonText);
        if (useClickClose) {
            jb.addActionListener(new ClickClose(this));
        }
        jb.setBounds(60, 85, 130, 50);
        c.add(jb);
    }

    public PopUpWindow(int width, int height, JFrame jFrame, String title, WordsDB wordsDB) {
        super(jFrame, title, true);
        this.wordsDB = wordsDB;
        _initialize(width, height, jFrame);
    }

    private void _initialize(int width, int height, JFrame jFrame) {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(jFrame);
        setBounds((MainFrame.WIDTH - width) / 2 + jFrame.getX(), (MainFrame.HEIGHT - height) / 2 + jFrame.getY(),
                width, height);

    }
}

// Nested class that disposes of the JDialog when clicked.
class ClickClose implements ActionListener {

    JDialog window = null;

    public ClickClose(JDialog window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.dispose();
    }
}

// Nested class that sets the panel and card to display on click.
class ClickChangeCard implements ActionListener {

    JPanel panel = null;
    String name;    // Destination card name.

    public ClickChangeCard(JPanel window, String cardName) {
        this.panel = window;
        this.name = cardName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((CardLayout) panel.getLayout()).show(panel, name);
    }
}

// Nested class to restart the geme when clicked
class ClickRestart implements ActionListener {

    JDialog window = null;
    LetterBox boxes;

    /**
     * Constructor.
     *
     * @param window the father {@code JDialog}.
     */
    public ClickRestart(JDialog window, LetterBox boxes) {
        this.window = window;
        this.boxes = boxes;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boxes.refresh();
        window.dispose();
    }
}

/*WIP - CHANGE AESTHETICS*/
// Nested class for the pop up window that shows when you win or lose
class PopRes extends PopUpWindow {

    public PopRes(JFrame jFrame, WordsDB wordsDB, boolean win) {
        super(251, 300, jFrame, "Congratulations", wordsDB); // Pass the wordsDB instance
        this.setLayout(null);
        this.addWindowListener(new CloseRefresh());

        Container c = this.getContentPane();
        JLabel line1;
        if (win) {
            line1 = MainFrame.makeLabel("Success", "Serif", Font.BOLD, 40);
            line1.setForeground(new Color(121, 167, 107));
            JLabel line4 = MainFrame.makeLabel("within " + (Position.getRow() + 1) + " attempts", "Segoe UI", Font.BOLD, 20);
            line4.setForeground(new Color(121, 167, 107));
            line4.setBounds(25, 150, 201, 30);
            c.add(line4);
        } else {
            this.setTitle("Oh no");
            line1 = MainFrame.makeLabel("Failed", "Serif", Font.BOLD, 40);
            line1.setForeground(new Color(121, 124, 126));
            JLabel line4 = MainFrame.makeLabel("within " + (Position.getRow() + 1) + " attempts", "Segoe UI", Font.BOLD, 20);
            line4.setForeground(new Color(198, 180, 102));
            line4.setBounds(25, 150, 201, 30);
            c.add(line4);
        }

        line1.setBounds(15, 20, 221, 50);
        c.add(line1);

        JLabel line2 = MainFrame.makeLabel("in guessing", "Segoe UI", Font.PLAIN, 16);
        line2.setForeground(Color.BLACK);
        line2.setBounds(25, 65, 201, 30);
        c.add(line2);

        JLabel line3 = MainFrame.makeLabel(wordsDB.getSecretWord(), "Segoe UI", Font.BOLD, 40); // Use the instance variable
        line3.setForeground(Color.BLACK);
        line3.setBounds(25, 95, 201, 50);
        c.add(line3);

        jb = new JButton("Play Again");
        jb.setBounds(60, 195, 130, 50);
        jb.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Increase the font size
        c.add(jb);

    }

    // Nested static class that refreshes the game upon closing the PopUpWindow
    private static class CloseRefresh implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            LetterBox.refresh();
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }
}
