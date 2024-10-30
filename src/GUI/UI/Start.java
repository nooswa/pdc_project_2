package GUI.UI;

import DataBase.PlayerDB;
import DataBase.WordsDB;
import GUI.model.Player;

public class Start {

    private static SignUpFrame signUpFrame;
    private static SignInFrame signInFrame;
    private static MainFrame mainFrame;
    private static PlayerDB playerDB = new PlayerDB(); // Shared PlayerDB instance
    private static WordsDB wordsDB = new WordsDB(); // Shared WordsDB instance

    public static void main(String[] args) {
        // Show the SignUpFrame first
        showSignUpFrame();
    }

    public static void showSignUpFrame() {
        signUpFrame = new SignUpFrame();
        signUpFrame.setVisible(true);
    }

    public static void showSignInFrame() {
        signInFrame = new SignInFrame();
        signInFrame.setVisible(true);
    }

    public static void showMainFrame(Player player) {
        mainFrame = new MainFrame(player, playerDB, wordsDB); // Reuse shared PlayerDB and WordsDB
        mainFrame.setVisible(true);
    }
}
