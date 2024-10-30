package GUI.UI;

public class SessionManager {
    private static String playerEmail;

    // Set the email of the logged-in player
    public static void setPlayerEmail(String email) {
        playerEmail = email;
    }

    // Get the email of the logged-in player
    public static String getPlayerEmail() {
        return playerEmail;
    }
}
