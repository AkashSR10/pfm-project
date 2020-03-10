package sample.controller.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import sample.User;
import sample.model.ViewSwitcher;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class signupController {
    // Declare Components

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private JFXTextField firstNameField;

    @FXML
    private JFXTextField lastNameField;

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXTextField passwordField;

    @FXML
    private JFXRadioButton maleRadio;

    @FXML
    private ToggleGroup Gender;

    @FXML
    private JFXRadioButton femaleRadio;

    @FXML
    private JFXButton signupButton;

    @FXML
    private JFXButton backButton;

    @FXML
    private Text error;

    // ViewSwitch Functions
    ViewSwitcher scene = new ViewSwitcher();

    @FXML
    void loadLogin(ActionEvent event) {
        backButton.getScene().getWindow().hide();
        scene.backtoLogin();

    }

    @FXML
    void initialize() {
        signupButton.setOnAction(event -> {
            if (signUp()) {
                signupButton.getScene().getWindow().hide();
                scene.memberSearchMovie();
            } else {
                System.out.print(("Error login in user")); // TODO add message for the user
            }
        });
    }

    public boolean signUp() {
        User user;
        user = new User();
        Connection conn;
        user.email = emailField.getText().trim();
        user.firstName = firstNameField.getText().trim();
        user.lastName = lastNameField.getText().trim();
        user.password = passwordField.getText().trim();
        user.gender = maleRadio.isSelected() ? 0 : 1; //if the male box is selected the value is 0, if not than the value is 1.
        user.gender = femaleRadio.isSelected() ? 1 : 0;

        if (user.email.isEmpty() || user.firstName.isEmpty() || user.password.isEmpty() || user.lastName.isEmpty() || user.password.isEmpty()) {
            error.setText("One or multiple fields are not filled in correctly");
            return false;
        } else {

            String saveMemberInfo = "INSERT INTO user (email, password, first_name, last_name, gender, admin) VALUES (?,?,?,?,?,0)"; //Add gender and admin when done

            try {
                String url = "jdbc:sqlite:db/pfm.db"; // db parameters
                conn = DriverManager.getConnection(url);// create a connection to the database
                PreparedStatement stmt = conn.prepareStatement(saveMemberInfo); //we use a prepared statement to input the values
                stmt.setString(1, user.email); //this line sets a value for the first question mark in string saveMemberInfo
                stmt.setString(2, user.password); //this line sets a value for the first question mark in string saveMemberInfo
                stmt.setString(3, user.firstName); //this line sets a value for the first question mark in string saveMemberInfo
                stmt.setString(4, user.lastName); //this line sets a value for the first question mark in string saveMemberInfo
                stmt.setInt(5, user.gender); //this line sets a value for the first question mark in string saveMemberInfo
                //add for admin
                stmt.execute();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                return false;
            }
        }
        return true;
    }
}


