/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

/**
 *
 * @author noooo
 */

import java.sql.*;

public class GameDB {
    private static Connection conn = null;
    private static final String url = "jdbc:derby:wordleDB;create=true"; 
      

    public GameDB() {
        dbsetup();
    }
    
    //Establish connection to Database
    public void dbsetup() {
        try {
            conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            String tableName = "UserInfo";

            if (!checkTableExisting(tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (userid VARCHAR(12), password VARCHAR(12), score INT)");
            }
            statement.close();
        } catch (Throwable e) {
            System.out.println("Database setup error: " + e.getMessage());
        }
    }

    private boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);
            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    flag = true;
                }
            }
            rsDBMeta.close();
        } catch (SQLException ex) {
            System.out.println("Error checking table existence: " + ex.getMessage());
        }
        return flag;
    }

    public boolean checkName(String username, String password) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT password, score FROM UserInfo WHERE userid = '" + username + "'");
            if (rs.next()) {
                String pass = rs.getString("password");
                if (password.equals(pass)) {
                    return true;
                }
            } else {
                // User not found, insert new user
                statement.executeUpdate("INSERT INTO UserInfo VALUES('" + username + "', '" + password + "', 0)");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error checking username: " + ex.getMessage());
        }
        return false;
    }

    public void updateScore(String username, int score) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE UserInfo SET score=" + score + " WHERE userid='" + username + "'");
        } catch (SQLException ex) {
            System.out.println("Error updating score: " + ex.getMessage());
        }
    }
}/*pull attempt*/


