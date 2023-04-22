package pknoche.scheduling_application.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.helper.DialogBox;
import pknoche.scheduling_application.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class CustomerDAO {
    private static final ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    public static boolean create(Customer customer) {
        try {
            String sql = "INSERT INTO client_schedule.customers VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setString(1, customer.getCustomer_Name());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getPostal_Code());
            ps.setString(4, customer.getPhone());
            ps.setTimestamp(5, Timestamp.valueOf(customer.getCreate_Date()));
            ps.setString(6, customer.getCreated_By());
            ps.setTimestamp(7, Timestamp.valueOf(customer.getLast_Update()));
            ps.setString(8, customer.getLast_Updated_By());
            ps.setInt(9, customer.getDivision_ID());
            ps.executeUpdate();
            refresh();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public static ObservableList<Customer> getAll() {
        if(allCustomers.isEmpty()) {
            try {
                allCustomers.clear();
                String sql = "SELECT * FROM client_schedule.customers";
                PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
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
                    Customer customer = new Customer(customer_ID, customer_Name, address, postal_Code, phone, create_Date,
                            created_By, last_Update, last_Updated_By, division_ID);
                    allCustomers.add(customer);
                }
            } catch (SQLException e) {
                DialogBox.generateErrorMessage("Error retrieving customers from database.");
                System.out.println(e.getMessage());
            }
        }
        return allCustomers;
    }

    public static void refresh() {
        allCustomers.clear();
        getAll();
    }

    public static Customer get(int CustomerId) {
        for(Customer c : allCustomers) {
            if(c.getCustomer_ID() == CustomerId) {
                return c;
            }
        }
        return null;
    }

    public static boolean update(Customer customer) {
        try {
            String sql = "UPDATE client_schedule.customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, " +
                    "Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? " +
                    "WHERE Customer_ID = ?";
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setString(1, customer.getCustomer_Name());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getPostal_Code());
            ps.setString(4, customer.getPhone());
            ps.setTimestamp(5, Timestamp.valueOf(customer.getLast_Update()));
            ps.setString(6, customer.getLast_Updated_By());
            ps.setInt(7, customer.getDivision_ID());
            ps.setInt(8, customer.getCustomer_ID());
            System.out.println(ps);
            ps.executeUpdate();
            refresh();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean delete(Customer customer) {
        try {
            String sql = "DELETE FROM client_schedule.customers WHERE customer_ID = ?";
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, customer.getCustomer_ID());
            ps.executeUpdate();
            refresh();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
