package cutit.cutit.logic.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShopQueries {

    public static void insertShop(Statement stmt, String shopName, String hairdresserPIVA) throws SQLException {
        String insertStatement = String.format("INSERT INTO shop (ShopName, Hairdresser_PIVA) VALUES ('%s', '%s')", shopName, hairdresserPIVA);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getShop(Statement stmt, String shopPIVA) throws SQLException {
        String sql = "SELECT * FROM shop WHERE Hairdresser_PIVA = '" + shopPIVA + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet getShopFromUser(Statement stmt, String userID) throws SQLException {
        String sql = "SELECT Hairdresser_PIVA, ShopName FROM user join Hairdresser on UserID = HairdresserEmail join shop on PIVA = Hairdresser_PIVA WHERE UserID= '" + userID + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet getOpenDays(Statement stmt, String shopPIVA) throws SQLException {
        String sql = "SELECT * FROM opendays WHERE ODShopName = '" + shopPIVA + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet updateShop(Statement stmt,String shopName, String address, String phoneNumber, String employee, String description, String openTime, String closeTime) throws SQLException {
        String sql = "UPDATE shop SET Employee = '" + employee + "', Address = '" + address +"', PhoneNumber = '" + phoneNumber + "', Description = '" + description + "', OpenTime = '" + openTime +"', CloseTime = '" + closeTime + "' WHERE ShopName = '" + shopName + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }


    }
