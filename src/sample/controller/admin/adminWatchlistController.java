package sample.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.model.DBQueries;
import sample.model.ViewSwitcher;

import java.net.URL;
import java.util.ResourceBundle;

public class adminWatchlistController {

            ViewSwitcher view = new ViewSwitcher();

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
}
