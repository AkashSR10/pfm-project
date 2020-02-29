package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;

import javafx.fxml.FXML;
import sample.Database.Connect;
import sample.Database.DatabaseHandler;
import sample.model.User;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton signUpButton;

    @FXML
    private JFXButton emailField;

    @FXML
    private JFXTextField firstNameField;

    @FXML
    private JFXTextField lastNameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXCheckBox maleBox;

    @FXML
    private JFXCheckBox femaleBox;


    @FXML
    void initialize() {

        signUpButton.setOnAction(event -> {
            if (signUp()) {
                sceneController sc1 = new sceneController();
                sc1.pMemberMenu();

            } else {
                System.out.print(("Error login in user")); // TODO add message for the user
            }
        });

        maleBox.setOnAction(actionEvent -> {
            femaleBox.setSelected(!maleBox.isSelected()); // check if the male box is selected and negate the female box
        });

        femaleBox.setOnAction(actionEvent -> {
            maleBox.setSelected(!femaleBox.isSelected()); // check if the female box is selected and negate the male box
        });


    }

    public boolean signUp() {

        User user = new User();
        Connection conn;
        user.email = "test@hotmail.com"; //emailField.getText().trim();
        user.firstName = firstNameField.getText().trim();
        user.lastName = lastNameField.getText().trim();
        user.password = passwordField.getText().trim();
        user.gender = maleBox.isSelected() ? 0 : 1; //if the male box is selected the value is 0, if not than the value is 1.
        user.gender = femaleBox.isSelected() ? 1 : 0;


        String saveMemberInfo = "INSERT INTO Member (Email, Password, First_name, Last_name, 'Gender(0=male/1=female)') VALUES (?,?,?,?,?)"; //Add gender and admin when done

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

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
