package cutit.cutit.logic.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShopQueries {

    public static void insertShop(Statement stmt, String shopName, String employee, String latitude, String hairdresserPIVA, String longitude, String phoneNumber, String description, String openTime, String closeTime) throws SQLException {
        String insertStatement = String.format("INSERT INTO shop (ShopName, Employee, Latitude, Hairdresser_PIVA, Longitude, PhoneNumber, Description, OpenTime, CloseTime) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", shopName, employee, latitude, hairdresserPIVA, longitude, phoneNumber, description, openTime, closeTime);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getShop(Statement stmt, String ShopName ,String shopPIVA) throws SQLException {
        String sql = "SELECT * FROM shop WHERE ShopName = '" + ShopName + "' AND Hairdresser_PIVA = '" + shopPIVA + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet getShopFromUser(Statement stmt, String userID) throws SQLException {
        String sql = "SELECT Hairdresser_PIVA, ShopName FROM user join Hairdresser on UserID = HairdresserEmail join shop on PIVA = Hairdresser_PIVA WHERE UserID= '" + userID + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }


    }
