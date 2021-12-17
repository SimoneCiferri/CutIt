package cutit.cutit.logic.database.query;


import cutit.cutit.logic.model.Hairdresser;
import cutit.cutit.logic.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HairdresserQueries {
    public static int insertHairdresser(Statement stmt, Hairdresser hairdresser) throws SQLException {
        String insertStatement = String.format("INSERT INTO hairdresser (PIVA, HairdresserEmail, HName, HSurname) VALUES ('%s','%s','%s','%s')", hairdresser.getpIVA(), hairdresser.getUserID(), hairdresser.getName(), hairdresser.getSurname());
        System.out.println(insertStatement);
        return stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getHairdresser(Statement stmt, User user) throws SQLException {
        String sql = "SELECT * FROM hairdresser WHERE HairdresserEmail = '" + user.getUserID() + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
}
