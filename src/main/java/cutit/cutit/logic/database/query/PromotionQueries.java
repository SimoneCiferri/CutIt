package cutit.cutit.logic.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PromotionQueries {

    public static void insertPromotion(Statement stmt, String promotionCode, Integer promValueOff, String expireDate, String serviceName, String serviceShop) throws SQLException {
        String insertStatement = String.format("INSERT INTO promotion (Code, Off, ExpireDate, Service_Name, Service_Shop_ShopName) VALUES ('%s', '%s', '%s', '%s', '%s')", promotionCode, promValueOff, expireDate, serviceName, serviceShop);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getAllPromotion(Statement stmt, String shopName) throws SQLException {
        String sql = "SELECT * FROM promotion WHERE Service_Shop_ShopName = '" + shopName + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static void deletePromotion(Statement stmt, String promotionCode) throws SQLException {
        String insertStatement = String.format("DELETE FROM promotion WHERE Code = '%s'", promotionCode);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

}
