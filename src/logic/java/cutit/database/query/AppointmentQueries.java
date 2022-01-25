package cutit.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AppointmentQueries {

    private AppointmentQueries(){}

    public static void insertAppointment(Statement stmt, List<String> appointmentStartAndEndTime, String shopName, String customerEmail, String serviceName, Float servicePrice, String promotionCode) throws SQLException {
        String insertStatement = String.format("INSERT INTO appointment (StartTime, EndTime, Shop_ShopName, Customer_CustomerID, Service_Name, Service_Price, Service_Shop_ShopName, Promotion_Code) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", appointmentStartAndEndTime.get(0), appointmentStartAndEndTime.get(1), shopName, customerEmail, serviceName, servicePrice, shopName, promotionCode);
        stmt.executeUpdate(insertStatement);
    }

    public static void insertAppointment(Statement stmt, List<String> appointmentStartAndEndTime, String shopName, String customerEmail, String serviceName, Float servicePrice) throws SQLException {
        String insertStatement = String.format("INSERT INTO appointment (StartTime, EndTime, Shop_ShopName, Customer_CustomerID, Service_Name, Service_Price, Service_Shop_ShopName) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')", appointmentStartAndEndTime.get(0), appointmentStartAndEndTime.get(1), shopName, customerEmail, serviceName, servicePrice, shopName);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getAllCustomerAppointments(Statement stmt, String customerID) throws SQLException {
        String sql = "SELECT * FROM appointment WHERE Customer_CustomerID = '" + customerID + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet getAllShopAppointments(Statement stmt, String shopName) throws SQLException {
        String sql = "SELECT * FROM appointment WHERE Shop_ShopName = '" + shopName + "'";
        return stmt.executeQuery(sql);
    }

    public static void deleteAppointment(Statement stmt, String appointmentStartTime, String shopName) throws SQLException {
        String insertStatement = String.format("DELETE FROM appointment WHERE StartTime = '%s' and Shop_ShopName = '%s'", appointmentStartTime, shopName);
        stmt.executeUpdate(insertStatement);
    }

}
