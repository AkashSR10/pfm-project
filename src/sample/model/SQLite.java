package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.User;

import java.sql.*;
import java.util.ArrayList;

public class SQLite {
    ResultSet rs = null;                                                     // Declare resultset
    PreparedStatement stmt = null;

    /**
     * Connect to a sample database
     */

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

    /**
     * Delete
     */

    public void delete(int id) {
        String sql = "DELETE FROM user where user_id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, id);
            //update
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Member Login
     */

    public boolean memberLogin(String user, String pass) {
        String sql = "select * from user where email = ? and password = ? and admin = 0";
        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // QUERY RESULTS
            stmt.setString(1, user);
            stmt.setString(2, pass);
            rs = stmt.executeQuery();
            // FUNCTION
            if (rs.next()) {
                String UserID = rs.getString("user_id");
                String Email = rs.getString("email");
                String Admin = rs.getString("admin");
                System.out.printf("FOUND ADMIN UserID = %s Email = %s Admin = %s", UserID, Email, Admin);
                return true;
            }
            // CLOSE CONNECTION
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        System.out.println("Operation adminLogin done successfully");
        return false;
    }
    /**
     * Admin Login
     */

    public boolean adminLogin(String user, String pass) {
        String sql = "select * from user where email = ? and password = ? and admin = 1";
        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // QUERY RESULTS
            stmt.setString(1, user);
            stmt.setString(2, pass);
            rs = stmt.executeQuery();
            // FUNCTION
            if (rs.next()) {
                String UserID = rs.getString("user_id");
                String Email = rs.getString("email");
                String Admin = rs.getString("admin");
                System.out.printf("FOUND ADMIN UserID = %s Email = %s Admin = %s", UserID, Email, Admin);
                return true;
            }
            // CLOSE CONNECTION
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        System.out.println("Operation adminLogin done successfully");
        return false;
    }

    /**
     * get User
     */

    public ObservableList<User> getUser() {
        ObservableList<User> users = FXCollections.observableArrayList();
        String sql = "select * from user";  // Set QUERY
        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // QUERY RESULTS
            rs = stmt.executeQuery();

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
            stmt.close();
            conn.close();
            return users;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return users;
    }

    /**
     * get Movie
     */

    public ObservableList<Movie> getMovie() {
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        String sql = "select * from movie";  // Set QUERY
        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // QUERY RESULTS
            rs = stmt.executeQuery();                                                   // Set autocommit to false -> see JBDC docs

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
            conn.close();
            return movies;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return movies;
    }

    public ObservableList<Movie> getWatchlist(int userID) {
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        String sql = "select movie_id, title, genre, duration, year, writer, director, rating, num_rating, adult from movie join watchlist on (movie.movie_id = watchlist.movieID) where user_id = ?";  // Set QUERY
        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // QUERY RESULTS
            stmt.setInt(1, userID);
            rs = stmt.executeQuery();                                                   // Set autocommit to false -> see JBDC docs

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
            conn.close();
            return movies;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return movies;
    }

}
