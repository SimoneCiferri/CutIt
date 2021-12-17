package cutit.cutit.logic.database.query;

import cutit.cutit.logic.model.Customer;
import cutit.cutit.logic.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserQueries {

    public static void insertUser(Statement stmt, User user) throws SQLException {
        String insertStatement = String.format("INSERT INTO user (UserID, Password, Role) VALUES ('%s', '%s', %d)", user.getUserID(), user.getPwd(), user.getRole());
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getUser(Statement stmt, User user) throws SQLException {
        String sql = "SELECT * FROM user WHERE UserID = '" + user.getUserID() + "' AND Password = '" + user.getPwd() + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

}
