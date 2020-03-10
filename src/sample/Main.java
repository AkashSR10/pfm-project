package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.SQLite;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/login/login.fxml"));
        primaryStage.setTitle("Programming For Managers - Movie Roulette");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        SQLite db = new SQLite();
        db.connect();
        sample.User lol = new sample.User();
        lol.memberLogin("yes", "yes");
        System.out.println(lol.userIDMember);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
