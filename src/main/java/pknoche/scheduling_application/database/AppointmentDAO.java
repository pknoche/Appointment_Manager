package pknoche.scheduling_application.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.helper.DialogBox;
import pknoche.scheduling_application.model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Contains methods for reading and modifying data associated with appointments in the database.
 */
public class AppointmentDAO {
    /**
     * List of all appointments from the database.
     */
    private static final ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    /**
     * Generates and executes a SQL command to create a new appointment using data passed in from an appointment object.
     *
     * @param appointment appointment object containing data to be added to the database
     * @return true if the appointment was successfully created
     */
    public static boolean create(Appointment appointment) {
            String sql = """
                    INSERT INTO client_schedule.appointments VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);""";
            try(PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
                ps.setString(1, appointment.getTitle());
                ps.setString(2, appointment.getDescription());
                ps.setString(3, appointment.getLocation());
                ps.setString(4, appointment.getType());
                ps.setTimestamp(5, Timestamp.valueOf(appointment.getStart()));
                ps.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));
                ps.setTimestamp(7, Timestamp.valueOf(appointment.getCreate_Date()));
                ps.setString(8, appointment.getCreated_By());
                ps.setTimestamp(9, Timestamp.valueOf(appointment.getLast_Update()));
                ps.setString(10, appointment.getLast_Updated_By());
                ps.setInt(11, appointment.getCustomer_ID());
                ps.setInt(12, appointment.getUser_ID());
                ps.setInt(13, appointment.getContact_ID());
                ps.executeUpdate();
                refresh();
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
    }

    /**
     * Generates and executes a SQL command to get all appointments from the database and puts them in a list.
     *
     * @return observable list of appointments
     */
    public static ObservableList<Appointment> getAll() {
        if(allAppointments.isEmpty()) {
                String sql = """
                        SELECT
                            appointments.*,
                            customers.Customer_Name,
                            users.User_Name,
                            contacts.Contact_Name
                        FROM
                            client_schedule.appointments
                                INNER JOIN
                            client_schedule.customers USING (Customer_ID)
                                INNER JOIN
                            client_schedule.users USING (User_ID)
                                INNER JOIN
                            client_schedule.contacts USING (Contact_ID)
                        ORDER BY Start;""";
                try(PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int Appointment_ID = rs.getInt("Appointment_ID");
                        String Title = rs.getString("Title");
                        String Description = rs.getString("Description");
                        String Location = rs.getString("Location");
                        String Type = rs.getString("Type");
                        LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
                        LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
                        LocalDateTime Create_Date = rs.getTimestamp("Create_Date").toLocalDateTime();
                        String Created_By = rs.getString("Created_By");
                        LocalDateTime Last_Update = rs.getTimestamp("Last_Update").toLocalDateTime();
                        String Last_Updated_By = rs.getString("Last_Updated_By");
                        int Customer_ID = rs.getInt("Customer_ID");
                        int User_ID = rs.getInt("User_ID");
                        int Contact_ID = rs.getInt("Contact_ID");
                        String Customer_Name = rs.getString("Customer_Name");
                        String User_Name = rs.getString("User_Name");
                        String Contact_Name = rs.getString("Contact_Name");
                        Appointment appointment = new Appointment(Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID, Customer_Name, User_Name, Contact_Name);
                        allAppointments.add(appointment);
                    }
                } catch (SQLException e) {
                    DialogBox.generateErrorMessage("Error retrieving appointments from database.");
                    System.out.println(e.getMessage());
                }
        }
        return allAppointments;
    }

    /**
     * Clears data from the allAppointments list and calls the getAll() method to regenerate it from the database.
     */
    public static void refresh() {
        allAppointments.clear();
        getAll();
    }

    /**
     * Generates and executes a SQL command to update an existing appointment in the database.
     *
     * @param appointment appointment object containing data to be updated
     * @return true if the appointment was successfully updated
     */
    public static boolean update(Appointment appointment) {
            String sql = """
                     UPDATE client_schedule.appointments
                     SET
                         Title = ?,
                         Description = ?,
                         Location = ?,
                         Type = ?,
                         Start = ?,
                         End = ?,
                         Last_Update = ?,
                         Last_Updated_By = ?,
                         Customer_ID = ?,
                         User_ID = ?,
                         Contact_ID = ?
                     WHERE
                         Appointment_ID = ?;""";
            try(PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
                ps.setString(1, appointment.getTitle());
                ps.setString(2, appointment.getDescription());
                ps.setString(3, appointment.getLocation());
                ps.setString(4, appointment.getType());
                ps.setTimestamp(5, Timestamp.valueOf(appointment.getStart()));
                ps.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));
                ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(8, UserDAO.getCurrentUser());
                ps.setInt(9, appointment.getCustomer_ID());
                ps.setInt(10, appointment.getUser_ID());
                ps.setInt(11, appointment.getContact_ID());
                ps.setInt(12, appointment.getAppointment_ID());
                ps.executeUpdate();
                refresh();
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
    }

    /**
     * Generates and executes a SQL command to delete an appointment from the database given a specific appointment.
     *
     * @param appointment appointment object containing data to be deleted
     * @return true if the deletion was successful
     */
    public static boolean delete(Appointment appointment) {
            String sql = "DELETE FROM client_schedule.appointments WHERE Appointment_ID = ?;";
            try(PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
                ps.setInt(1, appointment.getAppointment_ID());
                ps.executeUpdate();
                refresh();
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
    }

    /**
     * Generates and executes a SQL command to delete all appointments from the database associated with a specific
     * customer ID.
     *
     * @param customerId customer ID to delete associated appointments for
     * @return true if the deletion was successful
     */
    public static boolean delete(int customerId) {
            String sql = "DELETE FROM client_schedule.appointments WHERE Customer_ID = ?;";
            try(PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
                ps.setInt(1, customerId);
                ps.executeUpdate();
                refresh();
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
    }

    /**
     * Checks if a new appointment overlaps with an existing appointment for a specific customer.
     *
     * @param newAppointment appointment object containing data to check for overlap
     * @return true if an overlap is found
     */
    public static boolean appointmentOverlaps(Appointment newAppointment) {
        for(Appointment a : allAppointments) {
            if((newAppointment.getCustomer_ID() == a.getCustomer_ID()) &&
                    (newAppointment.getAppointment_ID() != a.getAppointment_ID())) {
                if ((newAppointment.getStart().isAfter(a.getStart()) || newAppointment.getStart().isEqual(a.getStart()))
                        && (newAppointment.getStart().isBefore(a.getEnd()))) {
                    return true;
                }
                if ((newAppointment.getEnd().isAfter(a.getStart())) &&
                        (newAppointment.getEnd().isBefore(a.getEnd()) || newAppointment.getEnd().isEqual(a.getEnd()))) {
                    return true;
                }
                if ((newAppointment.getStart().isBefore(a.getStart()) || newAppointment.getStart().isEqual(a.getStart()))
                        && (newAppointment.getEnd().isAfter(a.getEnd()) || newAppointment.getEnd().isEqual(a.getEnd()))) {
                    return true;
                }
            }
        }
        return false;
    }
}