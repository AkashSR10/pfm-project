package sample.controller.member;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.model.ViewSwitcher;

public class memberMovieSearch {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button searchButton;

    @FXML
    private Button watchlistButton;

    @FXML
    private Button rouletteButton;

    @FXML
    private Button logoutButton;

    @FXML
    void logout(ActionEvent event) {
        ViewSwitcher scene = new ViewSwitcher();
            logoutButton.getScene().getWindow().hide();
            scene.backtoLogin();
            System.out.println("Logout succesful");

    }

    @FXML
    void roulette(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void viewWatchlist(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
}
