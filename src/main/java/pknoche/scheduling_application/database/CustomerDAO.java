package pknoche.scheduling_application.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.database.reports.CustomersByDivisionDAO;
import pknoche.scheduling_application.helper.DialogBox;
import pknoche.scheduling_application.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Contains methods for reading and modifying data associated with customers in the database.
 */
public class CustomerDAO {
    /**
     * List of all customers from the database.
     */
    private static final ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    /**
     * Generates and executes a SQL command to create a new customer using data passed in from a customer object.
     *
     * @param customer customer object containing data to be added to the database
     * @return true if the customer was successfully created
     */
    public static boolean create(Customer customer) {
            String sql = "INSERT INTO client_schedule.customers VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            try(PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
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
                CustomersByDivisionDAO.refresh();
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }

    }

    /**
     * Generates and executes a SQL command to get all customers from the database and puts them in a list.
     *
     * @return observable list of customers
     */
    public static ObservableList<Customer> getAll() {
        if(allCustomers.isEmpty()) {
                String sql = "SELECT * FROM client_schedule.customers;";
                try(PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
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

    /**
     * Clears data from the allCustomers list and calls the getAll() method to regenerate it from the database.
     */
    public static void refresh() {
        allCustomers.clear();
        getAll();
    }

    /**
     * Looks up a specific customer by customer ID.
     *
     * @param CustomerId customer ID associated with customer to be found
     * @return customer if ID was found
     */
    public static Customer get(int CustomerId) {
        for(Customer c : allCustomers) {
            if(c.getCustomer_ID() == CustomerId) {
                return c;
            }
        }
        return null;
    }


    /**
     * Generates and executes a SQL command to update an existing customer in the database.
     *
     * @param customer customer object containing data to be updated
     * @return true if the customer was successfully updated
     */
    public static boolean update(Customer customer) {
            String sql = """
                    UPDATE client_schedule.customers
                    SET
                        Customer_Name = ?,
                        Address = ?,
                        Postal_Code = ?,
                        Phone = ?,
                        Last_Update = ?,
                        Last_Updated_By = ?,
                        Division_ID = ?
                    WHERE
                        Customer_ID = ?;""";
            try(PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
                ps.setString(1, customer.getCustomer_Name());
                ps.setString(2, customer.getAddress());
                ps.setString(3, customer.getPostal_Code());
                ps.setString(4, customer.getPhone());
                ps.setTimestamp(5, Timestamp.valueOf(customer.getLast_Update()));
                ps.setString(6, customer.getLast_Updated_By());
                ps.setInt(7, customer.getDivision_ID());
                ps.setInt(8, customer.getCustomer_ID());
                ps.executeUpdate();
                refresh();
                CustomersByDivisionDAO.refresh();
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
    }

    /**
     * Generates and executes a SQL command to delete a customer from the database.
     *
     * @param customer customer object containing data to be deleted
     * @return true if the deletion was successful
     */
    public static boolean delete(Customer customer) {
            String sql = "DELETE FROM client_schedule.customers WHERE customer_ID = ?;";
            try(PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
                ps.setInt(1, customer.getCustomer_ID());
                ps.executeUpdate();
                refresh();
                CustomersByDivisionDAO.refresh();
                return true;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
    }
}
