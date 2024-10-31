/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.UI;

import GUI.model.SessionManager;
import DataBase.PlayerDB;
import DataBase.WordsDB;
import GUI.model.PlayerStats;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * @author Noor Swadi 22167422
 * @Larissa Goh 18029695 This class displays a reminder pop-up with customisable
 * text, button, and dimensions.
 */
public class PopUpWindow extends JDialog {

    private JButton jb; // Instance of the button in the PopUpWindow. Mainly used for adding a MouseListener outside the class
    private WordsDB wordsDB; // Instance of WordsDB

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

    // Nested class that disposes of the JDialog when clicked.
    static class ClickClose implements ActionListener {
        private JDialog window;

        public ClickClose(JDialog window) {
            this.window = window;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            window.dispose();
        }
    }

    // Nested class to restart the game when clicked
    static class ClickRestart implements ActionListener {
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

    // Static inner class for the result popup that shows when you win or lose
    public static class PopRes extends PopUpWindow {
        private JFrame mainFrame;
        private JButton playAgainButton;
        private KeyboardInput keyboardInput;

        public PopRes(JFrame jFrame, WordsDB wordsDB, boolean win, KeyboardInput keyboardInput) {
            super(400, 430, jFrame, "Congratulations", wordsDB);
            this.mainFrame = jFrame;
            this.keyboardInput = keyboardInput;

            this.setResizable(false);
            this.setLayout(null);
            this.addWindowListener(new CloseRefresh());

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (screenSize.width - this.getWidth()) / 2;
            int y = (screenSize.height - this.getHeight()) / 2;
            this.setLocation(x, y);

            Container c = this.getContentPane();

            JLabel line1 = MainFrame.makeLabel(win ? "Success" : "Failed", "Serif", Font.BOLD, 40);
            line1.setForeground(win ? new Color(121, 167, 107) : new Color(255, 0, 0));
            line1.setBounds((this.getWidth() - 200) / 2, 20, 200, 50);
            c.add(line1);

            JLabel line2 = MainFrame.makeLabel("in guessing", "Segoe UI", Font.PLAIN, 16);
            line2.setForeground(Color.BLACK);
            line2.setBounds((this.getWidth() - 120) / 2, 65, 120, 30);
            c.add(line2);

            JLabel line3 = MainFrame.makeLabel(wordsDB.getSecretWord(), "Segoe UI", Font.BOLD, 40);
            line3.setForeground(Color.BLACK);
            line3.setBounds((this.getWidth() - 200) / 2, 95, 200, 50);
            c.add(line3);

            JLabel attemptsLabel = MainFrame.makeLabel("Attempts: " + (Position.getRow() + 1), "Segoe UI", Font.PLAIN, 16);
            attemptsLabel.setForeground(Color.BLACK);
            attemptsLabel.setBounds((this.getWidth() - 120) / 2, 140, 120, 30);
            c.add(attemptsLabel);

            // Retrieve and display player score
            PlayerDB playerDB = new PlayerDB();
            String playerEmail = SessionManager.getPlayerEmail();
            PlayerStats stats = (playerEmail != null) ? playerDB.getLoggedInPlayerScore(playerEmail) : null;
            String scoreText = (stats != null) 
                ? String.format("<html>Games Played: %d<br>Games Won: %d<br>Win Rate: %.2f%%</html>",
                                stats.getGamesPlayed(), stats.getGamesWon(), stats.getWinRate())
                : "<html>Unable to load player stats.</html>";

            JLabel scoreLabel = new JLabel(scoreText);
            scoreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            scoreLabel.setForeground(Color.BLACK);
            scoreLabel.setBounds(0, 180, this.getWidth(), 80);
            scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
            c.add(scoreLabel);

            playAgainButton = new JButton("Play Again");
            playAgainButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
            playAgainButton.setBounds(85, 290, 130, 50);
            playAgainButton.addActionListener(new ClickRestart(this, keyboardInput));
            c.add(playAgainButton);

            JButton signOutButton = new JButton("Sign Out");
            signOutButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
            signOutButton.setBounds(225, 290, 130, 50);
            signOutButton.addActionListener(new SignOutAction());
            c.add(signOutButton);
        }

        private class SignOutAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                dispose();
                SignUpFrame signUpFrame = new SignUpFrame();
                signUpFrame.setVisible(true);
            }
        }
    }

    private static class CloseRefresh implements java.awt.event.WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {}
        @Override
        public void windowClosing(WindowEvent e) {
            LetterBox.refresh();
        }
        @Override public void windowClosed(WindowEvent e) {}
        @Override public void windowIconified(WindowEvent e) {}
        @Override public void windowDeiconified(WindowEvent e) {}
        @Override public void windowActivated(WindowEvent e) {}
        @Override public void windowDeactivated(WindowEvent e) {}
    }
}

