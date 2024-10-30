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
public class Main extends JFrame {

    
    public static int WIDTH = 601;
    
    public static int HEIGHT = 680;
    
    private JLabel titleLabel = null;
    
    private JLabel version = null;
   
    private LetterBox letterBox = null;
   
    private WordsDB words = null;
    
    private JButton[] jbArray = null;

    KeyboardInput input;

    
    Button rules;

 
    public Main() {
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
      //  c.add (new SignUp());
        
        
      //  SignUp signUpPanel = new SignUp();
      //  signUpPanel.setBounds(50, 100, 500, 400); // Adjust size and position as needed
      //  c.add(signUpPanel);
        // The title
        titleLabel = new JLabel("Wordle");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 45));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setBounds(200, 40, 201, 50);
        c.add(titleLabel);

        // The test version
    /*version = new JLabel("version");
    version.setFont(new Font("Serif", Font.PLAIN, 15));
    version.setHorizontalAlignment(JLabel.CENTER);
    version.setVerticalAlignment(JLabel.CENTER);
    version.setBounds(200, 650, 201, 50); // Adjusted y-coordinate
    c.add(version);
        /// The rules button in main page
        jbArray[0] = new JButton("Rules");
        jbArray[0].setFont(new Font("SansSerif", Font.BOLD, 22));
        jbArray[0].setBounds(225, 580, 151, 50); // Adjusted height

        int radius = 20; // Radius for rounded edges
        jbArray[0].setBorder(new RoundedBorder(radius));
        c.add(jbArray[0]);*/
        jbArray[0] = new JButton("RULES");
        jbArray[0].setFont(new Font("SansSerif", Font.PLAIN, 22));
        jbArray[0].setBounds(225, 560, 151, 50);
        c.add(jbArray[0]);

    }
    // Rounded border class for curved edges

    /*class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(c.getBackground()); // Color should be set to the button's background color
            g.fillRoundRect(x, y, width, height, radius, radius);
            g.setColor(Color.GRAY); // Set the border color
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius); // Draw the border
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius, radius, radius, radius);
        }
    }*/

}
