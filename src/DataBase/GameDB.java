/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author noooo
 */
public class GameDB {

    private static final String username = "wordle2"; //DB username
    private static final String password = "wordle2"; //DB password
    private static final String URL = "jdbc:derby:wordleDB; create=true";  //URL of the DB host
    private static Connection conn = null;
   
     
    public GameDB() {
        dbSetup();// Set up the database connection
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
        try {
            if (conn == null) {
                conn = DriverManager.getConnection(URL, username, password);
                System.out.println(URL + " get CONNECTED...");
                System.out.println(getConn());
            }
        } catch (SQLException ex) {// Handle SQL exceptions
            System.out.println(ex.getMessage());
        }
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

    public Connection getConn() {
        return this.conn;
    }
    
    // Close the database connection
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) { // Handle SQL exceptions
                System.out.println(ex.getMessage());
            }
        }
    }

}
