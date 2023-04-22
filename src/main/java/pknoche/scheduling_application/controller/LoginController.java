package pknoche.scheduling_application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pknoche.scheduling_application.database.UserDAO;
import pknoche.scheduling_application.helper.AppointmentAlert;
import pknoche.scheduling_application.helper.DialogBox;
import pknoche.scheduling_application.helper.GUI_Navigator;
import pknoche.scheduling_application.helper.TimeConversion;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController {
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private Label localeLabel;
    @FXML
    private ResourceBundle resources;

    @FXML
    private void initialize() {
        localeLabel.setText(localeLabel.getText() + Locale.getDefault().getDisplayName());
    }

    @FXML
    private void onLoginButtonClick(ActionEvent event) {
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
            AppointmentAlert.execute();
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
            DialogBox.generateErrorMessage(resources.getString("invalidLoginPrompt"));
        }
    }
}