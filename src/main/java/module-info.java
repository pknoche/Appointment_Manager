module pknoche.scheduling_application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens pknoche.scheduling_application to javafx.fxml;
    exports pknoche.scheduling_application;
    exports pknoche.scheduling_application.login;
    opens pknoche.scheduling_application.login to javafx.fxml;
}