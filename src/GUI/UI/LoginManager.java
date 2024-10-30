package GUI.UI;

import DataBase.PlayerDB;

/**
 * @author Larissa Goh 18029695
* This class manages user authentication between the database and GUI.
 * Ensures sensitive data is protected by and validating logins simultaneously.
 */
public class LoginManager {
    private final PlayerDB playerDB;

    public LoginManager() {
        playerDB = new PlayerDB();
    }

    public boolean authenticate(String email, String password) {
        return playerDB.checkLogin(email, password); // Calls check method to verify email and password details
    }
}
