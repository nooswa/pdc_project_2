/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        return playerDB.checkLogin(email, password); // Calls heck method to verify email and password details
    }
}
 
