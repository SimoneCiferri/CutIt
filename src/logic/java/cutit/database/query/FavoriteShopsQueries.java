package cutit.database.query;

import java.sql.SQLException;
import java.sql.Statement;

public class FavoriteShopsQueries {

    public static void insertShopToFav(Statement stmt, String customerF, String shopF) throws SQLException {
        String insertStatement = String.format("INSERT INTO FavoriteShops (CustomerEmail, ShopName) VALUES ('%s', '%s')", customerF, shopF);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }
}
