package cutit.cutit.logic.database.query;

import cutit.cutit.logic.model.Service;
import cutit.cutit.logic.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceQueries {


    public static void insertService(Statement stmt, Service service) throws SQLException {
        String insertStatement = String.format("INSERT INTO service (Name, Price, Shop_ShopName) VALUES ('%s', '%f', '%s')", service.getServiceName(), service.getPrice(), service.getShopName());
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getService(Statement stmt, Service service) throws SQLException {
        String sql = "SELECT * FROM service WHERE Name = '" + service.getServiceName() + "' AND Price = '" + service.getPrice() + "' AND ShopName = " + service.getShopName() + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
}
