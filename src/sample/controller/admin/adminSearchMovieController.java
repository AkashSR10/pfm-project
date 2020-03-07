package sample.controller.admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.User;
import sample.model.DBQueries;
import sample.model.Movie;
import sample.model.ViewSwitcher;


public class adminSearchMovieController {
    ViewSwitcher view = new ViewSwitcher();
    DBQueries queries = new DBQueries();

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button searchMovieButton;
    @FXML
    private Button searchUserButton;
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
        view.adminRoulette();
    }

    @FXML
    void loadSearchMovie(ActionEvent event) {
        searchMovieButton.getScene().getWindow().hide();
        view.adminMenu();
    }

    @FXML
    void loadSearchUser(ActionEvent event) {
        searchUserButton.getScene().getWindow().hide();
        view.adminSearchUser();
    }

    @FXML
    void loadWatchlist(ActionEvent event) {
        watchlistButton.getScene().getWindow().hide();
        view.adminWatchlist();
    }

    //MOVIE TABLE
    private ObservableList<Movie> movie;
    @FXML
    private TableView<Movie> movieTable;
    @FXML
    private TableColumn<Movie, Integer> colID;
    @FXML
    private TableColumn<Movie, String> colTitle;
    @FXML
    private TableColumn<Movie, String> colGenre;
    @FXML
    private TableColumn<Movie, Integer> colDuration;
    @FXML
    private TableColumn<Movie, Integer> colYear;
    @FXML
    private TableColumn<Movie, String> colWriter;
    @FXML
    private TableColumn<Movie, String> colDirector;
    @FXML
    private TableColumn<Movie, Integer> colRating;
    @FXML
    private TableColumn<Movie, Integer> colVotes;
    @FXML
    private TableColumn<Movie, Integer> colAdult;


    @FXML
    void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("movieID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colWriter.setCellValueFactory(new PropertyValueFactory<>("writer"));
        colDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        colRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        colVotes.setCellValueFactory(new PropertyValueFactory<>("numRating"));
        colAdult.setCellValueFactory(new PropertyValueFactory<>("adult"));
        movieTable.setItems(queries.getMovie());

    }


}