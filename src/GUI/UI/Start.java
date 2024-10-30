package GUI.UI;

import DataBase.PlayerDB;
import DataBase.WordsDB;
import GUI.model.Player;

/**
 *
 * @author Noor Swadi 22167422
 * Method to launch the whole GUI
 */
public class Start {

    private static SignUpFrame signUpFrame;
    private static SignInFrame signInFrame;
    private static MainFrame mainFrame;
    private static PlayerDB playerDB = new PlayerDB(); 
    private static WordsDB wordsDB = new WordsDB(); 

    public static void main(String[] args) {
        showSignUpFrame(); // Shows SignUp frame first
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
        mainFrame = new MainFrame(player, playerDB, wordsDB); 
        mainFrame.setVisible(true);
    }
}
