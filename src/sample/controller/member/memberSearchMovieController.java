package sample.controller.member;


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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
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

    @FXML
    private TableColumn<User, Integer> colID;

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
        addButtonToTable();
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


    //adding button to table to add to watchlist
    public void addButtonToTable() {
        TableColumn<Movie, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<Movie, Void>, TableCell<Movie, Void>> cellFactory = new Callback<TableColumn<Movie, Void>, TableCell<Movie, Void>>() {

            @Override
            public TableCell<Movie, Void> call(final TableColumn<Movie, Void> param) {
                final TableCell<Movie, Void> cell = new TableCell<Movie, Void>() {

                    public final Button btn = new Button("Add to Watchlist"); {
                        btn.setOnAction((ActionEvent event) -> {
                            Movie data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            int MovieID = data.getMovieID(); //Creating and extra variable to call it
                            System.out.println("MovieID: " + MovieID); //test if it works
                            merge(userID, MovieID); //Here I am calling/creating a method (see bellow), because the variable MovieID cannot be seen outside the these brackets
                                    //Figure out a way to het the user ID here
                        });
                    }



                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };



        colBtn.setCellFactory(cellFactory);

        movieTable.getColumns().add(colBtn);

    }

    public void merge (int UserID, int MovieID) {



    }



}


