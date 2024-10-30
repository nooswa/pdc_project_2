package GUI.UI;
/**
 * This class manages the current session state for the current player.
 * Stores logged in laters email 
 * 
 * @author Larissa Goh 18029695
 */
public class SessionManager {
    private static String playerEmail;

    public static void setPlayerEmail(String email) {
        playerEmail = email;
    }

    public static String getPlayerEmail() {
        return playerEmail;
    }
}
