package cutit.cutit.logic.controller.viewController;

import cutit.cutit.logic.bean.DeleteAppointmentBean;
import cutit.cutit.logic.bean.ManageServiceBean;
import cutit.cutit.logic.controller.applController.DeleteBookedAppointmentController;
import cutit.cutit.logic.controller.applController.ManageServicesController;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;

public class HairdresserDeleteBookedAppointmentsViewController {

    private DeleteAppointmentBean deleteAppointmentBean;
    private DeleteBookedAppointmentController deleteBookedAppointmentController;

    @FXML
    public void initialize(){
        deleteAppointmentBean = new DeleteAppointmentBean();
        deleteBookedAppointmentController = new DeleteBookedAppointmentController();
    }

    @FXML
    public void goBackToAppH(){
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERAPPOINTMENTS);
    }

    @FXML
    public void deleteAppointment(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        deleteBookedAppointmentController.deleteAppointment(this.deleteAppointmentBean);
    }



}
