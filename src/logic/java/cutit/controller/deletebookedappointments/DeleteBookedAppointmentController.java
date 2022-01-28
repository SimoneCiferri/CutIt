package cutit.controller.deletebookedappointments;

import cutit.bean.AppointmentBeanInterface;
import cutit.bean.DeleteAppointmentBeanInterface;
import cutit.bean.AppointmentBean;
import cutit.database.dao.AppointmentDAO;
import cutit.database.dao.ShopDAO;
import cutit.exception.DBConnectionException;
import cutit.exception.RecordNotFoundException;
import cutit.exception.WrongInputDataException;
import cutit.log.LogWriter;
import cutit.model.Appointment;
import cutit.model.Shop;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DeleteBookedAppointmentController {

    public void deleteAppointment(DeleteAppointmentBeanInterface deleteAppointmentBean) throws DBConnectionException, SQLException, WrongInputDataException {
        try {
            LocalDate appointmentDay= deleteAppointmentBean.getStartTime().toLocalDate();
            if(isMoreThanTwoDaysAway(appointmentDay)){
                AppointmentDAO.deleteAppointment(deleteAppointmentBean.getStartTime().toString(), deleteAppointmentBean.getShopName());
            } else{
                throw new WrongInputDataException("Impossible to delete appointment.\nSelected appointment day is less than 2 days away.");
            }
        } catch (DBConnectionException | SQLException e) {
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    private boolean isMoreThanTwoDaysAway(LocalDate appointmentDay) {
        LocalDate day = appointmentDay;
        //tolgo 2 giorni alla data di appuntamento
        day = day.minusDays(2);
        //verifico che sia dopo la data odierna
        return day.isAfter(LocalDate.now());
    }

    public void getAllShopAppointments(DeleteAppointmentBeanInterface deleteAppointmentBean) throws DBConnectionException, SQLException, IOException, RecordNotFoundException {
        try {
            Shop shop = ShopDAO.getShopFromName(deleteAppointmentBean.getShopName());
            List<Appointment> allAppointments = shop.getAllAppointments();
            deleteAppointmentBean.setAllBookedAppointments(appointmentBeanListFromAppList(allAppointments));
        } catch (DBConnectionException | SQLException | IOException e) {
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    private List<AppointmentBeanInterface> appointmentBeanListFromAppList(List<Appointment> allAppointments) {
        List<AppointmentBeanInterface> appList = new ArrayList<>();
        if(!allAppointments.isEmpty()){
            for (Appointment allAppointment : allAppointments) {
                AppointmentBeanInterface bean = new AppointmentBean();
                bean.setStartTime(allAppointment.getStartTime());
                bean.setCustomer(allAppointment.getCustomer().getUserID());
                bean.setServiceName(allAppointment.getService().getServiceName());
                appList.add(bean);
            }
        }
        return appList;
    }
}
