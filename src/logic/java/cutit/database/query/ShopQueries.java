package cutit.database.query;

import cutit.database.DBConnection;
import cutit.exception.DBConnectionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;

public class ShopQueries {

    private ShopQueries(){}

    public static void insertShop(Statement stmt, String shopName, String hairdresserPIVA) throws SQLException {
        String insertStatement = String.format("INSERT INTO shop (ShopName, Hairdresser_PIVA) VALUES ('%s', '%s')", shopName, hairdresserPIVA);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getShop(Statement stmt, String shopPIVA) throws SQLException {
        String sql = "SELECT * FROM shop WHERE Hairdresser_PIVA = '" + shopPIVA + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet getShopFromName(Statement stmt, String shopName) throws SQLException {
        String sql = "SELECT * FROM shop WHERE ShopName = '" + shopName + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet getOpenDays(Statement stmt, String shopName) throws SQLException {
        String sql = "SELECT * FROM opendays WHERE ODShopName = '" + shopName + "'";
        return stmt.executeQuery(sql);
    }

    public static void updateShop(Statement stmt, String shopName, String address, String phoneNumber, String employee, String description, List<String> openAndCloseTimeList) throws SQLException {
        String sql = "UPDATE shop SET Employee = '" + employee + "', Address = '" + address +"', PhoneNumber = '" + phoneNumber + "', Description = '" + description + "', OpenTime = '" + openAndCloseTimeList.get(0) +"', CloseTime = '" + openAndCloseTimeList.get(1) + "' WHERE ShopName = '" + shopName + "'";
        stmt.executeUpdate(sql);
    }

    public static void insertOpenDay(Statement stmt, String shopName, Integer day, Integer open) throws SQLException {
        String insertStatement = String.format("INSERT INTO opendays(ODShopName, Day, Open) VALUES ('%s', '%d', '%d')", shopName, day, open);
        stmt.executeUpdate(insertStatement);
    }

    public static void updateOpenDays(Statement stmt, String shopName, Integer day, Integer open) throws SQLException {
        String insertStatement = "UPDATE opendays SET Open = '" + open + "' WHERE ODShopName = '" + shopName + "' and Day = '" + day + "'";
        stmt.executeUpdate(insertStatement);
    }

    public static void insertImage(String imageID, File file, String shopName) throws SQLException, DBConnectionException, FileNotFoundException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO image (Id, Size, Image, RefShop) VALUES (?, ?, ?, ?)");
        statement.setString(1,imageID);
        statement.setInt(2,99);
        FileInputStream input = new FileInputStream(file);
        statement.setBinaryStream(3, input, (int)file.length());
        statement.setString(4, shopName);
        statement.executeUpdate();
    }

    public static void deleteAllImages(Statement stmt, String shopName) throws SQLException{
        String insertStatement = "DELETE FROM image WHERE RefShop = '" + shopName + "'";
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getImages(Statement stmt, String shopName) throws SQLException {
        String sql = "SELECT * FROM image WHERE RefShop = '" + shopName + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet getShops(Statement stmt) throws SQLException {
        String sql = "SELECT * FROM shop";
        return stmt.executeQuery(sql);
    }

    public static ResultSet checkIfShopExists(Statement stmt, String shopName) throws SQLException {
        String sql = "SELECT COUNT(*) as isUsed FROM shop WHERE ShopName = '" + shopName + "'";
        return stmt.executeQuery(sql);
    }
}
