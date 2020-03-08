package sample.model;
import sample.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin extends User {
    Connection c = null;                                                             // Declare connection var
    ResultSet rs = null;                                                             // Declare Resultset
    PreparedStatement stmt = null;                                                  // Declare PreparedStatement
    String url = "jdbc:sqlite:db/pfm.db";                                            // db parameters
    String jdbc = "org.sqlite.JDBC";                                                 // JBDC parameters
}
