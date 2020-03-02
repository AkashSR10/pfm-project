package sample.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {

    // Connect DB
    public static void connectDB() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:db/test.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    // Login Method Member
    public boolean memberLogin(String user, String pass) {
        try {
            // SETUP
            //*******************************************************************************************************
            String query = "select * from Member where Email = ? and Password = ? and Admin = 0";  // Set QUERY
            //*******************************************************************************************************
            Connection c = null;                                                            // Declare connection var
            ResultSet resultSet = null;                                                     // Declare resultset
            PreparedStatement preparedStatement = null;                                     // Declare preparedstatement
            Class.forName("org.sqlite.JDBC");                                               // Load JDBC
            c = DriverManager.getConnection("jdbc:sqlite:db/test.db");                  // Establish connection to DB
            c.setAutoCommit(false);                                                         // Set autocommit to false -> see JBDC docs

            // QUERY RESULTS
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();

            // FUNCTION
            if (resultSet.next()) {
                String UserID = resultSet.getString("UserID");
                String Email = resultSet.getString("Email");
                String Admin = resultSet.getString("Admin");
                System.out.printf("FOUND MEMBER UserID = %s Email = %s Admin = %s", UserID, Email, Admin);
                return true;
            }
            // CLOSE CONNECTION
            resultSet.close();
            preparedStatement.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        System.out.println("Operation memberLogin done successfully");
        return false;
    }

    // Login Method Admin
    public boolean adminLogin(String user, String pass) {
        try {
            // SETUP
            //*******************************************************************************************************
            String query = "select * from Member where Email = ? and Password = ? and Admin = 1";  // Set QUERY
            //*******************************************************************************************************
            Connection c = null;                                                            // Declare connection var
            ResultSet resultSet = null;                                                     // Declare resultset
            PreparedStatement preparedStatement = null;                                     // Declare preparedstatement
            Class.forName("org.sqlite.JDBC");                                               // Load JDBC
            c = DriverManager.getConnection("jdbc:sqlite:db/test.db");                  // Establish connection to DB
            c.setAutoCommit(false);                                                         // Set autocommit to false -> see JBDC docs

            // QUERY RESULTS
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();

            // FUNCTION
            if (resultSet.next()) {
                String UserID = resultSet.getString("UserID");
                String Email = resultSet.getString("Email");
                String Admin = resultSet.getString("Admin");
                System.out.printf("FOUND ADMIN UserID = %s Email = %s Admin = %s", UserID, Email, Admin);
                return true;
            }
            // CLOSE CONNECTION
            resultSet.close();
            preparedStatement.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        System.out.println("Operation adminLogin done successfully");
return false;
    }
}

