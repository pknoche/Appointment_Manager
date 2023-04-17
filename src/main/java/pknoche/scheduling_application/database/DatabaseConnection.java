package pknoche.scheduling_application.database;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DatabaseConnection {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String server = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String URL = protocol + vendor + server + databaseName + "?connectionTimeZone = SERVER"; // assemble URL for connection and set time zone parameter to allow conversion to system default time
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String username = "sqlUser"; // username for connecting to database
    private static final String password = "Passw0rd!"; // password for connecting to database
    public static Connection connection = null;

    public static void openConnection() {


        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUrl(URL);
            dataSource.setDatabaseName(databaseName);
            dataSource.setUser(username);
            dataSource.setPassword(password);
            connection = dataSource.getConnection();
            System.out.println("Connection opened - " + getConnection());
        } catch (Exception e) {
            System.out.println("There was an error connecting to the database. " +
                    "Please verify that the database server is running and try restarting the program.");
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
