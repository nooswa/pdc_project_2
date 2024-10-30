
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package GUI.model;

import GUI.model.PlayerStats;
import GUI.model.PlayerStats;

/**@author Larissa Goh 18029695
 * @author Noor Swadi 22167422
 * Represents a player in the game, including their personal information and statistics.
 */
public class Player {

    private String fullname;
    private String email;
    private String password;
    private PlayerStats stats; // Encapsulate player statistics

    // Constructor
    public Player(String fullname, String email, String password, int gamesPlayed, int gamesWon) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.stats = new PlayerStats(gamesPlayed, gamesWon);
    }

    // Player information

    public String getFullName() {
        return fullname;
    }

    public void setFullName(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public void setStats(PlayerStats stats) {
        this.stats = stats;
    }

}
