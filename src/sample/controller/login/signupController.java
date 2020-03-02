package sample.controller.login;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import sample.User;
import sample.model.Login;
import sample.model.Signup;
import sample.model.ViewSwitcher;

public class signupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstnameText;

    @FXML
    private TextField lastnameText;

    @FXML
    private TextField emailText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button signupButton;

    @FXML
    private RadioButton maleRadio;

    @FXML
    private ToggleGroup Gender;

    @FXML
    private RadioButton femaleRadio;

    @FXML
    private Button backButton;

    ViewSwitcher scene = new ViewSwitcher();

    @FXML
    void loadLogin(ActionEvent event) {
        backButton.getScene().getWindow().hide();
        scene.backtoLogin();

    }

    @FXML
    void initialize() {
        signupButton.setOnAction(event -> {
            if (signUp()){
                signupButton.getScene().getWindow().hide();
                scene.memberMenu();

            } else {
                System.out.print(("Error login in user")); // TODO add message for the user
            }
        });

    }

    public boolean signUp() {

        User user = new User();
        Connection conn;
        user.email = emailText.getText().trim();
        user.firstName = firstnameText.getText().trim();
        user.lastName = lastnameText.getText().trim();
        user.password = passwordText.getText().trim();
        user.gender = maleRadio.isSelected() ? 0 : 1; //if the male box is selected the value is 0, if not than the value is 1.
        user.gender = femaleRadio.isSelected() ? 1 : 0;


        String saveMemberInfo = "INSERT INTO Member (Email, Password, First_name, Last_name, 'Gender') VALUES (?,?,?,?,?)"; //Add gender and admin when done

        try {
            String url = "jdbc:sqlite:db/test.db"; // db parameters
            conn = DriverManager.getConnection(url);// create a connection to the database
            PreparedStatement stmt = conn.prepareStatement(saveMemberInfo); //we use a prepared statement to input the values
            stmt.setString(1, user.email); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(2, user.password); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(3, user.firstName); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(4, user.lastName); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setInt(5, user.gender); //this line sets a value for the first question mark in string saveMemberInfo
            //add for admin
            stmt.execute();

        } catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
        return false;
        }
    }
}


