package cutit.cutit.logic.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PromotionQueries {

    public static void insertPromotion(Statement stmt, String promotionCode, String shopName, Integer promValueOff, String expireDate, String promName, String serviceName, Float servicePrice, String serviceShop) throws SQLException {
        String insertStatement = String.format("INSERT INTO promotion (Code, Shop_ShopName, Off, ExpireDate, Name, Service_Name, Service_Price, Service_Shop_ShopName) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", promotionCode, shopName, promValueOff, expireDate, promName, serviceName, servicePrice, serviceShop);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getAllPromotion(Statement stmt, String shopName) throws SQLException {
        String sql = "SELECT * FROM promotion WHERE Shop_ShopName = '" + shopName + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

}
