package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Database.Connect;
import sample.Database.DatabaseHandler;
import sample.model.Movie;




public class loginController {


    @FXML
    private JFXButton signUpButton;

    @FXML
    private JFXButton memberLoginButton;

    @FXML
    private JFXPasswordField loginPassword;

    @FXML
    private JFXTextField loginUsername;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton signupButton;

    @FXML
    private TableView movieView;


    @FXML
    void initialize() {
        memberLoginButton.setOnAction(event -> {
            if (login()) {
                sceneController sc1 = new sceneController();
                sc1.pMemberMenu();
            } else {
                System.out.print(("Error login in user")); // TODO add message for the user
            }
        });

        signUpButton.setOnAction(event -> {
            sceneController sc2 = new sceneController();
            sc2.pSignUp();
        });
    }


    private boolean login() {

        Connection conn;
        String checkLogin = "SELECT * FROM Member WHERE Email = ? AND Password = ?"; //AND Admin = ?";
        String adminValue = "0";
        try {
            String url = "jdbc:sqlite:db/pfm.db"; // db parameters
            conn = DriverManager.getConnection(url);// create a connection to the database
            PreparedStatement stmt = conn.prepareStatement(checkLogin); //we use a prepared statement to input the values
            stmt.setString(1, loginUsername.getText().trim()); //this line sets a value for the first question mark in string checkAdmin
            stmt.setString(2, loginPassword.getText().trim()); //this line sets a value for the first question mark in string checkAdmin
//            stmt.setString(3, adminValue);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String firstName = rs.getString("First_name");
                String lastName = rs.getString("Last_name");
                System.out.printf("Login %s %s", firstName, lastName);
                if (firstName != null) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
