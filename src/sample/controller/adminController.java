package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

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
        String loginText = loginUsername.getText().trim();
        String loginPWd  = loginPassword.getText().trim();

       adminLoginButton.setOnAction((event ->{
           if (loginText.equals("") || loginPassword.equals("")){
               loginAdmin(loginText,loginPWd);
           } else {
               System.out.print(("Error login in user"));
           }

       }));
    }

    private void loginAdmin(String username, String password) {
        // Check in db if user exist
    }
}
