package cutit.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PromotionQueries {

    private PromotionQueries(){}

    public static void insertPromotion(Statement stmt, String promotionCode, Integer promValueOff, String expireDate, String serviceName, String serviceShop) throws SQLException {
        String insertStatement = String.format("INSERT INTO promotion (Code, Off, ExpireDate, Service_Name, Service_Shop_ShopName) VALUES ('%s', '%s', '%s', '%s', '%s')", promotionCode, promValueOff, expireDate, serviceName, serviceShop);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getAllPromotion(Statement stmt, String shopName) throws SQLException {
        String sql = "SELECT * FROM promotion WHERE Service_Shop_ShopName = '" + shopName + "'";
        return stmt.executeQuery(sql);
    }

    public static void deletePromotion(Statement stmt, String promotionCode) throws SQLException {
        String insertStatement = String.format("DELETE FROM promotion WHERE Code = '%s'", promotionCode);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getPromotion(Statement stmt, String code) throws SQLException {
        String sql = "SELECT * FROM promotion WHERE Code = '" + code + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet getAllCustomerPromotion(Statement stmt, String customerID) throws SQLException {
        String sql = "SELECT * FROM customer_has_promotion join promotion on Promotion_Code = Code WHERE Customer_CustomerEmail = '" + customerID + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet getPersonalPromotion(Statement stmt, String customerID, String code) throws SQLException {
        String sql = "SELECT * FROM customer_has_promotion WHERE Customer_CustomerEmail = '" + customerID + "' and Promotion_Code = '" + code + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet getAllPersonalPromotions(Statement stmt, String customerID) throws SQLException {
        String sql = "SELECT * FROM customer_has_promotion WHERE Customer_CustomerEmail = '" + customerID + "'";
        return stmt.executeQuery(sql);
    }

    public static void insertPersonalPromotion(Statement stmt, String customer, String code) throws SQLException {
        String sql = String.format("INSERT INTO customer_has_promotion(Customer_CustomerEmail, Promotion_Code) VALUES('%s', '%s')", customer, code);
        stmt.executeUpdate(sql);
    }
}
