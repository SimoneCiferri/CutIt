package cutit.cutit.logic.database.query;


import cutit.cutit.logic.model.Hairdresser;

import java.sql.SQLException;
import java.sql.Statement;

public class HairdresserQueries {
    public static int insertHairdresser(Statement stmt, Hairdresser hairdresser) throws SQLException {
        String insertStatement = String.format("INSERT INTO hairdresser (PIVA, HairdresserEmail, HName, HSurname) VALUES ('%s','%s','%s','%s')", hairdresser.getpIVA(), hairdresser.getUserID(), hairdresser.getName(), hairdresser.getSurname());
        System.out.println(insertStatement);
        return stmt.executeUpdate(insertStatement);
    }
}
