package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/pick/pickMenu.fxml"));
        primaryStage.setTitle("pfm-project");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

/*
    1. Install IntelliJ
    2. Add Jfoenix 9.0 to Library
    3. Add sqlite-jdbc-3.30.1.jar to Library
 */