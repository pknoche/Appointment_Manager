module pknoche.scheduling_application {
    requires javafx.controls;
    requires javafx.fxml;


    opens pknoche.scheduling_application to javafx.fxml;
    exports pknoche.scheduling_application;
    exports pknoche.scheduling_application.login;
    opens pknoche.scheduling_application.login to javafx.fxml;
}