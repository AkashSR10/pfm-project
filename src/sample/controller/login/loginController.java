package sample.controller.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import sample.User;
import sample.model.ViewSwitcher;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginController {
    // Declare Components
    @FXML
    private JFXTextField test;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Text error;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton signupButton;

    // ViewSwitch Functions
    // view object created to call View Switch methods
    ViewSwitcher view = new ViewSwitcher();

    // Loads either the Admin or Member view. Functions checks if a user and password combination matches in DB
    @FXML
    public void loadLogin(ActionEvent event) throws SQLException {
        User user = new User();

        // Get text from inputs
        user.email = emailField.getText().trim();
        user.password = passwordField.getText().trim();

        // Checks if combination is correct and a member
        if (user.memberLogin(user.email, user.password)) {
            loginButton.getScene().getWindow().hide();
            view.memberRoulette();
            // Checks if combination is correct and an admin
        } else if (user.adminLogin(emailField.getText().trim(), passwordField.getText().trim())) {
            loginButton.getScene().getWindow().hide();
            view.adminRoulette();
            // Returns error if the user is not a member or admin
        } else {
            error.setText("Your email or password is incorrect");
        }
    }

    // Loads the Sign up View
    @FXML
    void loadSignup(ActionEvent event) {
        signupButton.getScene().getWindow().hide();
        view.signup();

    }
}


