package sample.controller.member;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.controller.login.loginController;
import sample.model.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


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

//    @FXML
//    void initialize() {
//
//        GetMemberMovieID(store_id2);
//    }

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

    // Retrieve watchlist



    @FXML
    private TableColumn UserID;
    @FXML
    private TableColumn MovieID;



    @FXML
    private TableView<Watchlist> watchlistView;

    private ObservableList<Watchlist> valuesID = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        DBQueries queries = new DBQueries();
        UserID.setCellValueFactory(new PropertyValueFactory<>("User_id")); //This variable should be changed once marko has made the query, also in thee memberWatchlist.fxml
        MovieID.setCellValueFactory(new PropertyValueFactory<>("Movie_id")); //This variable should be changed once marko has made the query query, also in thee memberWatchlist.fxml
        valuesID = queries.watchListMethod();
        watchlistView.setItems(valuesID);

    }

}