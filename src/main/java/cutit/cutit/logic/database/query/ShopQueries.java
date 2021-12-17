package cutit.cutit.logic.database.query;

import cutit.cutit.logic.model.Hairdresser;
import cutit.cutit.logic.model.Shop;
import cutit.cutit.logic.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShopQueries {

    public static void insertShop(Statement stmt, Shop shop) throws SQLException {
        String insertStatement = String.format("INSERT INTO shop (Hairdresser_PIVA, ShopName, Employee, Location) VALUES ('%s', '%s', '%s', '%s')", shop.getPIVA(), shop.getShopName(), ' ', ' ');
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getShop(Statement stmt, Hairdresser hairdresser) throws SQLException {
        String sql = "SELECT * FROM shop WHERE Hairdresser_PIVA = '" + hairdresser.getpIVA()+ "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }


    }
