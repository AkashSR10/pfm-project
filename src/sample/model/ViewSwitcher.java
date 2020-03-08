package sample.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import jfxtras.styles.jmetro.JMetro;
//import jfxtras.styles.jmetro.Style;

import java.io.IOException;

public class ViewSwitcher {

    // View Function
    public void setView(String resource) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(resource));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new javafx.scene.Scene(root));
        stage.show();
    }

    // VIEW LOGIN
    public void signup(){
        setView("/sample/view/login/signup.fxml");
    }

    public void backtoLogin(){
        setView("/sample/view/login/login.fxml");
    }

    // VIEW MEMBER
    public void memberMenu(){
        setView("/sample/view/member/memberSearchMovie.fxml");
    }

    public void memberRoulette(){
        setView("/sample/view/member/memberRoulette.fxml");
    }

    public void memberWatchlist(){
        setView("/sample/view/member/memberWatchlist.fxml");
    }

    // VIEW ADMIN
    public void adminMenu(){
        setView("/sample/view/admin/adminSearchMovie.fxml");
    }

    public void adminRoulette(){
        setView("/sample/view/admin/adminRoulette.fxml");
    }

    public void adminWatchlist(){
        setView("/sample/view/admin/adminWatchlist.fxml");
    }

    public void adminSearchUser(){
        setView("/sample/view/admin/adminSearchUser.fxml");
    }

}
