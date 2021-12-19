package cutit.cutit.logic.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PromotionQueries {

    public static ResultSet getAllPromotion(Statement stmt, String shopName) throws SQLException {
        String sql = "SELECT * FROM promotion WHERE Shop_ShopName = '" + shopName + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

}
