package GUI.UI;

public class Start {

    private static SignUpFrame signUpFrame;
    private static SignInFrame signInFrame;
    private static Main mainFrame;

    public static void main(String[] args) {
        // Initialize frames once
        signUpFrame = new SignUpFrame();
        signInFrame = new SignInFrame();
        mainFrame = new Main();

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
