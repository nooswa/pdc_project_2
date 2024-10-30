package GUI.model;

/**@author Larissa Goh 18029695
 *  Encapsulates and shows all statistics that are associated with a player.
 */
public class PlayerStats {
    private int gamesPlayed;
    private int gamesWon;

    // Constructor
    public PlayerStats(int gamesPlayed, int gamesWon) {
        this.gamesPlayed = Math.max(0, gamesPlayed);  // Prevents negative values
        this.gamesWon = Math.max(0, gamesWon);        
    }

    // Getter for games played
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    // Setter for games played with validation
    public void setGamesPlayed(int gamesPlayed) {
        if (gamesPlayed >= 0) {
            this.gamesPlayed = gamesPlayed;
        }
    }

    // Getter for games won
    public int getGamesWon() {
        return gamesWon;
    }

    // Setter for games won with validation
    public void setGamesWon(int gamesWon) {
        if (gamesWon >= 0) {
            this.gamesWon = gamesWon;
        }
    }

    // Increment games played by 1
    public void incrementGamesPlayed() {
        gamesPlayed++;
    }

    // Increment games won by 1
    public void incrementGamesWon() {
        gamesWon++;
    }

    // Calculate the win rate as a percentage
    public double getWinRate() {
        return gamesPlayed > 0 ? (double) gamesWon / gamesPlayed * 100 : 0;
    }
}
