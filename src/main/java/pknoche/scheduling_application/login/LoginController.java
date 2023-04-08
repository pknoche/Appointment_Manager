package pknoche.scheduling_application.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pknoche.scheduling_application.database.DatabaseLogin;
import pknoche.scheduling_application.helper.DialogBox;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button loginButton;

    @FXML
    void onLoginButtonClick(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean loginValid = DatabaseLogin.isLoginValid(username, password);
        if(loginValid) {
            System.out.println("Login valid");
        }
        else {
            System.out.println("Login not valid");
            DialogBox.generateErrorMessage("Invalid Username/Password. Please try again.");
        }

    }
}