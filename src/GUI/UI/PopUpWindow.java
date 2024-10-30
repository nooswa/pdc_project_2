/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.UI;

import DataBase.PlayerDB;
import GUI.model.LetterBox;
import DataBase.WordsDB;
import GUI.model.PlayerStats;
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
// Updated ClickRestart to reset the game using KeyboardInput's resetGame method
class ClickRestart implements ActionListener {
    private JDialog window;
    private KeyboardInput keyboardInput;

    public ClickRestart(JDialog window, KeyboardInput keyboardInput) {
        this.window = window;
        this.keyboardInput = keyboardInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LetterBox.refresh();  // Call refresh() to clear the board
        keyboardInput.resetGame();  // Ensure game state is reset
        window.dispose();  // Close the popup
    }
}



// Nested class for the pop up window that shows when you win or lose
class PopRes extends PopUpWindow {

    private JFrame mainFrame; // Reference to the main frame
    private JButton playAgainButton; // Declare playAgainButton as an instance variable
    private KeyboardInput keyboardInput;

    public PopRes(JFrame jFrame, WordsDB wordsDB, boolean win, KeyboardInput keyboardInput) {
        super(400, 500, jFrame, "Congratulations", wordsDB); // Set larger dimensions
        this.mainFrame = jFrame; // Assign the main frame reference
        this.keyboardInput = keyboardInput; // Store keyboardInput reference
        this.setResizable(false); // Disable resizing
        this.setLayout(null); // Keep null layout for fixed positioning
        this.addWindowListener(new CloseRefresh());

        // Center the popup on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(x, y);

        Container c = this.getContentPane();

        // Title setup based on win condition
        JLabel line1;
        if (win) {
            line1 = MainFrame.makeLabel("Success", "Serif", Font.BOLD, 40);
            line1.setForeground(new Color(121, 167, 107));
        } else {
            this.setTitle("Oh no");
            line1 = MainFrame.makeLabel("Failed", "Serif", Font.BOLD, 40);
            line1.setForeground(new Color(121, 124, 126));
        }
        line1.setBounds(100, 20, 200, 50); // Centered within the popup width
        c.add(line1);

        // Additional message setup
        JLabel line2 = MainFrame.makeLabel("in guessing", "Segoe UI", Font.PLAIN, 16);
        line2.setForeground(Color.BLACK);
        line2.setBounds(140, 65, 120, 30); // Centered within the popup width
        c.add(line2);

        // Display the secret word
        JLabel line3 = MainFrame.makeLabel(wordsDB.getSecretWord(), "Segoe UI", Font.BOLD, 40);
        line3.setForeground(Color.BLACK);
        line3.setBounds(100, 95, 200, 50); // Centered within the popup width
        c.add(line3);

        // Display the number of attempts taken
        JLabel attemptsLabel = MainFrame.makeLabel("Attempts: " + (Position.getRow() + 1), "Segoe UI", Font.PLAIN, 16);
        attemptsLabel.setForeground(Color.BLACK);
        attemptsLabel.setBounds(140, 140, 120, 30); // Centered within the popup width
        c.add(attemptsLabel);

        // Retrieve and display player score
        PlayerDB playerDB = new PlayerDB();
        String playerEmail = SessionManager.getPlayerEmail(); // Get logged-in player's email
        PlayerStats stats = (playerEmail != null) ? playerDB.getLoggedInPlayerScore(playerEmail) : null;

        String scoreText;
        if (stats != null) {
            // Format the score information if stats are available
            scoreText = String.format(
                "<html>Games Played: %d<br>Games Won: %d<br>Win Rate: %.2f%%</html>",
                stats.getGamesPlayed(),
                stats.getGamesWon(),
                stats.getWinRate()
            );
        } else {
            // Display an error message if stats are null
            scoreText = "<html>Unable to load player stats.</html>";
        }

        // Score label
        JLabel scoreLabel = new JLabel(scoreText);
        scoreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setBounds(140,180, 200, 80); // Positioned below Attempts label
        c.add(scoreLabel);

        // Play Again button setup
        playAgainButton = new JButton("Play Again"); // Initialize the instance variable
        playAgainButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        playAgainButton.setBounds(85, 290, 130, 50); // Centered horizontally
        c.add(playAgainButton);

        // Use ClickRestart with keyboardInput's resetGame method
        playAgainButton.addActionListener(new ClickRestart(this, keyboardInput)); 

        // Sign Out button setup
        JButton signOutButton = new JButton("Sign Out");
        signOutButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        signOutButton.setBounds(225, 290, 130, 50); // Centered horizontally next to Play Again button
        c.add(signOutButton);

        // Action listener for sign out
        signOutButton.addActionListener(new SignOutAction());
    }
 
    // Nested class to handle sign out action
    private class SignOutAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Close the main frame
            mainFrame.dispose(); // Closes the MainFrame

            // Close current PopRes window
            dispose(); // Closes the PopRes

            // Show the SignUpFrame
            SignUpFrame signUpFrame = new SignUpFrame();
            signUpFrame.setVisible(true); // Display the SignUpFrame
        }
        
    }

    // Nested static class that refreshes the game upon closing the PopUpWindow
    private static class CloseRefresh implements java.awt.event.WindowListener {
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