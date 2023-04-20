package pknoche.scheduling_application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pknoche.scheduling_application.database.UserDAO;
import pknoche.scheduling_application.helper.DialogBox;
import pknoche.scheduling_application.helper.GUI_Navigator;
import pknoche.scheduling_application.helper.TimeConversion;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Label localeLabel;
    @FXML
    private Button loginButton;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private Label usernameLabel;
    private String invalidLoginPrompt = "Invalid Username/Password. Please try again.";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            resourceBundle = ResourceBundle.getBundle("pknoche.scheduling_application.localization.languages", Locale.getDefault());
            usernameLabel.setText(resourceBundle.getString("usernameLabel"));
            passwordLabel.setText(resourceBundle.getString("passwordLabel"));
            loginButton.setText(resourceBundle.getString("loginButton"));
            localeLabel.setText(resourceBundle.getString("localeLabel") + Locale.getDefault().getDisplayName());
            localeLabel.setVisible(true);
            invalidLoginPrompt = resourceBundle.getString("invalidLoginPrompt");
        } catch (MissingResourceException ignored) {}
    }

    @FXML
    void onLoginButtonClick(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if(UserDAO.loginIsValid(username, password)) {
            try {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("logs/login_history.txt", true)));
                printWriter.println(TimeConversion.toUTC(LocalDateTime.now()));
                printWriter.println("Username: " + username);
                printWriter.println("Login Successful");
                printWriter.println();
                printWriter.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            GUI_Navigator.replaceScene("MainMenu", "Scheduling Application", event);
        }
        else {
            try {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("logs/login_history.txt", true)));
                printWriter.println(TimeConversion.toUTC(LocalDateTime.now()));
                printWriter.println("Username: " + username);
                printWriter.println("Login Failed");
                printWriter.println();
                printWriter.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            DialogBox.generateErrorMessage(invalidLoginPrompt);
        }
    }
}