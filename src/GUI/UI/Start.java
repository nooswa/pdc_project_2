/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.UI;
/**
 *
 * @author Noor Swadi 22167422
 * Method to launch the whole GUI
 */
public class Start {

    private static SignUpFrame signUpFrame;
    private static SignInFrame signInFrame;
    private static MainFrame mainFrame;

    public static void main(String[] args) {
        // Initialize frames once
        signUpFrame = new SignUpFrame();
        signInFrame = new SignInFrame();
        mainFrame = new MainFrame();

        // Show the SignUpFrame first
        showSignUpFrame();
    }

    public static void showSignUpFrame() {
        // Hide other frames and show SignUpFrame
        signInFrame.setVisible(false);
        mainFrame.setVisible(false);
        signUpFrame.setVisible(true);
    }

    public static void showSignInFrame() {
        // Hide other frames and show SignInFrame
        signUpFrame.setVisible(false);
        mainFrame.setVisible(false);
        signInFrame.setVisible(true);
    }

    public static void showMainFrame() {
        // Hide other frames and show Main
        signUpFrame.setVisible(false);
        signInFrame.setVisible(false);
        mainFrame.setVisible(true);
    }
}
