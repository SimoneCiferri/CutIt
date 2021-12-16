package cutit.cutit.logic.database.query;


import cutit.cutit.logic.model.Hairdresser;

import java.sql.SQLException;
import java.sql.Statement;

public class HairdresserQueries {
    public static int insertHairdresser(Statement stmt, Hairdresser hairdresser) throws SQLException {
        String insertStatement = String.format("INSERT INTO hairdresser (HairdresserEmail, HName, HSurname, P.IVA) VALUES ('%s','%s','%s','%s')", hairdresser.getUserID(), hairdresser.getName(), hairdresser.getSurname(),hairdresser.getpIVA());
        System.out.println(insertStatement);
        return stmt.executeUpdate(insertStatement);
    }
}
