/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

/**
 *
 * @author noooo
 */

import Logic.Player;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class WordleDB {
    private Connection conn;
    private final String dbURL = "jdbc:derby:wordleDB;create=true"; 
    private final String dbUsername = "wordle2";
    private final String dbPassword = "wordle2";
    protected Statement statement;

    protected WordleDB() {
        if (!connect()) {
            showDatabaseError();
        }
        dbSetup();
    }
    
    private void showDatabaseError() {
        Object[] options = {"OK"};
        JOptionPane.showOptionDialog(null, "Unable to connect to the database. " +
                "Another instance may already be open. In embedded mode, only one process can access the Derby database. The application requires the database to function.", 
                "Database Connection Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, null);
        System.exit(0);
    }
    
    protected void dbSetup() {
        try {
            statement = conn.createStatement();
            createPlayersTable();
            WordleLists wordleLists = new WordleLists(statement); // Create an instance of WordleLists
            wordleLists.setupLists(); // Set up the word lists
            statement.close();
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    protected boolean connect() {
        boolean success = false;
        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            System.out.println("DATABASE: Connected");
            success = true;
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return success;
    }
    
     protected void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
     }

    protected void createPlayersTable() {
        if (!checkTableExisting("Players")) {
            try {
                statement.executeUpdate("CREATE TABLE Players (FULLNAME VARCHAR(255), EMAIL VARCHAR(255), PASSWORD VARCHAR(255), SCORE INTEGER)");
                System.out.println("Players table was created.");
                insertSampleData();
            } catch (SQLException ex) {
                Logger.getLogger(WordleDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }

    protected void insertSampleData() throws SQLException {
        statement.executeUpdate("INSERT INTO Players (FULLNAME, EMAIL, PASSWORD, SCORE) VALUES "
                + "('Alice Smith', 'alice.smith@example.com', 'alice123', 50), "
                + "('Bob Johnson', 'bob.johnson@example.com', 'bob456', 40), "
                + "('Carol White', 'carol.white@example.com', 'carol789', 35)");
        System.out.println("Sample data was added to 'Players' table.");
    }

    protected boolean checkTableExisting(String newTableName) {
        boolean tableExisting = false;
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);
            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.equalsIgnoreCase(newTableName)) {
                    tableExisting = true;
                    break;
                }
            }
            rsDBMeta.close();
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
        return tableExisting;
    }

    protected boolean checkLogin(String email, String password) {
        boolean valid = false;
        try {
            ResultSet rs = statement.executeQuery("SELECT FULLNAME FROM Players WHERE EMAIL = '" + email + "' AND PASSWORD = '" + password + "'");
            if (rs.next()) {
                valid = true; // Login is valid
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
        return valid;
    }

    protected boolean registerUser(String fullname, String email, String password) {
        boolean success = false;
        try {
            if (!checkLogin(email, password)) {
                statement.executeUpdate("INSERT INTO Players (FULLNAME, EMAIL, PASSWORD, SCORE) VALUES ('" + fullname + "', '" + email + "', '" + password + "', 0)");
                success = true;
                System.out.println("User " + fullname + " was successfully created.");
            } else {
                System.out.println("User " + fullname + " already exists, please use different credentials.");
            }
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
        return success;
    }

    protected void updateScore(Player player) {
        try {
            statement.executeUpdate("UPDATE Players SET SCORE = " + player.getScore() + " WHERE EMAIL = '" + player.getEmail() + "'");
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
    }

    protected ArrayList<Player> getScore() {
        ArrayList<Player> players = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM Players");
            while (rs.next()) {
                players.add(new Player(rs.getString("FULLNAME"), rs.getInt("SCORE")));
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex);
        }
        return players;
    }

    protected Player loadPlayer(String email, String pass) {
        Player player = null;
        if (checkLogin(email, pass)) {
            try {
                ResultSet rs = statement.executeQuery("SELECT * FROM Players WHERE EMAIL = '" + email + "' AND PASSWORD = '" + pass + "'");
                if (rs.next()) {
                    player = new Player(rs.getString("FULLNAME"), rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getInt("SCORE"));
                }
                rs.close();
            } catch (SQLException ex) {
                System.err.println("SQL Exception: " + ex);
            }
        }
        return player;
    }
}



