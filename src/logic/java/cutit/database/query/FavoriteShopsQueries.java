package cutit.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FavoriteShopsQueries {

    public static void insertShopToFav(Statement stmt, String customerF, String shopF) throws SQLException {
        String insertStatement = String.format("INSERT INTO FavoriteShops (CustomerEmail, ShopName) VALUES ('%s', '%s')", customerF, shopF);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getAllFavouritesShop(Statement stmt, String customerF) throws SQLException {
        String sql = "SELECT * FROM FavoriteShops WHERE CustomerEmail = '" + customerF + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
}
