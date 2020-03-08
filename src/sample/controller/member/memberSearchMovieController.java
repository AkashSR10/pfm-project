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
import sample.model.DBQueries;
import sample.model.Movie;
import sample.model.ViewSwitcher;

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
        DBQueries queries = new DBQueries();
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
                            } if (movie.getGenre().toLowerCase().contains(lowerCaseFilter)) {
                                return true; // Filter matches last name.
                            }
                            if (movie.getDirector().toLowerCase().contains(lowerCaseFilter)){
                                    return true;
                                } if (movie.getWriter().toLowerCase().contains(lowerCaseFilter)){
                             return true;
                            }
                            else {
                                return false; // Does not match.
                            }
                        });
                    });
            SortedList<Movie> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(movieTable.comparatorProperty());
            movieTable.setItems(sortedData);
                    });

        // 3. Wrap the FilteredList in a SortedList.

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.

        // 5. Add sorted (and filtered) data to the table.
    }
}


