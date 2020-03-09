package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.Admin;
import sample.model.SQLite;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/login/login.fxml"));
        primaryStage.setTitle("pfm-project");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        SQLite db = new SQLite();
        db.connect();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
