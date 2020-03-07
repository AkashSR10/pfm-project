package sample.controller.admin;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.User;
import sample.model.DBQueries;
import sample.model.ViewSwitcher;

import java.net.URL;
import java.util.ResourceBundle;

public class adminSearchUserController {
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

        // USER TABLE
        private ObservableList<User> user;
        @FXML
        private TableView<User> userTable;
        @FXML
        private TableColumn<User, Integer> colIDU;
        @FXML
        private TableColumn<User, String> colEmail;
        @FXML
        private TableColumn<User, String> colFirstName;
        @FXML
        private TableColumn<User, String> colLastName;
        @FXML
        private TableColumn<User, String> colPassword;
        @FXML
        private TableColumn<User, Integer> colGender;
        @FXML
        private TableColumn<User, Integer> colAdmin;


        @FXML
        void initialize() {

            colIDU.setCellValueFactory(new PropertyValueFactory<>("userID"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colAdmin.setCellValueFactory(new PropertyValueFactory<>("admin"));
            userTable.setItems(queries.getUser());
        }


    }

