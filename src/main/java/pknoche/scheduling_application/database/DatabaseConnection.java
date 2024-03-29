package pknoche.scheduling_application.database;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Contains methods for establishing database connectivity.
 */
public class DatabaseConnection {
    /**
     * protocol of API used to connect to database
     */
    private static final String protocol = "jdbc";
    /**
     * Database vendor
     */
    private static final String vendor = ":mysql:";
    /**
     * Database server address
     */
    private static final String server = "//localhost/";
    /**
     * Name of database
     */
    private static final String databaseName = "client_schedule";
    /**
     * Concatenation of protocol, vendor, server, database name, and time zone property
     */
    private static final String URL = protocol + vendor + server + databaseName + "?connectionTimeZone = SERVER"; // assemble URL for connection and set time zone parameter to allow conversion to system default time
    /**
     * Username for connecting to database. Distinct from username entered by user.
     */
    private static final String username = "sqlUser";
    /**
     * Password for connecting to database. Distinct from password entered by user.
     */
    private static final String password = "Passw0rd!";
    /**
     * Variable for holding connection
     */
    public static Connection connection = null;

    /**
     * Attempts to open connection to database.
     */
    public static void openConnection() {
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUrl(URL);
            dataSource.setDatabaseName(databaseName);
            dataSource.setUser(username);
            dataSource.setPassword(password);
            connection = dataSource.getConnection();
            System.out.println("Database connection opened - " + getConnection());
        } catch (SQLException e) {
            System.out.println("There was an error connecting to the database. " +
                    "Please verify that the database server is running and try restarting the program.");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets database connection information used for generating and executing SQL queries.
     * @return database connection
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Closes connection to the database.
     */
    public static void closeConnection() {
        try{
            connection.close();
            System.out.println("Database connection closed");
        } catch (SQLException ignored) {
            // ignored because closeConnection() is only called upon closing program
        }
    }
}
