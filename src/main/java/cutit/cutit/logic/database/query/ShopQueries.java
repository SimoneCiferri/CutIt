package cutit.cutit.logic.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShopQueries {

    public static void insertShop(Statement stmt, String shopPIVA, String shopName, String employee, String location) throws SQLException {
        String insertStatement = String.format("INSERT INTO shop (Hairdresser_PIVA, ShopName, Employee, Location) VALUES ('%s', '%s', '%s', '%s')", shopPIVA, shopName, employee, location);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getShop(Statement stmt, String hairdresserPIVA) throws SQLException {
        String sql = "SELECT * FROM shop WHERE Hairdresser_PIVA = '" + hairdresserPIVA + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet getShopFromUser(Statement stmt, String userID) throws SQLException {
        String sql = "SELECT Hairdresser_PIVA, ShopName FROM user join hairdresser on UserID = HairdresserEmail join shop on PIVA = Hairdresser_PIVA WHERE UserID= '" + userID + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }


    }
