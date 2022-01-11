package cutit.controller.bookappointment;

import cutit.bean.CustomerBean;
import cutit.decorator.ViewLayout;
import cutit.decorator.concreteDecorator.CustomerAppointmentInfoView;
import cutit.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CustomerAppointmentsViewController {

    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private CustomerBean customerBeanFirstUI;
    private BookAppointmentController bookAppointmentController;

    @FXML
    private VBox vbInScrollCA;

    public boolean initialize() throws IOException {
        bookAppointmentController = new BookAppointmentController();
        vbInScrollCA.setSpacing(15);
        System.out.println("CONTROLLER GRAFICO CUSTOMERAPPOINTMENTSVIEWCONTROLLER");
        return true;
    }

    /*private void showClientApp() {
        try {
            vbInScrollCA.getChildren().clear();
            bookAppointmentController.getAppointments(customerBeanFirstUI);
            for(int i = 0; i< customerBeanFirstUI.getAllBookedAppointment().size(); i++){
                Integer appN = i;
                String appointment = customerBeanFirstUI.getAllBookedAppointment().get(appN).getAppShopName() + ", " + customerBeanFirstUI.getAllBookedAppointment().get(appN).getAppStarTime();
                Label l = JavaFXNodeFactory.getInstance().createCardLabel(appointment, labelStyle);
                l.setOnMouseClicked((MouseEvent) -> showAppInfo(customerBeanFirstUI.getAllBookedAppointment().get(appN).getAppStarTime(), customerBeanFirstUI.getAllBookedAppointment().get(appN).getAppEndTime(), customerBeanFirstUI.getAllBookedAppointment().get(appN).getAppService(), customerBeanFirstUI.getAllBookedAppointment().get(appN).getAppShopName()));
                vbInScrollCA.getChildren().add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    private void showAppInfo(String appStartTime, String appEndTime, String appService, String appShop){
        Facade.getInstance().decorateView(ViewLayout.CUSTOMERAPPINFO);
        CustomerAppointmentInfoView view = (CustomerAppointmentInfoView) Facade.getInstance().getViewMap().get(ViewLayout.CUSTOMERAPPINFO);
        CustomerAppointmentInfoViewController viewController = (CustomerAppointmentInfoViewController) view.getLoadedViewController(ViewLayout.CUSTOMERAPPINFO);
        viewController.fillView(appStartTime, appEndTime, appService, appShop);
    }

    public void fillView(CustomerBean customerBeanFirstUI){
        this.customerBeanFirstUI = customerBeanFirstUI;
        System.out.println("Filling View from ShopBean data passedBY TopBarCustomerViewController");
        //quì riempirò i campi delle TextFile/TextArea/Label dell'fxml grazie ai getter della bean che mi è stata passata in ingresso
        //showClientApp();
    }

}
