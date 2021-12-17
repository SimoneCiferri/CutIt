package cutit.cutit.logic.database.dao;

import cutit.cutit.logic.database.DBConnection;
import cutit.cutit.logic.database.query.HairdresserQueries;
import cutit.cutit.logic.model.Customer;
import cutit.cutit.logic.model.Hairdresser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HairdresserDAO {

    private static HairdresserDAO instance = null;

    private HairdresserDAO(){}

    public static synchronized HairdresserDAO getInstance(){
        if(instance == null){
            instance = new HairdresserDAO();
        }
        return instance;
    }

    public void insertNewHairdresser(Hairdresser hairdresser) throws Exception {
        Connection conn = conn= DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        HairdresserQueries.insertHairdresser(stm, hairdresser);
        if(stm != null){
            stm.close();
        }
        //DBConnection.getInstance().closeConnection();
    }

}
