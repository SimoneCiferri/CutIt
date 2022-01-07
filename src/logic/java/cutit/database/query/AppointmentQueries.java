package cutit.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppointmentQueries {

    public static ResultSet getAllCustomerAppointments(Statement stmt, String customerID) throws SQLException {
        String sql = "SELECT * FROM appointment WHERE Customer_CustomerID = '" + customerID + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

}
