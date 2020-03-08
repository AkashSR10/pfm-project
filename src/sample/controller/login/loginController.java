package sample.controller.login;
import java.lang.*;
import java.util.Properties;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.User;
import sample.model.DBQueries;
import sample.model.Login;
import sample.model.ViewSwitcher;

public class loginController {

    public static int store_id;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordText;

    @FXML
    private TextField emailText;

    @FXML
    private Button signupButton;

    @FXML
    private Text error;

    ViewSwitcher view = new ViewSwitcher();


    // Called when loginButton is pressed
    @FXML
   public void loadLogin(ActionEvent event) throws SQLException {
        // Make new Objects
        DBQueries query = new DBQueries();
        User user = new User();


        // Get text from inputs
        user.email = emailText.getText().trim();
        user.password = passwordText.getText().trim();

        // Checks if combination is correct and a  member
        if (query.memberLogin(user.email, user.password)) {
            store_id = query.Store_id(user.email, user.password);
            System.out.println(store_id);
            loginButton.getScene().getWindow().hide();
            view.memberMenu();
// Checks if combination is correct and an admin
        } else if (query.adminLogin(emailText.getText().trim(), passwordText.getText().trim())) {

            loginButton.getScene().getWindow().hide();
            System.out.println("hello");
            view.adminMenu();
            // Returns error if the user is not a member or admin
        } else {
            error.setText("Your email or password is incorrect");
        }
    }

    // Called when signupButton is pressed
    @FXML
    void loadSignup(ActionEvent event) {
            signupButton.getScene().getWindow().hide();
            view.signup();

    }

    @FXML
    void initialize() {

    }

//    public static Long getCurrentUserId() {
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();
//        String id = null;
//        if (authentication != null)
//            if (authentication.getPrincipal() instanceof UserDetails)
//                id = ((UserDetails) authentication.getPrincipal()).getUsername();
//            else if (authentication.getPrincipal() instanceof String)
//                id = (String) authentication.getPrincipal();
//        try {
//            return Long.valueOf(id != null ? id : "1"); //anonymoususer
//        } catch (NumberFormatException e) {
//            return 1L;
//        }
//    }
}


