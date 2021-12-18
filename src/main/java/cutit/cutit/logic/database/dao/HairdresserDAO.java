package cutit.cutit.logic.database.dao;

import cutit.cutit.logic.database.DBConnection;
import cutit.cutit.logic.database.query.HairdresserQueries;
import cutit.cutit.logic.model.Hairdresser;
import cutit.cutit.logic.model.User;

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
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        HairdresserQueries.insertHairdresser(stm, hairdresser.getpIVA(), hairdresser.getUserID(), hairdresser.getName(), hairdresser.getSurname());
        if(stm != null){
            stm.close();
        }
        //DBConnection.getInstance().closeConnection();
    }

    public Hairdresser getHairdresser(User user) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = HairdresserQueries.getHairdresser(stm, user.getUserID());
        if(!rs.first()){
            Exception e = new Exception("No user Found matching with name: "+ user.getUserID());
            throw e;
        }else{
            String hEmail = rs.getString(1);
            String name = rs.getString(2);
            String surname = rs.getString(3);
            String piva = rs.getString(4);
            Hairdresser hairdresser = new Hairdresser(hEmail, user.getPwd(), user.getRole(), name, surname, piva);
            if(stm != null){
                stm.close();
            }
            //DBConnection.getInstance().closeConnection();
            return hairdresser;
        }
    }

}
