package cutit.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppointmentQueries {

    public static void insertAppointment(Statement stmt, String startTime, String endTime, String shopName, String customerEmail, String serviceName, Float servicePrice, String promotionCode) throws SQLException {
        String insertStatement = String.format("INSERT INTO appointment (StartTime, EndTime, Shop_ShopName, Customer_CustomerID, Service_Name, Service_Price, Service_Shop_ShopName, Promotion_Code) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", startTime, endTime, shopName, customerEmail, serviceName, servicePrice, shopName, promotionCode);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getAllCustomerAppointments(Statement stmt, String customerID) throws SQLException {
        String sql = "SELECT * FROM appointment WHERE Customer_CustomerID = '" + customerID + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet getAllShopAppointments(Statement stmt, String shopName) throws SQLException {
        String sql = "SELECT * FROM appointment WHERE Shop_ShopName = '" + shopName + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

}
