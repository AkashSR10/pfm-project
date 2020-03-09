package sample.controller.member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import sample.model.*;

import java.util.function.Predicate;
import java.util.logging.Filter;

public class memberSearchMovieController {
    ViewSwitcher view = new ViewSwitcher();

    @FXML
    private Button searchMovieButton;

    @FXML
    private Button watchlistButton;

    @FXML
    private Button rouletteButton;

    @FXML
    private Button logoutButton;

    @FXML
    private TextField searchField;

    @FXML
    void search(KeyEvent event) {
// Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Movie> filteredData = new FilteredList<>(movies, e -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Movie>) movie -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (movie.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches first name.
                    }
                    if (movie.getGenre().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches last name.
                    }
                    if (movie.getDirector().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    if (movie.getWriter().toLowerCase().contains(lowerCaseFilter)) {
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
    }


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
    private TableView<Movie> movieTable;

    @FXML
    private TableColumn<Movie, String> colID;

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
    private TextField movieIDField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField durationField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField writerField;

    @FXML
    private TextField directorField;

    @FXML
    private TextField ratingField;

    @FXML
    private TextField votesField;

    @FXML
    private TextField adultField;

    @FXML
    private Button addButton;

    @FXML
    void addToWatchlist(ActionEvent event) {
        Member member = new Member();
        int userID = 1;
        int movieID = Integer.parseInt(movieIDField.getText());
        member.addMovieWatchlist(userID, movieID);
    }

    @FXML
    void initialize() {
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
        colAdult.setCellValueFactory(new PropertyValueFactory<>("adult"));
        movies = queries.getMovie();
        movieTable.setItems(movies);

        movieTable.setOnMouseClicked(e -> {
            Movie movie = (Movie) movieTable.getSelectionModel().getSelectedItem();
            System.out.println(movie.getMovieID());
            String movieID = String.valueOf(movie.getMovieID());
            String title = movie.getTitle();
            String genre = movie.getGenre();
            String duration = String.valueOf(movie.getDuration());
            String year = String.valueOf(movie.getYear());
            String writer = movie.getWriter();
            String director = movie.getDirector();
            String rating = String.valueOf(movie.getRating());
            String votes = String.valueOf(movie.getNumRating());
            String adult = String.valueOf(movie.getAdult());

            movieIDField.setText(movieID);
            titleField.setText(title);
            genreField.setText(genre);
            durationField.setText(duration);
            yearField.setText(year);
            writerField.setText(writer);
            directorField.setText(director);
            ratingField.setText(rating);
            votesField.setText(votes);
            adultField.setText(adult);
        });
    }
}

