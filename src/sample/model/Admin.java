package sample.model;

import sample.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Admin extends User {

    public static void connectDB() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:db/pfm.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public void deleteUser(int userID) {

        PreparedStatement stmt = null;
        Connection conn = null;

        String saveMemberInfo = "DELETE FROM user where user_id = ?"; //Add gender and admin when done

        try {
            String url = "jdbc:sqlite:db/pfm.db"; // db parameters
            conn = DriverManager.getConnection(url);// create a connection to the database
            stmt = conn.prepareStatement(saveMemberInfo); //we use a prepared statement to input the values
            stmt.setInt(1, userID); //this line sets a value for the first question mark in string saveMemberInfo
            //add for admin
            stmt.executeUpdate();
            System.out.println("UserID " + userID + " has been deleted");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
    }

    public void addUser(String email, String firstName, String lastName, String password, int gender, int admin) {

        PreparedStatement stmt = null;
        Connection conn = null;

        String saveMemberInfo = "INSERT INTO user (email, password, first_name, last_name, gender, admin) VALUES (?,?,?,?,?,?)";
        try {
            String url = "jdbc:sqlite:db/pfm.db"; // db parameters
            conn = DriverManager.getConnection(url);// create a connection to the database
            stmt = conn.prepareStatement(saveMemberInfo); //we use a prepared statement to input the values
            stmt.setString(1, email); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(2, password); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(3, firstName); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(4, lastName); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setInt(5, gender); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setInt(6, admin); //this line sets a value for the first question mark in string saveMemberInfo

            //add for admin
            stmt.executeUpdate();
            System.out.println("User" + email + " has been added");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }

    }

    public void updateUser(int userid, String email, String firstName, String lastName, String password, int gender, int admin) {

        PreparedStatement stmt = null;
        Connection conn = null;

        String saveMemberInfo = "UPDATE user set email = ?, password = ?, first_name = ?, last_name = ?, gender = ?, admin = ? where user_id = " + userid + "";
        try {
            String url = "jdbc:sqlite:db/pfm.db"; // db parameters
            conn = DriverManager.getConnection(url);// create a connection to the database
            stmt = conn.prepareStatement(saveMemberInfo); //we use a prepared statement to input the values
            stmt.setString(1, email); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(2, password); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(3, firstName); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(4, lastName); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setInt(5, gender); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setInt(6, admin); //this line sets a value for the first question mark in string saveMemberInfo

            //add for admin
            stmt.executeUpdate();
            System.out.println("UserID " + userID + " has been updated");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }

    }

    public void deleteMovie(int movieID) {

        PreparedStatement stmt = null;
        Connection conn = null;

        String saveMemberInfo = "DELETE FROM movie where movie_id = ?"; //Add gender and admin when done

        try {
            String url = "jdbc:sqlite:db/pfm.db"; // db parameters
            conn = DriverManager.getConnection(url);// create a connection to the database
            stmt = conn.prepareStatement(saveMemberInfo); //we use a prepared statement to input the values
            stmt.setInt(1, movieID); //this line sets a value for the first question mark in string saveMemberInfo
            //add for admin
            stmt.executeUpdate();
            System.out.println("MovieID " + movieID + " has been deleted");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
    }

    public void updateMovie(int movieID, String title, String genre, int duration, int year, String writer, String director, int rating, int numRating) {

        PreparedStatement stmt = null;
        Connection conn = null;

        String saveMemberInfo = "UPDATE movie set title = ?, genre = ?, duration = ?, year = ?, writer = ?, director = ?, rating = ?, num_rating = ? where movie_id = " + movieID + "";
        try {
            String url = "jdbc:sqlite:db/pfm.db"; // db parameters
            conn = DriverManager.getConnection(url);// create a connection to the database
            stmt = conn.prepareStatement(saveMemberInfo); //we use a prepared statement to input the values
            stmt.setString(1, title); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(2, genre); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setInt(3, duration); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setInt(4, year); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(5, writer); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(6, director); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setInt(7, rating); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setInt(8, numRating); //this line sets a value for the first question mark in string saveMemberInfo

            //add for admin
            stmt.executeUpdate();
            System.out.println("MovieID " + movieID + " has been updated");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }

        }
    }

    public void addMovie(String title, String genre, int duration, int year, String writer, String director, int rating, int numRating) {

        PreparedStatement stmt = null;
        Connection conn = null;

        String saveMemberInfo = "INSERT INTO movie (title, genre, duration, year, writer, director, rating, num_rating) VALUES (?,?,?,?,?,?,?,?)";
        try {
            String url = "jdbc:sqlite:db/pfm.db"; // db parameters
            conn = DriverManager.getConnection(url);// create a connection to the database
            stmt = conn.prepareStatement(saveMemberInfo); //we use a prepared statement to input the values
            stmt.setString(1, title); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(2, genre); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setInt(3, duration); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setInt(4, year); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(5, writer); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(6, director); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setInt(7, rating); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setInt(8, numRating); //this line sets a value for the first question mark in string saveMemberInfo

            //add for admin
            stmt.executeUpdate();
            System.out.println("MovieID " + title + " has been updated");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }

        }
    }


}