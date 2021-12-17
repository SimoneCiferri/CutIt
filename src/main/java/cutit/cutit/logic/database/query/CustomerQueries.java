package cutit.cutit.logic.database.query;

import cutit.cutit.logic.model.Customer;
import cutit.cutit.logic.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerQueries {

    public static void insertCustomer(Statement stmt, Customer customer) throws SQLException {
        String insertStatement = String.format("INSERT INTO customer (CustomerEmail, Age, Gender, CName, CSurname) VALUES ('%s',%d,'%s','%s','%s')", customer.getUserID(), customer.getAge(), customer.getGender(), customer.getName(), customer.getSurname());
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getCustomer(Statement stmt, User user) throws SQLException {
        String sql = "SELECT * FROM customer WHERE CustomerEmail = '" + user.getUserID() + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

}
