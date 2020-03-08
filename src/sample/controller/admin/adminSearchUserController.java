package sample.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.User;
import sample.model.Admin;
import sample.model.DBQueries;
import sample.model.Movie;
import sample.model.ViewSwitcher;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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
    private TextField searchField;

    @FXML
    private TextField uidField;

    @FXML
    private TextField emailText;

    @FXML
    private TextField firstnameText;

    @FXML
    private TextField lastnameText;

    @FXML
    private TextField passwordText;

    ObservableList<Integer> genderData = (ObservableList<Integer>) FXCollections.observableArrayList(0, 1);
    ObservableList<Integer> adminData = (ObservableList<Integer>) FXCollections.observableArrayList(0, 1);

    @FXML
    private ComboBox<Integer> genderBox;

    @FXML
    private ComboBox<Integer> adminBox;

    @FXML
    private Button updateButton;

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

    private ObservableList<User> users = FXCollections.observableArrayList();

        @FXML
        void initialize() {
            genderBox.setItems(genderData);
            adminBox.setItems(adminData);


            colIDU.setCellValueFactory(new PropertyValueFactory<>("userID"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colAdmin.setCellValueFactory(new PropertyValueFactory<>("admin"));
            userTable.setItems(queries.getUser());
            users = queries.getUser();
userTable.setItems(users);

            updateButton.setOnAction(e -> {
                    if (signUp()) {
                        userTable.refresh();
                    } else {
                        System.out.print(("Error login in user")); // TODO add message for the user
                    }
                });
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
                        } if (user.getLastName().toLowerCase().contains(lowerCaseFilter)){
                            return true;
                        }
                        else {
                            return false; // Does not match.
                        }
                    });
                });
                SortedList<User> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(userTable.comparatorProperty());
                userTable.setItems(sortedData);
            });
            }

            public boolean signUp() {
                User user;
                user = new User();
                Connection conn;
                user.email = emailText.getText().trim();
                user.firstName = firstnameText.getText().trim();
                user.lastName = lastnameText.getText().trim();
                user.password = passwordText.getText().trim();

                if (user.email.isEmpty() || user.firstName.isEmpty() || user.password.isEmpty() || user.lastName.isEmpty() || user.password.isEmpty()) {
                    return false;
                } else {

                    String saveMemberInfo = "INSERT INTO user (email, password, first_name, last_name, gender, admin) VALUES (?,?,?,?,?,?)"; //Add gender and admin when done

                    try {
                        String url = "jdbc:sqlite:db/pfm.db"; // db parameters
                        conn = DriverManager.getConnection(url);// create a connection to the database
                        PreparedStatement stmt = conn.prepareStatement(saveMemberInfo); //we use a prepared statement to input the values
                        stmt.setString(1, user.email); //this line sets a value for the first question mark in string saveMemberInfo
                        stmt.setString(2, user.password); //this line sets a value for the first question mark in string saveMemberInfo
                        stmt.setString(3, user.firstName); //this line sets a value for the first question mark in string saveMemberInfo
                        stmt.setString(4, user.lastName); //this line sets a value for the first question mark in string saveMemberInfo
                        stmt.setInt(5, 0); //this line sets a value for the first question mark in string saveMemberInfo
                        stmt.setInt(6, 0); //this line sets a value for the first question mark in string saveMemberInfo
                        stmt.execute();
                        conn.close();
                        stmt.close();
                    } catch (Exception e) {
                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                        return false;
                    }
                }
                return true;
            }
            }


