package sample.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class sceneController {

    // Set path to pAdmin
    public void pAdmin(){
        setStage("/sample/view/pick/pickAdmin.fxml");
    }

    // Set  path to pLogin
    public void pLogin(){
        setStage("/sample/view/pick/pickLogin.fxml");
    }

    // Set  path to signUp
    public void pSignUp(){
        setStage("/sample/view/pick/pickSignup.fxml");
    }
    // Set  path to signUp
    public void pMemberMenu(){
        setStage("/sample/view/member/memberMenu.fxml");
    }

    public void padminMenu(){
        setStage("/sample/view/admin/adminMenu.fxml");
    }

    public void setStage(String resource) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(resource));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }


}
