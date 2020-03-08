package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Watchlist {


//    // Connect DB
//    public static void connectDB() {
//        Connection c = null;
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:db/test.db");
//        } catch (Exception e) {
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
//        }
//        System.out.println("Opened database successfully");
//    }
//
//    // Retrieve watchlist
//
//    public static int watchlist (int userID) {
//
//        List<Movie> watchlist = new ArrayList();
//
//        try {
//            // SETUP
//            //*************
//            String query = "select * from watchlist where user_id = ? ";  // Set QUERY
//            //*************
//            Connection c = null;                                                            // Declare connection var
//            ResultSet rs = null;                                                     // Declare resultset
//            PreparedStatement preparedStatement = null;                                     // Declare preparedstatement
//            Class.forName("org.sqlite.JDBC");                                               // Load JDBC
//            c = DriverManager.getConnection("jdbc:sqlite:db/pfm.db");                  // Establish connection to DB
//            c.setAutoCommit(false);                                                         // Set autocommit to false -> see JBDC docs
//
//            // QUERY RESULTS
//            preparedStatement = c.prepareStatement(query);
//            preparedStatement.setInt(1, userID);
//            rs = preparedStatement.executeQuery();
//
//            // FUNCTION
//            while (rs.next()) {
//                Movie movie = new Movie();
//                movie.movieID = rs.getInt("Movie_id");
//                int UserID = rs.getInt("user_id");
//                int MovieID = rs.getInt("Movie_id");
//
//                System.out.printf("FOUND movie: %s for user: %s \n",MovieID, UserID);
//                watchlist.add(movie) ;
//            }
//            ObservableList<Movie> data = FXCollections.observableArrayList();
//            data.addAll(watchlist);
//            System.out.println("number of movies in data: " + data.size());
//            System.out.println(data.get(0).title);
//            watchlistView.setItems(data);
//
//
//
//
//            // CLOSE CONNECTION
//            rs.close();
//            preparedStatement.close();
//            c.close();
//
//
//
//
//
//        } catch (Exception e) {
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//            return -1;
//        }
//        System.out.println("Operation memberLogin done successfully");
//        return -1;
//    }

}