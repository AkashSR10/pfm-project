package sample.controller.admin;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.util.Callback; // Yv added

import javafx.scene.control.TableCell; //Yv added
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
import javafx.scene.text.Text;
import sample.User;
import sample.controller.login.loginController;
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
    private TextField searchField;

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
    private Text error;

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

        addButton.setOnAction(event -> {
            if (addMovie()) {
                movieTable.refresh();
            } else {
                System.out.print(("Error login in user")); // TODO add message for the user
            }
        });
    }

    public boolean addMovie() {
        Movie movie = new Movie();
        Connection conn;
        movie.title = titleField.getText().trim();
        movie.genre = genreField.getText().trim();
        movie.duration = Integer.parseInt(durationField.getText().trim());
        movie.year = Integer.parseInt(yearField.getText().trim());
        movie.writer = writerField.getText().trim();
        movie.director = directorField.getText().trim();
        movie.rating = Integer.parseInt(ratingField.getText().trim());
        movie.numRating = Integer.parseInt(votesField.getText().trim());
        movie.adult = Integer.parseInt(adultField.getText().trim());

        if (movie.title.isEmpty() || movie.genre.isEmpty() || movie.writer.isEmpty() || movie.director.isEmpty()) {
            error.setText("One or multiple fields are not filled in");
            return false;
        } else {

            String saveMemberInfo = "INSERT INTO movie (title, genre, duration, year, writer, director, rating, num_rating, adult) VALUES (?,?,?,?,?,?,?,?,?)"; //Add gender and admin when done

            try {
                String url = "jdbc:sqlite:db/pfm.db"; // db parameters
                conn = DriverManager.getConnection(url);// create a connection to the database
                PreparedStatement stmt = conn.prepareStatement(saveMemberInfo); //we use a prepared statement to input the values
                stmt.setString(1, movie.title); //this line sets a value for the first question mark in string saveMemberInfo
                stmt.setString(2, movie.genre); //this line sets a value for the first question mark in string saveMemberInfo
                stmt.setInt(3, movie.duration); //this line sets a value for the first question mark in string saveMemberInfo
                stmt.setInt(4, movie.year); //this line sets a value for the first question mark in string saveMemberInfo
                stmt.setString(5, movie.writer); //this line sets a value for the first question mark in string saveMemberInfo
                stmt.setString(6, movie.director); //this line sets a value for the first question mark in string saveMemberInfo
                stmt.setInt(7, movie.rating); //this line sets a value for the first question mark in string saveMemberInfo
                stmt.setInt(8, movie.numRating); //this line sets a value for the first question mark in string saveMemberInfo
                stmt.setInt(9, movie.adult); //this line sets a value for the first question mark in string saveMemberInfo
                //add for admin
                stmt.execute();
                stmt.close();
                conn.close();

            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                return false;
            }
        }

        return true;
    }



}