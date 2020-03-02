package sample.model;

import sample.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Signup {

    public boolean signUp(String email, String password, String firstName, String lastName,  int gender) {

        User user = new User();
        Connection conn;


        String saveMemberInfo = "INSERT INTO Member (Email, Password, First_name, Last_name, Gender) VALUES (?,?,?,?,?)"; //Add gender and admin when done

        try {
            String url = "jdbc:sqlite:db/test.db"; // db parameters
            conn = DriverManager.getConnection(url);// create a connection to the database
            PreparedStatement stmt = conn.prepareStatement(saveMemberInfo); //we use a prepared statement to input the values
            stmt.setString(1, user.email); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(2, user.password); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(3, user.firstName); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setString(4, user.lastName); //this line sets a value for the first question mark in string saveMemberInfo
            stmt.setInt(5, user.gender); //this line sets a value for the first question mark in string saveMemberInfo
            //add for admin
            stmt.executeUpdate();
            return true;
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }


}
