package cutit.cutit.logic.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceQueries {

    public static void insertService(Statement stmt, String serviceName, Float servicePrice, String shopName) throws SQLException {
        String insertStatement = String.format("INSERT INTO service (Name, Price, Shop_ShopName) VALUES ('%s', '%s', '%s')", serviceName, servicePrice , shopName);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static void deleteService(Statement stmt, String serviceName, String shopName) throws SQLException {
        String insertStatement = String.format("DELETE FROM service WHERE Name = '%s' and Shop_ShopName = '%s'", serviceName, shopName);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }


    public static ResultSet getService(Statement stmt, String serviceName, String shopName) throws SQLException {
        String sql = "SELECT * FROM service WHERE Name = '" + serviceName + "' AND ShopName = '" + shopName + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet getAllServices(Statement stmt, String shopNAme) throws SQLException {
        String sql = "SELECT * FROM service WHERE Shop_ShopName = '" + shopNAme + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
}
