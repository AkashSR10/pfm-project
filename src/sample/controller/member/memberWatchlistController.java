package sample.controller.member;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.model.ViewSwitcher;

public class memberWatchlistController {
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
}
