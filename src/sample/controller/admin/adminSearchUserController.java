package sample.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.User;
import sample.model.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class adminSearchUserController{
    ViewSwitcher view = new ViewSwitcher();
        SQLite queries = new SQLite();

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
    private TextField userIDField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField genderField;

    @FXML
    private TextField adminField;

    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;

        @FXML
        void loadLogout(ActionEvent event) {
            logoutButton.getScene().getWindow().hide();
            view.backtoLogin();
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

        // SEARCH
        @FXML
        void search(KeyEvent event) {
            // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<User> filteredData = new FilteredList<>(users, e -> true);
            // 2. Set the filter Predicate whenever the filter changes.
            searchField.setOnKeyReleased(e -> {
                searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super User>) user -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        // Compare first name and last name of every person with filter text.
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (user.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches first name.
                        } if (user.getPassword().toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches last name.
                        }
                        if (user.getFirstName().toLowerCase().contains(lowerCaseFilter)){
                            return true;
                        } // Does not match.
                        return user.getLastName().toLowerCase().contains(lowerCaseFilter);
                    });
                });
                SortedList<User> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(userTable.comparatorProperty());
                userTable.setItems(sortedData);
            });
        }

        // CRUD
        @FXML
        void viewDetails(MouseEvent event) {
            User user= userTable.getSelectionModel().getSelectedItem();
            String userID = String.valueOf(user.getUserID());
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String email = user.getEmail();
            String password = user.getPassword();
            String gender = String.valueOf(user.getGender());
            String admin = String.valueOf(user.getAdmin());
            userIDField.setText(userID);
            firstnameField.setText(firstName);
            lastnameField.setText(lastName);
            emailField.setText(email);
            passwordField.setText(password);
            genderField.setText(gender);
            adminField.setText(admin);
        }

    @FXML
    void addRecord(ActionEvent event) {
        User user = new User();
        Admin admin = new Admin();
        user.userID = Integer.parseInt(userIDField.getText());
        user.firstName = firstnameField.getText();
        user.lastName = lastnameField.getText();
        user.email =  emailField.getText();
        user.password = passwordField.getText();
        user.admin = Integer.parseInt(adminField.getText());
        user.gender = Integer.parseInt(genderField.getText());
        admin.addUser( user.email, user.firstName, user.lastName, user.password, user.gender, user.admin);
        userTable.setItems(queries.getUser());
        users = queries.getUser();
        userTable.setItems(users);
    }

    @FXML
    void updateRecord(ActionEvent event) {
        Admin admin = new Admin();
        User user = new User();

        user.userID = Integer.parseInt(userIDField.getText());
        user.firstName = firstnameField.getText();
        user.lastName = lastnameField.getText();
        user.email =  emailField.getText();
        user.password = passwordField.getText();
        user.admin = Integer.parseInt(adminField.getText());
        user.gender = Integer.parseInt(genderField.getText());
        admin.updateUser( user.userID, user.email, user.firstName, user.lastName, user.password, user.gender, user.admin);
        userTable.setItems(queries.getUser());
        users = queries.getUser();
        userTable.setItems(users);
    }

    @FXML
    void deleteRecord(ActionEvent event) {
        Admin admin = new Admin();
        int userID = Integer.parseInt(userIDField.getText());
        admin.deleteUser(userID);
        users.clear();
        userTable.setItems(queries.getUser());
        users = queries.getUser();
        userTable.setItems(users);
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

    private ObservableList<User> users = FXCollections.observableArrayList();

        @FXML
        void initialize() {
            User user = new User();

            colIDU.setCellValueFactory(new PropertyValueFactory<>("userID"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colAdmin.setCellValueFactory(new PropertyValueFactory<>("admin"));
            users = queries.getUser();
            userTable.setItems(users);



        }
}

