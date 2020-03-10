package sample.controller.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import sample.User;
import sample.model.Admin;
import sample.model.SQLite;
import sample.model.ViewSwitcher;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class adminSearchUserController {
    // Declare Components
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private JFXButton rouletteButton;
    @FXML
    private JFXButton watchlistButton;
    @FXML
    private JFXButton searchMovieButton;
    @FXML
    private JFXButton searchUserButton;
    @FXML
    private JFXButton logoutButton;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton updateButton;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXTextField searchField;
    @FXML
    private JFXTextField userIDField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField firstnameField;
    @FXML
    private JFXTextField lastnameField;
    @FXML
    private JFXTextField passwordField;
    @FXML
    private JFXTextField genderField;
    @FXML
    private JFXTextField adminField;
    @FXML
    private Text error;

    // ViewSwitch Functions
    ViewSwitcher view = new ViewSwitcher();

    @FXML
    void loadLogout(ActionEvent event) {
        logoutButton.getScene().getWindow().hide();
        view.backtoLogin();
    }

    @FXML
    void loadSearchMovie(ActionEvent event) {
        searchMovieButton.getScene().getWindow().hide();
        view.adminSearchMovie();
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

    @FXML
    void loadRoulette(ActionEvent event) {
        rouletteButton.getScene().getWindow().hide();
        view.adminRoulette();
    }

    // Search Function
    @FXML
    void search(KeyEvent event) {
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<User> filteredData = new FilteredList<>(users, e -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super User>) user -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (user.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    if (user.getPassword().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    if (user.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return user.getLastName().toLowerCase().contains(lowerCaseFilter);
                });
            });
            SortedList<User> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(userTable.comparatorProperty());
            userTable.setItems(sortedData);
        });
    }



    // Show values in text fields below ... modify records

    @FXML
    void viewDetails(MouseEvent event) {
        User user = userTable.getSelectionModel().getSelectedItem();
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

    // CRUD Functions
    SQLite queries = new SQLite();

    @FXML
    void addRecord(ActionEvent event) {
        User user = new User();
        Admin admin = new Admin();
        user.userID = Integer.parseInt(userIDField.getText());
        user.firstName = firstnameField.getText();
        user.lastName = lastnameField.getText();
        user.email = emailField.getText();
        user.password = passwordField.getText();
        user.admin = Integer.parseInt(adminField.getText());
        user.gender = Integer.parseInt(genderField.getText());
        admin.addUser(user.email, user.firstName, user.lastName, user.password, user.gender, user.admin);
        userTable.setItems(queries.getUser());
        users = queries.getUser();
        userTable.setItems(users);
        error.setText("Record added succesfully");
    }

    @FXML
    void updateRecord(ActionEvent event) {
        Admin admin = new Admin();
        User user = new User();

        user.userID = Integer.parseInt(userIDField.getText());
        user.firstName = firstnameField.getText();
        user.lastName = lastnameField.getText();
        user.email = emailField.getText();
        user.password = passwordField.getText();
        user.admin = Integer.parseInt(adminField.getText());
        user.gender = Integer.parseInt(genderField.getText());
        admin.updateUser(user.userID, user.email, user.firstName, user.lastName, user.password, user.gender, user.admin);
        userTable.setItems(queries.getUser());
        users = queries.getUser();
        userTable.setItems(users);
        error.setText("Record updated succesfully");

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
        error.setText("Record deleted succesfully");

    }

    // Declare components User Table
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
        //Set cells to corresponding value for the Observable List P.S. -> You can either call functions outside of the initialize function. For this project we used both methods to display our capabilities
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

