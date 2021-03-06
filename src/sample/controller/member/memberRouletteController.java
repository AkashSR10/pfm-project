package sample.controller.member;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import sample.User;
import sample.model.Movie;
import sample.model.SQLite;
import sample.model.ViewSwitcher;

public class memberRouletteController extends User {
    ViewSwitcher view = new ViewSwitcher();

    @FXML
    private JFXButton rouletteButton;

    @FXML
    private JFXButton watchlistButton;

    @FXML
    private JFXButton searchMovieButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private Text titleText;

    @FXML
    private Text titleText3;

    @FXML
    private Text titleText4;

    @FXML
    private JFXButton spinButton;

    @FXML
    private JFXComboBox<String> comboGenre;

    @FXML
    private JFXComboBox<Integer> comboYear;

    @FXML
    private JFXComboBox<Integer> comboRating;
    @FXML
    private Text ratingText;
    @FXML
    private Text error;
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
        view.memberSearchMovie();
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

        spinButton.setOnAction(e -> {

            if (comboYear.getSelectionModel().isEmpty() || comboRating.getSelectionModel().isEmpty() || comboGenre.getSelectionModel().isEmpty()) {
                error.setText("Please select values to continue");

            } else {
                int ratingInput = comboRating.getValue();
                int yearInput = comboYear.getValue();
                String genreInput = comboGenre.getValue();
                queries.rouletteMovie(genreInput, yearInput, ratingInput);
                titleText.setText(queries.title);
                ratingText.setText(String.valueOf(queries.rating));
            }
        });

    }
}

