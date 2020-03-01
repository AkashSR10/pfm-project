package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import sample.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MemberMenuController {

    @FXML
    private TableView movieView;

    @FXML
    private ObservableList<Movie> movies;



    @FXML
    void initialize() {

        addAllMovies();

    }

    private void addAllMovies() { //this methods connects to the movie table and retreives all the movies

        Connection conn;
        String sql = "SELECT * FROM Movie ORDER BY primaryTitle";
        List<Movie> allMovies = new ArrayList();
        try {
            String url = "jdbc:sqlite:db/pfm.db"; // db parameters
            conn = DriverManager.getConnection(url);// create a connection to the database
            PreparedStatement stmt = conn.prepareStatement(sql); //we use a prepared statement to input the values
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Movie movie = new Movie();
                movie.primaryTitle = rs.getString("primaryTitle");
                movie.titleType = rs.getString("titleType");
                movie.averageRating = rs.getString("averageRating");
                movie.numVotes = rs.getString("numVotes");
                movie.runtimeMinutes = rs.getString("runtimeMinutes");
                movie.genre1 = rs.getString("genre1");
                movie.genre2 = rs.getString("genre2");
                movie.genre3 = rs.getString("genre3");
                movie.director = rs.getString("director");
                movie.writer = rs.getString("writer");
                allMovies.add(movie);

            }
            ObservableList<Movie> data = FXCollections.observableArrayList();
            data.addAll(allMovies);
            System.out.println("number of movies in data: " + data.size());
            System.out.println(data.get(0).primaryTitle);
            movieView.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
