package sample.controller.member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import sample.model.Member;
import sample.model.Movie;
import sample.model.SQLite;
import sample.model.ViewSwitcher;

public class memberRouletteController {
    ViewSwitcher view = new ViewSwitcher();

    @FXML
    private Button searchMovieButton;

    @FXML
    private Button addWatchListButton;


    @FXML
    private Button watchlistButton;

    @FXML
    private Button rouletteButton;

    @FXML
    private Button logoutButton;

    ObservableList<String> options = FXCollections.observableArrayList("1", "2", "3");

    @FXML
    private ComboBox<String> comboGenre;

    @FXML
    private ComboBox<Integer> comboYear;

    @FXML
    private ComboBox<Integer> comboRating;

    @FXML
    private Button spinButton;

    @FXML
    private Text titleText;

    @FXML
    private Text movieID;

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
        comboGenre.getItems().addAll("Action", "Adventure", "Animation", "Biography", "Comedy", "Crime", "Documentary", "Drama", "Family", "Fantasy", "History", "Horror", "Mystery", "Romance", "Sci-Fi", "Thriller", "Western");
        comboYear.getItems().addAll(2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020);
        comboRating.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        SQLite queries = new SQLite();
        Movie rouletteMovie = new Movie();

        spinButton.setOnAction( e -> {
            int ratingInput = comboRating.getValue();
            int yearInput = comboYear.getValue();
            String genreInput = comboGenre.getValue();
            queries.rouletteMovie(genreInput, yearInput, ratingInput);
            titleText.setText(queries.title);
            movieID.setText(String.valueOf(queries.movieID));

        });

        addWatchListButton.setOnAction( e -> {
            Member member = new Member();
            member.addMovieWatchlist(queries.userID, queries.movieID);
    });
    }
}

