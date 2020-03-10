package sample.model;

import sample.User;

import java.sql.*;

public class Member extends User {
    ResultSet rs = null;                                                     // Declare resultset
    PreparedStatement stmt = null;

    public Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:db/pfm.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void addMovieWatchlist(int userID, int movieID) {

        PreparedStatement stmt = null;
        Connection conn = null;

        String saveMemberInfo = "INSERT INTO watchlist (user_id, movieID) VALUES (?,?)";
        try {
            String url = "jdbc:sqlite:db/pfm.db"; // db parameters
            conn = DriverManager.getConnection(url);// create a connection to the database
            stmt = conn.prepareStatement(saveMemberInfo); //we use a prepared statement to input the values
            stmt.setInt(1, userID); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setInt(2, movieID); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.executeUpdate();
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

    public void deleteMovieWatchlist(int userID, int movieID) {

        PreparedStatement stmt = null;
        Connection conn = null;

        String saveMemberInfo = "DELETE FROM watchlist where user_id = ? and movieID = ?";
        try {
            String url = "jdbc:sqlite:db/pfm.db"; // db parameters
            conn = DriverManager.getConnection(url);// create a connection to the database
            stmt = conn.prepareStatement(saveMemberInfo); //we use a prepared statement to input the values
            stmt.setInt(1, userID); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setInt(2, movieID); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.executeUpdate();
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
