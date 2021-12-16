package cutit.cutit.logic.database.query;

import cutit.cutit.logic.model.Customer;

import java.sql.SQLException;
import java.sql.Statement;

class UserQueries {

    public static int insertCustomer(Statement stmt, Customer customer) throws SQLException {
        String insertStatement = String.format("INSERT INTO customer (CustomerID, NameSurname, Age, Gender) VALUES (%s,'%s','%s',%s)"); //vedi perchè '%s' e non %s
        System.out.println(insertStatement);
        return stmt.executeUpdate(insertStatement);
    }

    public static int insertHairdresser(Statement stmt, Customer customer) throws SQLException {
        String insertStatement = String.format("INSERT INTO hairdresser (P.IVA, hairdresserID, NameSurname) VALUES (%s,'%s',%s)"); //vedi perchè '%s' e non %s
        System.out.println(insertStatement);
        return stmt.executeUpdate(insertStatement);
    }

}
