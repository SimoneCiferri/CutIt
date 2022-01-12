package cutit.database.dao;

import cutit.database.DBConnection;
import cutit.database.query.FavoriteShopsQueries;
import cutit.exception.DuplicatedRecordException;
import cutit.model.Customer;
import cutit.model.FavoriteShop;
import cutit.model.Shop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class FavoriteShopsDAO {

    public static void insertFavoriteShop( String customerF, String shopNameF) throws Exception{
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = FavoriteShopsQueries.getAllFavouritesShop(stm, customerF);
        while (rs.next()) {
            String shopN = rs.getString(2);
            if(Objects.equals(shopNameF, shopN)){
                throw new DuplicatedRecordException(shopNameF + " already exists!");
            }
        }
        rs.close();
        stm.close();
        stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        FavoriteShopsQueries.insertShopToFav(stm, customerF, shopNameF);
        stm.close();
    }
}
