module pknoche.scheduling_application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires java.naming;

    opens pknoche.scheduling_application to javafx.fxml;
    exports pknoche.scheduling_application;
    opens pknoche.scheduling_application.controller to javafx.fxml;
    exports pknoche.scheduling_application.controller;
    opens pknoche.scheduling_application.model;
    exports pknoche.scheduling_application.model;
    opens pknoche.scheduling_application.reports;
    exports pknoche.scheduling_application.reports;
}