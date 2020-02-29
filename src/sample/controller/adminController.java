package sample.controller;

import com.jfoenix.controls.JFXButton;
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

public class adminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton adminLoginButton;

    @FXML
    private JFXPasswordField loginPassword;

    @FXML
    private JFXTextField loginUsername;


    @FXML
    void initialize() {

        adminLoginButton.setOnAction((event -> {
            if (loginAdmin()) {
                // TODO add landing page
            } else {
                System.out.print(("Error login in user")); // TODO add message for the user
            }

        }));
    }

    private boolean loginAdmin() {

        Connection conn;
        String checkAdmin = "SELECT * FROM Member WHERE Email = ? AND Password = ?"; // TODO add AND Admin =?
        System.out.println(checkAdmin);

        try {

            String url = "jdbc:sqlite:db/pfm.db"; // db parameters
            conn = DriverManager.getConnection(url); // create a connection to the database

            PreparedStatement stmt = conn.prepareStatement(checkAdmin); //we use a prepared statement to input the values
            stmt.setString(1, loginUsername.getText().trim()); //this line sets a value for the first question mark in string checkAdmin
            stmt.setString(2, loginPassword.getText().trim()); //this line sets a value for the first question mark in string checkAdmin
//            stmt.setString(3, "1"); //this line sets a value for the first question mark in string checkAdmin
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
