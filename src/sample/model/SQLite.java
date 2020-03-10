package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.User;

import java.sql.*;

public class SQLite extends Movie {
    ResultSet rs = null;                                                     // Declare resultset
    PreparedStatement stmt = null;
    public int userID = 0;

    // Connect to DB
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


    // Get list all users
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

    // Get list all movies
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

    // Get list all movies from watchlist
    public ObservableList<Movie> getWatchlist(int userID) {
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        String sql = "select movie_id, title, genre, duration, year, writer, director, rating, num_rating from movie join watchlist on (movie.movie_id = watchlist.movieID) where user_id = ?";  // Set QUERY
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

    // Get random roulette Movie
    public boolean rouletteMovie(String genreInput, int yearInput, int ratingInput) {
        String sql = "select * from movie where genre = ? and year >= ? and rating >= ? ORDER BY RANDOM () LIMIT 1";
        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, genreInput);
            stmt.setInt(2, yearInput);
            stmt.setInt(3, ratingInput);
            rs = stmt.executeQuery();
            // FUNCTION
            while (rs.next()) {
                movieID = rs.getInt("movie_id");
                title = rs.getString("title");
                genre = rs.getString("genre");
                duration = rs.getInt("duration");
                year = rs.getInt("year");
                writer = rs.getString("writer");
                director = rs.getString("director");
                rating = rs.getInt("rating");
                numRating = rs.getInt("num_rating");
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
        title = "Not found ☹";
        rating = 0;
        System.out.println("Operation adminLogin done successfully");
        return false;
    }

}
