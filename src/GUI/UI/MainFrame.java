package GUI.UI;

import DataBase.PlayerDB;
import GUI.model.LetterBox;
import DataBase.WordsDB;
import GUI.model.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This MainFrame class sets up the window for the main game logic and manages keyboard input 
 * 
 * @version Noor Swadi 22167422
 * @version Larissa Goh 18029695
 */
public class MainFrame extends JFrame { 
    public static int WIDTH = 601;
    public static int HEIGHT = 680;

    private JLabel titleLabel = null;
    private JLabel version = null;
    private LetterBox letterBox = null;
    private final WordsDB words; // Make WordsDB final, so it’s passed once and reused
    private JButton[] jbArray = null;

    KeyboardInput input;
    private final Player player; // Make Player final
    private final PlayerDB playerDB; // Make PlayerDB final to avoid re-initialization

    public MainFrame(Player player, PlayerDB playerDB, WordsDB words) { // Accept PlayerDB and WordsDB as parameters
        this.player = player;
        this.playerDB = playerDB; // Reuse passed-in PlayerDB instance
        this.words = words; // Reuse passed-in WordsDB instance
        words.getValidWords(); // Load words only once, if not already loaded

        this.setTitle("Wordle");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null); // Center the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false); // Disable resizing the window
        this.setLayout(null);
        this.jbArray = new JButton[10];
        
        this._buildPage();

        // Attach KeyboardInput to letterBox with player and playerDB
        input = new KeyboardInput(letterBox, words, this, player, playerDB);
        new Button(jbArray, letterBox, words, this);

        // Keep letterBox focused
        this.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                letterBox.requestFocusInWindow();
            }
        });
    }

    public void requestLetterBoxFocus() {
        letterBox.requestFocusInWindow();
    }

    public static JLabel makeLabel(String text, String font, int FontStyle, int textSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(font, FontStyle, textSize));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        return label;
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            javax.swing.SwingUtilities.invokeLater(() -> letterBox.requestFocusInWindow());
        }
    }

    private void _buildPage() {  // Add components
        Container c = this.getContentPane();
        letterBox = new LetterBox();
        letterBox.setBounds(133, 130, LetterBox.WIDTH, LetterBox.HEIGHT);
        c.add(letterBox);

        // Title setup
        titleLabel = new JLabel("Wordle");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 45));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setBounds(200, 40, 201, 50);
        c.add(titleLabel);

        // Rules button setup
        jbArray[0] = new JButton("SHOW RULES");
        jbArray[0].setFont(new Font("Segoe UI", Font.BOLD, 18));
        jbArray[0].setBounds(125, 560, 151, 50);
        c.add(jbArray[0]);
        
        // Sign Out button setup
        jbArray[1] = new JButton("SIGN OUT");
        jbArray[1].setFont(new Font("Segoe UI", Font.BOLD, 18));
        jbArray[1].setBounds(325, 560, 151, 50); 
        c.add(jbArray[1]);

        // Add ActionListener to Sign Out button
        jbArray[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Closes the MainFrame
                // Show the SignUpFrame
                SignUpFrame signUpFrame = new SignUpFrame();
                signUpFrame.setVisible(true); // Display the SignUpFrame
            }
        });

        // Request focus for the letterBox 
        letterBox.requestFocusInWindow();
    }
}
