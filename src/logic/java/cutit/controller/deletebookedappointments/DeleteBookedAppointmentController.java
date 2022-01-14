package cutit.controller.deletebookedappointments;

import cutit.bean.DeleteAppointmentBean;
import cutit.database.dao.AppointmentDAO;
import cutit.database.dao.ShopDAO;
import cutit.log.LogWriter;
import cutit.model.Appointment;
import cutit.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class DeleteBookedAppointmentController {

    public Boolean deleteAppointment(DeleteAppointmentBean deleteAppointmentBean){
        //dovrò passare la bean, in modo che questa si possa registrare come osservatore del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("CONTROLLER APPLICATIVO -> Deleting Appointment (data from DeleteAppointmentBean passed by my viewController)");
        return true;
    }

    public void getAllShopAppointments(DeleteAppointmentBean deleteAppointmentBeanFirstUI) throws Exception {
        try {
            Shop shop = ShopDAO.getShopFromName(deleteAppointmentBeanFirstUI.getShopName());
            List<Appointment> allAppointments = shop.getAllAppointments();
            deleteAppointmentBeanFirstUI.setAllAppointments(stringListFromAppList(allAppointments));
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }

    }

    private List<String> stringListFromAppList(List<Appointment> allAppointments) {
        List<String> appList = new ArrayList<>();
        if(!allAppointments.isEmpty()){
            for(int i = 0; i<allAppointments.size(); i++){
                String p = allAppointments.get(i).getCustomer().getName() + " " + allAppointments.get(i).getCustomer().getSurname() + ", " + allAppointments.get(i).getStartTime().toLocalDate() + " at " + allAppointments.get(i).getStartTime().toLocalTime();
                appList.add(p);
            }
        }
        return appList;
    }
}
