package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.DBQueries;
import sample.model.Login;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/login/login.fxml"));
        primaryStage.setTitle("pfm-project");
        primaryStage.setScene(new Scene(root));
primaryStage.show();
        DBQueries database = new DBQueries();
        database.connectDB();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
