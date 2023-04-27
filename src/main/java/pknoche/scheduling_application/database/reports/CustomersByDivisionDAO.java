package pknoche.scheduling_application.database.reports;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.database.DatabaseConnection;
import pknoche.scheduling_application.reports.CustomersByDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersByDivisionDAO {
    private static final ObservableList<CustomersByDivision> allCustomersByDivision =
            FXCollections.observableArrayList();

    public static ObservableList<CustomersByDivision> getAll() {
        if(allCustomersByDivision.isEmpty()) {
                String sql = """
                        SELECT
                            CONCAT(first_level_divisions.Division_ID,
                                    ' - ',
                                    first_level_divisions.Division) AS Division,
                            COUNT(customers.Division_ID) AS CustomerCount
                        FROM
                            client_schedule.first_level_divisions
                                INNER JOIN
                            client_schedule.customers USING (Division_ID)
                        GROUP BY customers.Division_ID;""";
                try(PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        String division = rs.getString("Division");
                        int customerCount = rs.getInt("CustomerCount");
                        CustomersByDivision c = new CustomersByDivision(division, customerCount);
                        allCustomersByDivision.add(c);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
        }
        return allCustomersByDivision;
    }

    public static void refresh() {
        allCustomersByDivision.clear();
        getAll();
    }
}
