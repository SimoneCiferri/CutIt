package cutit.database.dao;

import cutit.database.DBConnection;
import cutit.database.query.FavoriteShopsQueries;
import cutit.exception.DuplicatedRecordException;
import cutit.model.Customer;
import cutit.model.FavoriteShop;
import cutit.model.Service;
import cutit.model.Shop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FavoriteShopsDAO {

    public static void insertFavoriteShop(String customerEmail, String shopNameF) throws Exception{
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = FavoriteShopsQueries.getAllFavouritesShop(stm, customerEmail);
        while (rs.next()) {
            String shopN = rs.getString(2);
            if(Objects.equals(shopNameF, shopN)){
                throw new DuplicatedRecordException(shopNameF + " already added!");
            }
        }
        rs.close();
        stm.close();
        stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        FavoriteShopsQueries.insertShopToFav(stm, customerEmail, shopNameF);
        stm.close();
    }

    public static List<Shop> getFavouritesShops(String customerEmail) throws Exception{
        List<Shop> favList = new ArrayList<>();
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = FavoriteShopsQueries.getAllFavouritesShop(stm, customerEmail);
        if (rs.first()) {
            rs.first();
            do {
                String sName = rs.getString(2);
                Shop s = ShopDAO.getShopFromName(sName);
                favList.add(s);
            } while (rs.next());
            rs.close();
            stm.close();
            //DBConnection.getInstance().closeConnection();
        }
        return favList;
    }
}
