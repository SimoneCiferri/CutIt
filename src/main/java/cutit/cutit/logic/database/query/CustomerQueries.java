package cutit.cutit.logic.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerQueries {

    public static void insertCustomer(Statement stmt, String userID, String birthdate, String gender, String cName, String cSurname) throws SQLException {
        String insertStatement = String.format("INSERT INTO customer (CustomerEmail, Birthdate, Gender, CName, CSurname) VALUES ('%s',%s,'%s','%s','%s')", userID, birthdate, gender, cName, cSurname);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getCustomer(Statement stmt, String userID) throws SQLException {
        String sql = "SELECT * FROM customer WHERE CustomerEmail = '" + userID + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

}
