package GUI;

import javax.swing.JFrame;

public class Start {

    private static JFrame mainFrame;

    public static void main(String[] args) {
        // Initialize the main frame
        mainFrame = new JFrame("Wordle App");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 400); // Set the preferred size
        mainFrame.setLocationRelativeTo(null); // Center the window
        
        // Show the SignUp panel first
        showSignUp();
        
        mainFrame.setVisible(true);
    }

    public static void showSignUp() {
        mainFrame.getContentPane().removeAll();
        
        SignUp signUpPanel = new SignUp();
        mainFrame.add(signUpPanel);
        
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public static void showSignIn() {
        mainFrame.getContentPane().removeAll();
        
        SignIn signInPanel = new SignIn();
        mainFrame.add(signInPanel);
        
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
