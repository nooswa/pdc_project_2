package GUI.model;

import DataBase.PlayerDB;
import GUI.model.Player;

/**
 * @author Larissa Goh 18029695
 * This class manages user authentication between the database and GUI.
 * Ensures sensitive data is protected and validates logins simultaneously.
 */
public class LoginManager {
    private final PlayerDB playerDB;

    public LoginManager() {
        playerDB = new PlayerDB(); 
    }

    public Player authenticate(String email, String password) {
        Player currentPlayer = playerDB.loadPlayer(email, password);
        return currentPlayer;
    }
}
