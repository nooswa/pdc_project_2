/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.UI;

import GUI.model.LetterBox;
import DataBase.WordsDB;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
/**
 *
 * @author Noor Swadi 22167422
 */
public class MainFrame extends JFrame {

    
    public static int WIDTH = 601;
    
    public static int HEIGHT = 680;
    
    private JLabel titleLabel = null;
    
    private JLabel version = null;
   
    private LetterBox letterBox = null;
   
    private WordsDB words = null;
    
    private JButton[] jbArray = null;

    KeyboardInput input;

    
    Button rules;

 
    public MainFrame() {
        this.setTitle("Wordle");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);// Make the window always generate at the center of screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        this.setResizable(false);   // Disable resizing the window
        this.setLayout(null);
        this.words = new WordsDB();
        words.getValidWords();
        this.jbArray = new JButton[10];     
        this._buildPage();
        input = new KeyboardInput(letterBox, words, this);
       rules = new Button(jbArray, letterBox, words, this);
    }


    public void myShow() {
        this.addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                letterBox.requestFocusInWindow();
            }
        });
        this.setVisible(true);
    }


    public static JLabel makeLabel(String text, String font, int FontStyle, int textSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(font, FontStyle, textSize));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        return label;
    }

    private void _buildPage() {  // Add components
        Container c = this.getContentPane();
        letterBox = new LetterBox();
        letterBox.setBounds(133, 130, LetterBox.WIDTH, LetterBox.HEIGHT);
        c.add(letterBox);

        // The title
        titleLabel = new JLabel("Wordle");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 45));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setBounds(200, 40, 201, 50);
        c.add(titleLabel);

       
        jbArray[0] = new JButton("SHOW RULES");
        jbArray[0].setFont(new Font("SansSerif", Font.PLAIN, 22));
        jbArray[0].setBounds(225, 560, 151, 50);
        c.add(jbArray[0]);

    }
    
}
