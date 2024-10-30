package GUI.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Larissa Goh 18029695
 * Test class for PlayerStats.
 * Ensures that all methods are working correctly.
 */
public class PlayerStatsTest {

    private PlayerStats playerStats;

    @Before
    public void setUp() {
        playerStats = new PlayerStats(10, 5); // Initializing each test to default with 10 games played, 5 games won
        System.out.println("SETUP: Games played initialized to: " + playerStats.getGamesPlayed() +
                           ", Games won initialized to: " + playerStats.getGamesWon());
    }

    /**
     * Test for getGamesPlayed method.
     */
    @Test
    public void testGetGamesPlayed() {
        System.out.println("getGamesPlayed - Initial value should be 10");
        int expected = 10;
        int result = playerStats.getGamesPlayed();
        assertEquals("Games played should be initialized to 10", expected, result);
    }

    /**
     * Test for setGamesPlayed method.
     */
    @Test
    public void testSetGamesPlayed() {
        System.out.println("setGamesPlayed - Setting games played to 15");
        int newGamesPlayed = 15;
        playerStats.setGamesPlayed(newGamesPlayed);
        System.out.println("Games played is now set to: " + playerStats.getGamesPlayed());
        assertEquals("Games played should be updated to 15", newGamesPlayed, playerStats.getGamesPlayed());
    }

    /**
     * Test for getGamesWon method.
     */
    @Test
    public void testGetGamesWon() {
        System.out.println("getGamesWon - Initial value should be 5");
        int expected = 5;
        int result = playerStats.getGamesWon();
        System.out.println("Games won retrieved as: " + result);
        assertEquals("Games won should be initialized to 5", expected, result);
    }

    /**
     * Test for setGamesWon method.
     */
    @Test
    public void testSetGamesWon() {
        System.out.println("setGamesWon - Setting games won to 8");
        int newGamesWon = 8;
        playerStats.setGamesWon(newGamesWon);
        System.out.println("Games won is now set to: " + playerStats.getGamesWon());
        assertEquals("Games won should be updated to 8", newGamesWon, playerStats.getGamesWon());
    }

    /**
     * Test for incrementGamesPlayed method.
     */
    @Test
    public void testIncrementGamesPlayed() {
        System.out.println("incrementGamesPlayed - Initial games played: " + playerStats.getGamesPlayed());
        playerStats.incrementGamesPlayed();
        int expected = 11;
        System.out.println("Games played after incrementing: " + playerStats.getGamesPlayed());
        assertEquals("Games played should increment by 1 to 11", expected, playerStats.getGamesPlayed());
    }

    /**
     * Test for incrementGamesWon method.
     */
    @Test
    public void testIncrementGamesWon() {
        System.out.println("incrementGamesWon - Initial games won: " + playerStats.getGamesWon());
        playerStats.incrementGamesWon();
        int expected = 6;
        System.out.println("Games won after incrementing: " + playerStats.getGamesWon());
        assertEquals("Games won should increment by 1 to 6", expected, playerStats.getGamesWon());
    }

    /**
     * Test for getWinRate method.
     */
    @Test
    public void testGetWinRate() {
        System.out.println("getWinRate - Calculating winrate: 5/10 games won:");
        double expectedWinRate = 50.0;
        double result = playerStats.getWinRate();
        System.out.println("Win rate calculated as: " + result + "%");
        assertEquals("Win rate should be 50.0%", expectedWinRate, result, 0.01);
    }
}
