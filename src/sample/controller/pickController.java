package sample.controller;
import sample.Database.Connect;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class pickController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton pickAdminButton;

    @FXML
    private JFXButton pickGuestButton;

    @FXML
    private JFXButton pickLoginButton;

    @FXML
    void initialize() {
        sceneController controller = new sceneController();

        pickAdminButton.setOnAction(event -> {
          // Take users to Admin Login Screen
          pickAdminButton.getScene().getWindow().hide();
          controller.pAdmin();
      });

        pickGuestButton.setOnAction(event -> {
            System.out.println("Click!");
            Connect connect = new Connect();
            connect.connect();


        });

        pickLoginButton.setOnAction(event -> {
            pickLoginButton.getScene().getWindow().hide();
            controller.pLogin();
        });
    }
}
