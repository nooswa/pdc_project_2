/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.UI;

import DataBase.PlayerDB;
import GUI.model.Player;
/**
 * @author Larissa Goh 18029695
* This class manages user authentication between the database and GUI.
 * Ensures sensitive data is protected by and validating logins simultaneously.
 */
public class LoginManager {
    private PlayerDB playerDB;

    public LoginManager() {
        playerDB = new PlayerDB(); // Initialize PlayerDB
    }

    // Modify authenticate to return Player instead of boolean
    public Player authenticate(String email, String password) {
        // Attempt to load the player based on email and password
        Player currentPlayer = playerDB.loadPlayer(email, password);

        // If the player is found, return the Player object; otherwise, return null
        return currentPlayer;
    }
}