package cutit.controller.deletebookedappointments;

import cutit.bean.AppointmentBean;
import cutit.bean.DeleteAppointmentBean;
import cutit.bean.AppointmentBeanUQ;
import cutit.database.dao.AppointmentDAO;
import cutit.database.dao.ShopDAO;
import cutit.exception.WrongInputDataException;
import cutit.log.LogWriter;
import cutit.model.Appointment;
import cutit.model.Shop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DeleteBookedAppointmentController {

    public Boolean deleteAppointment(DeleteAppointmentBean deleteAppointmentBean) throws Exception {
        try {
            LocalDate appointmentDay= deleteAppointmentBean.getStartTime().toLocalDate();
            if(isMoreThanTwoDaysAway(appointmentDay)){
                AppointmentDAO.deleteAppointment(deleteAppointmentBean.getStartTime().toString(), deleteAppointmentBean.getShopName());
            } else{
                throw new WrongInputDataException("Impossible to delete appointment.\nSelected appointment day is less than 2 days away.");
            }
        } catch (WrongInputDataException wde) {
            throw wde;
        }catch (Exception e) {
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
        return true;
    }

    private boolean isMoreThanTwoDaysAway(LocalDate appointmentDay) {
        LocalDate day = appointmentDay;
        //tolgo 2 giorni alla data di appuntamento
        day = day.minusDays(2);
        //verifico che sia dopo la data odierna
        return day.isAfter(LocalDate.now());
    }

    public void getAllShopAppointments(DeleteAppointmentBean deleteAppointmentBean) throws Exception {
        try {
            Shop shop = ShopDAO.getShopFromName(deleteAppointmentBean.getShopName());
            List<Appointment> allAppointments = shop.getAllAppointments();
            deleteAppointmentBean.setAllBookedAppointments(appointmentBeanListFromAppList(allAppointments));
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }

    }

    private List<AppointmentBean> appointmentBeanListFromAppList(List<Appointment> allAppointments) {
        List<AppointmentBean> appList = new ArrayList<>();
        if(!allAppointments.isEmpty()){
            for(int i = 0; i<allAppointments.size(); i++){
                AppointmentBean bean = new AppointmentBeanUQ();
                bean.setStartTime(allAppointments.get(i).getStartTime());
                bean.setCustomer(allAppointments.get(i).getCustomer().getUserID());
                bean.setServiceName(allAppointments.get(i).getService().getServiceName());
                appList.add(bean);
            }
        }
        return appList;
    }
}
