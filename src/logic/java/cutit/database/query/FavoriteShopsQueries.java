package cutit.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FavoriteShopsQueries {

    private FavoriteShopsQueries(){}

    public static void insertShopToFav(Statement stmt, String customerF, String shopF) throws SQLException {
        String insertStatement = String.format("INSERT INTO FavoriteShops (CustomerEmail, ShopName) VALUES ('%s', '%s')", customerF, shopF);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getAllFavouritesShop(Statement stmt, String customerF) throws SQLException {
        String sql = "SELECT * FROM FavoriteShops WHERE CustomerEmail = '" + customerF + "'";
        return stmt.executeQuery(sql);
    }
}
