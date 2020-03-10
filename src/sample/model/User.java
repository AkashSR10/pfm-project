package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Movie;

import java.sql.*;

public class User {

    // COLUMNS
    public int userID;
    public String email; //private
    public String firstName;
    public String lastName;
    public String password; //private
    public int gender;
    public int admin;

    public User(int userID, String email, String firstName, String lastName, String password, int gender, int admin) {
        this.userID = userID;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.gender = gender;
        this.admin = admin;
    }

    public User() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    // FUNCTIONS SQL

    ResultSet rs = null;
    PreparedStatement stmt = null;


    /**
     * Connect to a  database
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

    public String Emailz = null;



    public int userIDMember = 0;
    public int userIDAdmin = 0;

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
                int userID = Integer.parseInt(rs.getString("user_id"));
                String Email = rs.getString("email");
                String Admin = rs.getString("admin");
                System.out.printf("FOUND ADMIN UserID = %s Email = %s Admin = %s", userID, Email, Admin);
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
                userIDAdmin = Integer.parseInt(rs.getString("user_id"));
                String Email = rs.getString("email");
                String Admin = rs.getString("admin");
                System.out.printf("FOUND ADMIN UserID = %s Email = %s Admin = %s", userIDAdmin, Email, Admin);
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
                        rs.getInt("num_rating")
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

