package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBQueries {
    Connection c = null;                                                            // Declare connection var
    ResultSet rs = null;                                                           // Declare resultset
    PreparedStatement stmt = null;

    public ObservableList<Movie> getMovie() {
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        try {
            // SETUP
            //*******************************************************************************************************
            String query = "select * from movie";  // Set QUERY
            //*******************************************************************************************************
            // Declare preparedstatement
            Class.forName("org.sqlite.JDBC");                                               // Load JDBC
            c = DriverManager.getConnection("jdbc:sqlite:db/pfm.db");                  // Establish connection to DB
            c.setAutoCommit(false);                                                         // Set autocommit to false -> see JBDC docs

            // QUERY RESULTS
            stmt = c.prepareStatement(query);
            rs = stmt.executeQuery();

            // FUNCTION
            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getInt("duration"),
                        rs.getInt("year"),
                        rs.getString("writer"),
                        rs.getString("director"),
                        rs.getInt("rating"),
                        rs.getInt("num_rating"),
                        rs.getInt("adult")
                ));
            }
            // CLOSE CONNECTION
            rs.close();
            stmt.close();
            c.close();
            return movies;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return movies;
    }

    public ObservableList<User> getUser() {
        ObservableList<User> users = FXCollections.observableArrayList();
        Connection c = null;                                                            // Declare connection var
        ResultSet rs = null;                                                           // Declare resultset
        PreparedStatement preparedStatement = null;
        try {
            // SETUP
            //*******************************************************************************************************
            String query = "select * from user";  // Set QUERY
            //*******************************************************************************************************
            // Declare preparedstatement
            Class.forName("org.sqlite.JDBC");                                               // Load JDBC
            c = DriverManager.getConnection("jdbc:sqlite:db/pfm.db");                  // Establish connection to DB
            c.setAutoCommit(false);                                                         // Set autocommit to false -> see JBDC docs

            // QUERY RESULTS
            preparedStatement = c.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            // FUNCTION
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("password"),
                        rs.getInt("gender"),
                        rs.getInt("admin")
                ));
            }
            // CLOSE CONNECTION
            rs.close();
            preparedStatement.close();
            c.close();
            return users;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return users;
    }

    public boolean signUp(String email, String password, String firstName, String lastName, int gender) {

        Connection conn;


        String saveMemberInfo = "INSERT INTO Member (Email, Password, First_name, Last_name, Gender) VALUES (?,?,?,?,?)"; //Add gender and admin when done

        try {
            String url = "jdbc:sqlite:db/pfm.db"; // db parameters
            conn = DriverManager.getConnection(url);// create a connection to the database
            PreparedStatement stmt = conn.prepareStatement(saveMemberInfo); //we use a prepared statement to input the values
            stmt.setString(1, email); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(2, password); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(3, firstName); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(4, lastName); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setInt(5, gender); //this line sets a value for the first question mark in string saveMemberInfo
            //add for admin
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

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
            String query = "select * from user where email = ? and password = ? and admin = 0";  // Set QUERY
            //*******************************************************************************************************
            Connection c = null;                                                            // Declare connection var
            ResultSet resultSet = null;                                                     // Declare resultset
            PreparedStatement preparedStatement = null;                                     // Declare preparedstatement
            Class.forName("org.sqlite.JDBC");                                               // Load JDBC
            c = DriverManager.getConnection("jdbc:sqlite:db/pfm.db");                  // Establish connection to DB
            c.setAutoCommit(false);                                                         // Set autocommit to false -> see JBDC docs

            // QUERY RESULTS
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();

            // FUNCTION
            if (resultSet.next()) {
                String UserID = resultSet.getString("user_id");
                String Email = resultSet.getString("email");
                String Admin = resultSet.getString("admin");
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
            String query = "select * from user where email = ? and password = ? and admin = 1";  // Set QUERY
            //*******************************************************************************************************
            Connection c = null;                                                            // Declare connection var
            ResultSet resultSet = null;                                                     // Declare resultset
            PreparedStatement preparedStatement = null;                                     // Declare preparedstatement
            Class.forName("org.sqlite.JDBC");                                               // Load JDBC
            c = DriverManager.getConnection("jdbc:sqlite:db/pfm.db");                  // Establish connection to DB
            c.setAutoCommit(false);                                                         // Set autocommit to false -> see JBDC docs

            // QUERY RESULTS
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();

            // FUNCTION
            if (resultSet.next()) {
                String UserID = resultSet.getString("user_id");
                String Email = resultSet.getString("email");
                String Admin = resultSet.getString("admin");
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


    public int Store_id (String user, String pass) {
        try {
            // SETUP
            //*************
            String query = "select * from user where email = ? and password = ? and admin = 0";  // Set QUERY
            //*************
            Connection c = null;                                                            // Declare connection var
            ResultSet resultSet = null;                                                     // Declare resultset
            PreparedStatement preparedStatement = null;                                     // Declare preparedstatement
            Class.forName("org.sqlite.JDBC");                                               // Load JDBC
            c = DriverManager.getConnection("jdbc:sqlite:db/pfm.db");                  // Establish connection to DB
            c.setAutoCommit(false);                                                         // Set autocommit to false -> see JBDC docs

            // QUERY RESULTS
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();

            // FUNCTION
            if (resultSet.next()) {
                int UserID = resultSet.getInt("user_id");
                String Email = resultSet.getString("email");
                String Admin = resultSet.getString("admin");
                System.out.println("");
                System.out.printf("FOUND MEMBER UserID = %s ", UserID);
                return UserID ;
            }
            // CLOSE CONNECTION
            resultSet.close();
            preparedStatement.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return -1;
        }
        System.out.println("Operation memberLogin done successfully");
        return -1;
    }




}




