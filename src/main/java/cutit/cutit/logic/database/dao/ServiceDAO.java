package cutit.cutit.logic.database.dao;

import cutit.cutit.logic.database.DBConnection;
import cutit.cutit.logic.database.query.ServiceQueries;
import cutit.cutit.logic.database.query.UserQueries;
import cutit.cutit.logic.model.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceDAO {


    private static ServiceDAO instance = null;

    private ServiceDAO(){}

    public static synchronized ServiceDAO getInstance(){
        if(instance == null){
            instance = new ServiceDAO();
        }
        return instance;
    }

    public void insertService(Service service) throws Exception {
        Connection conn = conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ServiceQueries.insertService(stm, service);
        if(stm != null){
            stm.close();
        }
    }
}
