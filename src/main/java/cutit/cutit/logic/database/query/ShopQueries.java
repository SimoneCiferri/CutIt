package cutit.cutit.logic.database.query;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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

    public static ResultSet getShopFromName(Statement stmt, String shopName) throws SQLException {
        String sql = "SELECT * FROM shop WHERE ShopName = '" + shopName + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet getShopFromUser(Statement stmt, String userID) throws SQLException {
        String sql = "SELECT Hairdresser_PIVA, ShopName FROM user join Hairdresser on UserID = HairdresserEmail join shop on PIVA = Hairdresser_PIVA WHERE UserID= '" + userID + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet getOpenDays(Statement stmt, String shopName) throws SQLException {
        String sql = "SELECT * FROM opendays WHERE ODShopName = '" + shopName + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static void updateShop(Statement stmt,String shopName, String address, String phoneNumber, String employee, String description, String openTime, String closeTime) throws SQLException {
        String sql = "UPDATE shop SET Employee = '" + employee + "', Address = '" + address +"', PhoneNumber = '" + phoneNumber + "', Description = '" + description + "', OpenTime = '" + openTime +"', CloseTime = '" + closeTime + "' WHERE ShopName = '" + shopName + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }

    public static void insertOpenDay(Statement stmt,String shopName, Integer day, Integer open) throws SQLException {
        String insertStatement = String.format("INSERT INTO opendays(ODShopName, Day, Open) VALUES ('%s', '%d', '%d')", shopName, day, open);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static void updateOpenDay(Statement stmt,String shopName, Integer day, Integer open) throws SQLException {
        String insertStatement = "UPDATE opendays SET Open = '" + open + "' WHERE ODShopName = '" + shopName + "' and Day = '" + day + "'";
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static void insertImage(Statement stmt, String shopName,Integer imageID, File image) throws SQLException {
        String insertStatement = String.format("INSERT INTO image (RefShop, ImageID, Image) VALUES ('%s', '%d', '%s')", shopName, imageID, image);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    }
