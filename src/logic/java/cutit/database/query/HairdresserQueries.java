package cutit.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HairdresserQueries {

    private HairdresserQueries(){}

    public static int insertHairdresser(Statement stmt, String hairdresserPIVA, String hairdresserUserID, String hName, String hSurname) throws SQLException {
        String insertStatement = String.format("INSERT INTO hairdresser (PIVA, HairdresserEmail, HName, HSurname) VALUES ('%s','%s','%s','%s')", hairdresserPIVA, hairdresserUserID, hName, hSurname);
        return stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getHairdresser(Statement stmt, String userID) throws SQLException {
        String sql = "SELECT * FROM hairdresser WHERE HairdresserEmail = '" + userID + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet checkIfPIVAExists(Statement stmt, String piva) throws SQLException {
        String sql = "SELECT COUNT(*) as isUsed FROM hairdresser WHERE PIVA = '" + piva + "'";
        return stmt.executeQuery(sql);
    }
}
