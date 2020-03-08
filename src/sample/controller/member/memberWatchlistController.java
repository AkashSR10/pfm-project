package sample.controller.member;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import sample.controller.login.loginController;
import sample.model.*;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import sample.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import java.net.URL;
import java.util.ResourceBundle;

public class memberWatchlistController {
    ViewSwitcher view = new ViewSwitcher();
    int store_id2 = loginController.store_id;

    @FXML
    private TableView watchlistView;

    @FXML
    private Button searchMovieButton;

    @FXML
    private Button watchlistButton;

    @FXML
    private Button rouletteButton;

    @FXML
    private Button logoutButton;

    @FXML
    void loadLogout(ActionEvent event) {
        logoutButton.getScene().getWindow().hide();
        view.backtoLogin();
    }

    @FXML
    void loadRoulette(ActionEvent event) {
        rouletteButton.getScene().getWindow().hide();
        view.memberRoulette();
    }

    @FXML
    void loadSearchMovie(ActionEvent event) {
        searchMovieButton.getScene().getWindow().hide();
        view.memberMenu();
    }

    @FXML
    void loadWatchlist(ActionEvent event) {
        watchlistButton.getScene().getWindow().hide();
        view.memberWatchlist();
    }

    @FXML
    void initialize() {

        watchlist(store_id2);
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

    // Retrieve watchlist

    public int watchlist (int userID) {

        List<Movie> watchlist = new ArrayList();

        try {
            // SETUP
            //*************
            String query = "select * from watchlist where user_id = ? ";  // Set QUERY
            //*************
            Connection c = null;                                                            // Declare connection var
            ResultSet rs = null;                                                     // Declare resultset
            PreparedStatement preparedStatement = null;                                     // Declare preparedstatement
            Class.forName("org.sqlite.JDBC");                                               // Load JDBC
            c = DriverManager.getConnection("jdbc:sqlite:db/pfm.db");                  // Establish connection to DB
            c.setAutoCommit(false);                                                         // Set autocommit to false -> see JBDC docs

            // QUERY RESULTS
            preparedStatement = c.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            rs = preparedStatement.executeQuery();

            // FUNCTION
            while (rs.next()) {
                Movie movie = new Movie();
                movie.movieID = rs.getInt("Movie_id");
                int UserID = rs.getInt("user_id");
                int MovieID = rs.getInt("Movie_id");

                System.out.printf("FOUND movie: %s for user: %s \n",MovieID, UserID);
                watchlist.add(movie) ;
            }
            ObservableList<Movie> data = FXCollections.observableArrayList();
            data.addAll(watchlist);
            System.out.println("number of movies in data: " + data.size());
            System.out.println(data.get(0).title);
            watchlistView.setItems(data);

            // CLOSE CONNECTION
            rs.close();
            preparedStatement.close();
            c.close();





        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return -1;
        }
        System.out.println("Operation memberLogin done successfully");
        return -1;
    }

//
//    private void Watchlist (){
//
//        Connection conn;
//        String sql = "SELECT * FROM Movie";
//        List<Movie> watchlist = new ArrayList();
//
//        try {
//            String url = "jdbc:sqlite:db/pfm.db";
//            conn = DriverManager.getConnection(url);
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                Movie movie = new Movie();
//                movie.title = rs.getString("Title");
//                movie.rating = rs.getInt("Rating");
//                movie.year = rs.getInt("Year");
//                movie.genre= rs.getString("Genre");
//                watchlist.add(movie);
//
//            }
//            ObservableList<Movie> data = FXCollections.observableArrayList();
//            data.addAll(watchlist);
//            System.out.println("number of movies in data: " + data.size());
//            System.out.println(data.get(0).title);
//            watchlistView.setItems(data);
//
//        } catch (
//                SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
