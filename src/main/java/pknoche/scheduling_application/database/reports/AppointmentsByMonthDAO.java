package pknoche.scheduling_application.database.reports;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.database.DatabaseConnection;
import pknoche.scheduling_application.helper.reports.AppointmentsByMonth;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentsByMonthDAO {
    private static final ObservableList<AppointmentsByMonth> allAppointmentsByMonth = FXCollections.observableArrayList();

    public static ObservableList<AppointmentsByMonth> getAll(int year) {
        if(allAppointmentsByMonth.isEmpty()) {
            try {
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
                PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
                ps.setInt(1, year);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {

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

    public static void refresh(int year) { //TODO - add functionality to call method on appointments list change
        allAppointmentsByMonth.clear();
        getAll(year);
    }
}