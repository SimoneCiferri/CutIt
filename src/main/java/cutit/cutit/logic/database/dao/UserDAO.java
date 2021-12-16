package cutit.cutit.logic.database.dao;

import com.mysql.cj.exceptions.ConnectionIsClosedException;
import cutit.cutit.logic.database.DBConnection;
import cutit.cutit.logic.database.query.UserQueries;
import cutit.cutit.logic.model.Customer;
import cutit.cutit.logic.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO {

    public static void userLogin() throws Exception {
        Connection conn = conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        int rs = UserQueries.insertCustomer(stm ,new Customer(4, "pellegrini", "pippo", "123@gmail.com"));
        if(stm != null){
            stm.close();
        }
        DBConnection.getInstance().closeConnection();
    }

}
