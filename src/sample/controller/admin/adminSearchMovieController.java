package sample.controller.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import sample.User;
import sample.model.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class adminSearchMovieController extends User {
    // Declare Components
    @FXML
    private JFXButton rouletteButton;
    @FXML
    private JFXButton watchlistButton;
    @FXML
    private JFXButton searchMovieButton;
    @FXML
    private JFXButton searchUserButton;
    @FXML
    private JFXButton logoutButton;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton updateButton;
    @FXML
    private JFXButton deleteButton;
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
    private Text error;
    @FXML
    private JFXTextField searchField;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private JFXButton addToWatchlistButton;

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

    // Search Function
    @FXML
    void search(KeyEvent event) {
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Movie> filteredData = new FilteredList<>(movies, e -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Movie>) movie -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (movie.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    if (movie.getGenre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    if (movie.getWriter().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    if (movie.getDirector().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false; // Does not match.
                    }
                });
            });
            SortedList<Movie> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(movieTable.comparatorProperty());
            movieTable.setItems(sortedData);
        });
        SortedList<Movie> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(movieTable.comparatorProperty());
        movieTable.setItems(sortedData);
    }

    ;

    // CRUD Functions
    SQLite queries = new SQLite();

    @FXML
    void addRecord(ActionEvent event) {
        try {
            Movie movie = new Movie();
            Admin admin = new Admin();
            movie.movieID = Integer.parseInt(movieIDField.getText());
            movie.title = titleField.getText();
            movie.genre = genreField.getText();
            movie.year = Integer.parseInt(yearField.getText());
            movie.duration = Integer.parseInt(durationField.getText());
            movie.director = directorField.getText();
            movie.writer = writerField.getText();
            movie.year = Integer.parseInt(yearField.getText());
            movie.rating = Integer.parseInt(ratingField.getText());
            movie.numRating = Integer.parseInt(votesField.getText());
            admin.addMovie(movie.title, movie.genre, movie.duration, movie.year, movie.writer, movie.director, movie.rating, movie.numRating);
            movieTable.setItems(queries.getMovie());
            movies = queries.getMovie();
            movieTable.setItems(movies);
            error.setText("Record added succesfully");
        } catch (Error e) {
            error.setText("Please check if you");

        }
    }

    @FXML
    void deleteRecord(ActionEvent event) {
        Admin admin = new Admin();
        Movie movie = new Movie();
        int movieID = Integer.parseInt(movieIDField.getText());
        admin.deleteMovie(movieID);
        movies.clear();
        movieTable.setItems(queries.getMovie());
        movies = queries.getMovie();
        movieTable.setItems(movies);
        error.setText("Record deleted succesfully");

    }

    @FXML
    void updateRecord(ActionEvent event) {
        Movie movie = new Movie();
        Admin admin = new Admin();
        movie.movieID = Integer.parseInt(movieIDField.getText());
        movie.title = titleField.getText();
        movie.genre = genreField.getText();
        movie.year = Integer.parseInt(yearField.getText());
        movie.duration = Integer.parseInt(durationField.getText());
        movie.director = directorField.getText();
        movie.writer = writerField.getText();
        movie.year = Integer.parseInt(yearField.getText());
        movie.rating = Integer.parseInt(ratingField.getText());
        movie.numRating = Integer.parseInt(votesField.getText());
        admin.updateMovie(movie.movieID, movie.title, movie.genre, movie.duration, movie.year, movie.writer, movie.director, movie.rating, movie.numRating);
        movieTable.setItems(queries.getMovie());
        movies = queries.getMovie();
        movieTable.setItems(movies);
        error.setText("Record updated succesfully");

    }

    // Add to Watchlist

    @FXML
    void addToWatchlist(ActionEvent event) {
        Member member = new Member();
        int movieID = Integer.parseInt(movieIDField.getText());
        member.addMovieWatchlist(userID, movieID);

    }

    // Declare components Movie Table
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
    private ObservableList<Movie> movies = FXCollections.observableArrayList();


    @FXML
    void initialize() {
        //Set cells to corresponding value for the Observable List
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
