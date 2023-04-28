package pknoche.scheduling_application.database.reports;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.database.DatabaseConnection;
import pknoche.scheduling_application.reports.AppointmentsByMonth;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contains methods for reading data used for the Appointments By Month report from the database.
 */
public class AppointmentsByMonthDAO {
    /**
     * Observable list containing objects that contain data for populating the Appointments By Month report.
     */
    private static final ObservableList<AppointmentsByMonth> allAppointmentsByMonth = FXCollections.observableArrayList();

    /**
     * Queries the database to generate data for populating the Appointments By Month report. Generates a table
     * containing data that shows the number of each appointment type by month.
     *
     * @param year the year to generate the report for
     * @return observable list of objects containing data for populating the Appointments By Month report
     */
    public static ObservableList<AppointmentsByMonth> getAll(int year) {
        if(allAppointmentsByMonth.isEmpty()) {
                String sql = """
                        SELECT
                            MONTHNAME(Start) AS Month,
                            MONTH(Start) AS MonthNumber,
                            COUNT(CASE
                                WHEN Type = 'New Client' THEN 1
                            END) AS NewClient,
                            COUNT(CASE
                                WHEN Type = 'Planning Session' THEN 1
                            END) AS PlanningSession,
                            COUNT(CASE
                                WHEN Type = 'Status Update' THEN 1
                            END) AS StatusUpdate,
                            COUNT(CASE
                                WHEN Type = 'De-Briefing' THEN 1
                            END) AS DeBriefing
                        FROM
                            client_schedule.appointments
                        WHERE
                            YEAR(Start) = ?
                        GROUP BY Month , MonthNumber
                        ORDER BY MonthNumber;""";
                try(PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
                    ps.setInt(1, year);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {

                        String month = rs.getString("Month");
                        int newClient = rs.getInt("NewClient");
                        int planningSession = rs.getInt("PlanningSession");
                        int statusUpdate = rs.getInt("StatusUpdate");
                        int debriefing = rs.getInt("DeBriefing");
                        AppointmentsByMonth a = new AppointmentsByMonth(month, newClient,
                                planningSession, statusUpdate, debriefing);
                        allAppointmentsByMonth.add(a);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
        }
        return allAppointmentsByMonth;
    }

    /**
     * Clears data in the allAppointmentsByMonth list and calls the getAll() method to regenerate the data.
     *
     * @param year the year to generate the report for
     */
    public static void refresh(int year) {
        allAppointmentsByMonth.clear();
        getAll(year);
    }
}