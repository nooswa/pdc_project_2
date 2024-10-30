package GUI.model;

/**@author Larissa Goh 18029695
 *  Encapsulates and shows all statistics that are associated with a player.
 */
public class PlayerStats {
    private int gamesPlayed;
    private int gamesWon;

    // Constructor
    public PlayerStats(int gamesPlayed, int gamesWon) {
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public void incrementGamesPlayed() {
        gamesPlayed++;
    }

    public void incrementGamesWon() {
        gamesWon++;
    }

    // Example of a method that calculates win rate
    public double getWinRate() {
        return gamesPlayed > 0 ? (double) gamesWon / gamesPlayed * 100 : 0;
    }
}
