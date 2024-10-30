package GUI.UI;

/**Handles sign in interface where users enter their email and password in the fields. Navigates to main game 
 * if successful.
 *@author Noor Swadi 22167422
 * @author Larissa Goh 18029695
 */
import GUI.model.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInFrame extends JFrame {

    private final LoginManager loginManager;
    private JTextField emailField;
    private JTextField passwordField;
    private JButton signInButton;
    private JButton backToSignUpButton;

    public SignInFrame() {
        loginManager = new LoginManager();
        setTitle("Sign In");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null); // Center the frame
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel welcomeBackLabel = new JLabel("Welcome Back!", JLabel.CENTER);
        welcomeBackLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(welcomeBackLabel, gbc);

        gbc.gridy++;
        JLabel signInLabel = new JLabel("Please Sign In", JLabel.CENTER);
        signInLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        add(signInLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(emailLabel, gbc);

        gbc.gridx = 1;
        emailField = new JTextField(15);
        add(emailField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        passwordField = new JTextField(15);
        add(passwordField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        signInButton = new JButton("Sign In");
        signInButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                handleSignIn();
            }
        });
        add(signInButton, gbc);

        gbc.gridy++;
        backToSignUpButton = new JButton("Back to Sign Up");
        backToSignUpButton.setContentAreaFilled(false);
        backToSignUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backToSignUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Start.showSignUpFrame(); // Navigate to SignUpFrame
            }
        });
        add(backToSignUpButton, gbc);
    }

    private void handleSignIn() {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        Player currentPlayer = loginManager.authenticate(email, password);

        if (currentPlayer != null) { // Login successful if player objected retrieved
            SessionManager.setPlayerEmail(email); // Store email

            JOptionPane.showMessageDialog(this, "Login successful!");

            Start.showMainFrame(currentPlayer);

            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password. Please try again.");
        }
    }
}
