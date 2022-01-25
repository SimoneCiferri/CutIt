package cutit.controller.bookappointment;

import cutit.bean.CustomerBean;
import cutit.decorator.ViewLayout1;
import cutit.decorator.concrete_decorator.CustomerAppointmentInfoView1;
import cutit.facade.Facade;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomerAppointmentsViewController {

    private static final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private CustomerBean customerBeanFirstUI;
    private BookAppointmentController bookAppointmentController;

    @FXML
    private VBox vbInScrollCA;

    public boolean initialize() throws IOException {
        bookAppointmentController = new BookAppointmentController();
        vbInScrollCA.setSpacing(15);
        return true;
    }

    private void showClientApp() {
        try {
            vbInScrollCA.getChildren().clear();
            bookAppointmentController.getAppointments(customerBeanFirstUI);
            for(int i = 0; i< customerBeanFirstUI.getAllBookedAppointments().size(); i++){
                int appN = i;
                String appointment = customerBeanFirstUI.getAllBookedAppointments().get(appN).getShopName() + ", " + customerBeanFirstUI.getAllBookedAppointments().get(appN).getStartTime().toLocalDate() + " at " + customerBeanFirstUI.getAllBookedAppointments().get(appN).getStartTime().toLocalTime();
                Label l = JavaFXNodeFactory.getInstance().createCardLabel(appointment, labelStyle);
                l.setOnMouseClicked(mouseEvent -> showAppInfo(customerBeanFirstUI.getAllBookedAppointments().get(appN).getStartTime(), customerBeanFirstUI.getAllBookedAppointments().get(appN).getEndTime(), customerBeanFirstUI.getAllBookedAppointments().get(appN).getServiceName(), customerBeanFirstUI.getAllBookedAppointments().get(appN).getShopName()));
                vbInScrollCA.getChildren().add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAppInfo(LocalDateTime appStartTime, LocalDateTime appEndTime, String appService, String appShop){
        Facade.getInstance().decorateView(ViewLayout1.CUSTOMERAPPINFO);
        CustomerAppointmentInfoView1 view = (CustomerAppointmentInfoView1) Facade.getInstance().getViewMap().get(ViewLayout1.CUSTOMERAPPINFO);
        CustomerAppointmentInfoViewController viewController = (CustomerAppointmentInfoViewController) view.getLoadedViewController1(ViewLayout1.CUSTOMERAPPINFO);
        viewController.fillView(appStartTime, appEndTime, appService, appShop);
    }

    public void fillView(CustomerBean customerBeanFirstUI){
        this.customerBeanFirstUI = customerBeanFirstUI;
        showClientApp();
    }

}
