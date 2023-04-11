package pknoche.scheduling_application.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.model.Appointment;
import pknoche.scheduling_application.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CustomerDAO {
    public static void create(Customer customer) {

    }

    public static void read(String customerId) {

    }

    public static void update(String customerId) {
    }

    public static void delete(String customerId) {
    }

    public static ObservableList<Customer> getAll() {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM client_schedule.customers";
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int customer_ID = rs.getInt("Customer_ID");
                String customer_Name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postal_Code = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                LocalDateTime create_Date = rs.getTimestamp("Create_Date").toLocalDateTime();
                String created_By = rs.getString("Created_By");
                LocalDateTime last_Update = rs.getTimestamp("Last_Update").toLocalDateTime();
                String last_Updated_By = rs.getString("Last_Updated_By");
                int division_ID = rs.getInt("Division_ID");
                Customer customer = new Customer(customer_ID, customer_Name, address, postal_Code, phone, create_Date, created_By, last_Update, last_Updated_By, division_ID);
                allCustomers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customers from database.");
        }
        return allCustomers;
    }
}
