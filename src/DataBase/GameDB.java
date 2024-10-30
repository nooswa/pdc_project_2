package DataBase;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Database connection handler for Wordle game
 * Ensures a single connection is used across the application.
 * @author Noor Swadi 22167422
 */
public class GameDB {

    private static final String username = "wordle2"; // DB username
    private static final String password = "wordle2"; // DB password
    private static final String URL = "jdbc:derby:wordleDB;create=true"; // URL of the DB host
    private static Connection conn = null; // Singular shared connection

    public GameDB() {
        dbSetup(); // Set up the database connection
    }

    // Show an error message if database connection fails
    private void showDatabaseError() {
        Object[] options = {"OK"};
        JOptionPane.showOptionDialog(null, "Unable to connect to the database. "
                + "Another instance may already be open. In embedded mode, only one process can access the Derby database. The application requires the database to function.",
                "Database Connection Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, null);
        System.exit(0);
    }

    //Establish connection to database
    private void dbSetup() {
        if (conn == null) { // Opens connection if it doesn't already exist
            try {
                conn = DriverManager.getConnection(URL, username, password);
                System.out.println(URL + " CONNECTED...");
            } catch (SQLException ex) {
                System.err.println("SQL Exception during connection setup: " + ex.getMessage());
                showDatabaseError();
            }
        }
    }

    public static Connection getConn() {
        if (conn == null) {
            new GameDB(); 
        }
        return conn;
    }

    // Check if a specific table exists in the database
    protected boolean checkTableExisting(String tableName) {
        boolean tableExists = false;
        try {
            DatabaseMetaData dbMetaData = getConn().getMetaData();
            ResultSet resultSet = dbMetaData.getTables(null, null, tableName.toUpperCase(), null);
            tableExists = resultSet.next();
            resultSet.close();
        } catch (SQLException ex) { // Handle SQL exceptions
            System.err.println("SQL Exception: " + ex.getMessage());
        }
        return tableExists;
    }

    // Close the database connection 
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Database connection closed.");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
