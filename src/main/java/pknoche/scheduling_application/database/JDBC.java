package pknoche.scheduling_application.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String server = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String URL = protocol + vendor + server + databaseName + "?connectionTimeZone = SERVER"; // assemble URL for connection and set time zone parameter to allow conversion
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String username = "sqlUser"; // username for connecting to database
    private static final String password = "Passw0rd!"; // password for connecting to database
    public static Connection connection = null;

    public static void openConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, username, password);
            System.out.println("Connection opened - " + getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        try{
            connection.close();
            System.out.println("Connection closed");
        } catch (Exception e) {
            // catch exception and ignore because closeConnection() is only called upon closing program
        }

    }

}
