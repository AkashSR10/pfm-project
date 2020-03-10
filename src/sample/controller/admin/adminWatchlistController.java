package sample.controller.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import sample.model.Movie;
import sample.model.SQLite;
import sample.model.ViewSwitcher;

public class adminWatchlistController {
    // Declare Components
    @FXML
    private JFXButton rouletteButton;
    @FXML
    private JFXButton watchlistButton;
    @FXML
    private JFXButton addWatchlistButton;
    @FXML
    private JFXButton searchMovieButton;
    @FXML
    private JFXButton searchUserButton;
    @FXML
    private JFXButton logoutButton;
    @FXML
    private JFXTextField movieIDField;
    @FXML
    private JFXTextField titleField;
    @FXML
    private JFXTextField genreField;
    @FXML
    private JFXTextField durationField;
    @FXML
    private JFXTextField yearField;
    @FXML
    private JFXTextField writerField;
    @FXML
    private JFXTextField directorField;
    @FXML
    private JFXTextField ratingField;
    @FXML
    private JFXTextField votesField;
    @FXML
    private JFXTextField searchField;
    @FXML
    private Text error;

    // ViewSwitch Functions
    ViewSwitcher view = new ViewSwitcher();

    @FXML
    void loadLogout(ActionEvent event) {
        logoutButton.getScene().getWindow().hide();
        view.backtoLogin();
    }

    @FXML
    void loadSearchMovie(ActionEvent event) {
        searchMovieButton.getScene().getWindow().hide();
        view.adminSearchMovie();
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

    @FXML
    void loadRoulette(ActionEvent event) {
        rouletteButton.getScene().getWindow().hide();
        view.adminRoulette();
    }

    // Declare components Watchlist Table
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
    private ObservableList<Movie> movies = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        //Set cells to corresponding value for the Observable List
        SQLite queries = new SQLite();
        colID.setCellValueFactory(new PropertyValueFactory<>("movieID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colWriter.setCellValueFactory(new PropertyValueFactory<>("writer"));
        colDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        colRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        colVotes.setCellValueFactory(new PropertyValueFactory<>("numRating"));
        movies = queries.getMovie();
        movieTable.setItems(movies);

        // Show values in text fields below ... modify records
        movieTable.setOnMouseClicked(e -> {
            Movie movie = (Movie) movieTable.getSelectionModel().getSelectedItem();
            String movieID = String.valueOf(movie.getMovieID());
            String title = movie.getTitle();
            String genre = movie.getGenre();
            String duration = String.valueOf(movie.getDuration());
            String year = String.valueOf(movie.getYear());
            String writer = movie.getWriter();
            String director = movie.getDirector();
            String rating = String.valueOf(movie.getRating());
            String votes = String.valueOf(movie.getNumRating());

            movieIDField.setText(movieID);
            titleField.setText(title);
            genreField.setText(genre);
            durationField.setText(duration);
            yearField.setText(year);
            writerField.setText(writer);
            directorField.setText(director);
            ratingField.setText(rating);
            votesField.setText(votes);
        });

    }
}
