/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import Logic.Player;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author noooo Responsible for persistent user profiles
 */
public class PlayerDB extends GameDB {

    public PlayerDB() {
        super(); // Call the constructor of the parent class GameDB
        createTable(); // Create the Players table if it doesn't exist
    }

    // Create the PLAYERS table in the database
    protected void createTable() {
        if (!checkTableExisting("PLAYERS")) {
            String createTableSQL = "CREATE TABLE PLAYERS ("
                    + "FULLNAME VARCHAR(255), "
                    + "EMAIL VARCHAR(255), "
                    + "PASSWORD VARCHAR(255), "
                    + "GAMES_PLAYED INT, "
                    + "GAMES_WON INT, "
                    + ")";

            try ( Statement statement = getConn().createStatement()) {
                statement.executeUpdate(createTableSQL);
                System.out.println("Players table was created.");
                insertSampleData(statement);
            } catch (SQLException ex) { // Handle SQL exceptions
                Logger.getLogger(PlayerDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Insert sample player data into the PLAYERS table
    protected void insertSampleData(Statement statement) throws SQLException {
        statement.executeUpdate("INSERT INTO PLAYERS (FULLNAME, EMAIL, PASSWORD, GAMES_PLAYED, GAMES_WON) VALUES "
                + "('Alice Smith', 'alice.smith@example.com', 'alice123', 50, 10), "
                + "('Bob Johnson', 'bob.johnson@example.com', 'bob456', 40, 34),"
                + "('Carol White', 'carol789', 'carol789', 35, 30),");
        System.out.println("Sample data was added to 'Players' table.");
    }

   
    // Check if the login credentials are valid
    protected boolean checkLogin(String email, String password) {
        boolean valid = false;
        String query = "SELECT FULLNAME FROM PLAYERS WHERE EMAIL = ? AND PASSWORD = ?";
        try ( var pstmt = getConn().prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            valid = rs.next();
            rs.close();
        } catch (SQLException ex) { // Handle SQL exceptions
            System.err.println("SQL Exception: " + ex.getMessage());
        }
        return valid;
    }
    
    // Register a new user in the PLAYERS table
    protected boolean registerUser(String fullname, String email, String password) {
        boolean success = false;
        if (!checkLogin(email, password)) {
            String insertQuery = "INSERT INTO PLAYERS (FULLNAME, EMAIL, PASSWORD, GAMES_PLAYED, GAMES_WON) VALUES (?, ?, ?, 0, 0)";
            try ( var pstmt = getConn().prepareStatement(insertQuery)) {
                pstmt.setString(1, fullname);
                pstmt.setString(2, email);
                pstmt.setString(3, password);
                pstmt.executeUpdate();
                System.out.println("User " + fullname + " was successfully created.");
                success = true;
            } catch (SQLException ex) { // Handle SQL exceptions
                System.err.println("SQL Exception: " + ex.getMessage());
            }
        } else {
            System.out.println("User " + fullname + " already exists, please use different credentials.");
        }
        return success;
    }
    
    // Update the score of a player
    protected void updateScore(Player player) {
        String updateQuery = "UPDATE PLAYERS SET GAMES_PLAYED = ?, GAMES_WON = ? WHERE EMAIL = ?";
        try ( var pstmt = getConn().prepareStatement(updateQuery)) {

            pstmt.setInt(1, player.getStats().getGamesPlayed());
            pstmt.setInt(2, player.getStats().getGamesWon());

            pstmt.setString(3, player.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException ex) { // Handle SQL exceptions
            System.err.println("SQL Exception: " + ex.getMessage());
        }
    }
    
    // Retrieve scores of all players from the PLAYERS table
    protected ArrayList<Player> getScore() {
        ArrayList<Player> players = new ArrayList<>();
        String query = "SELECT FULLNAME, GAMES_PLAYED, GAMES_WON FROM Players";
        try ( var stmt = getConn().createStatement();  ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                players.add(new Player(
                        rs.getString("FULLNAME"),
                        null, // email is not retrieved in this method
                        null, // password is not retrieved in this method
                        rs.getInt("GAMES_PLAYED"),
                        rs.getInt("GAMES_WON")
                ));
            }
        } catch (SQLException ex) { // Handle SQL exceptions
            System.err.println("SQL Exception: " + ex.getMessage());
        }
        return players;// Return the list of players
    }

    // Load a player by their email and password
    protected Player loadPlayer(String email, String password) {
        Player player = null;
        String query = "SELECT * FROM PLAYERS WHERE EMAIL = ? AND PASSWORD = ?";

        try ( var pstmt = getConn().prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    player = new Player(
                            rs.getString("FULLNAME"),
                            rs.getString("EMAIL"),
                            rs.getString("PASSWORD"),
                            rs.getInt("GAMES_PLAYED"),
                            rs.getInt("GAMES_WON")
                    );
                }
            }
        } catch (SQLException ex) { // Handle SQL exceptions
            System.err.println("SQL Exception: " + ex.getMessage());
        }

        return player;// Return the loaded player object
    }

}
