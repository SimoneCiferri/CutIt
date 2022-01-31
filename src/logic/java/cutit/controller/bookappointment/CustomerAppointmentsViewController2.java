package cutit.controller.bookappointment;

import cutit.bean.interfaces.AppointmentBeanInterface;
import cutit.bean.interfaces.CustomerBeanInterface;
import cutit.exception.DBConnectionException;
import cutit.utils.ExceptionText;
import cutit.exception.RecordNotFoundException;
import cutit.factory.AlertFactory;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class CustomerAppointmentsViewController2 {

    private CustomerBeanInterface customerBeanSecondUI;
    private BookAppointmentController bookAppointmentController;
    private static final String LABEL_STYLES = "-fx-border-color: grey; -fx-border-radius: 5;";
    private static final Double TITLE_FONT_SIZE = 30.0;
    private static final Double NORMAL_LABEL_FONT_SIZE = 14.0;

    @FXML
    private VBox vbAppointmentsInScroll;

    @FXML
    private VBox vbAppointmentInfo;

    @FXML
    public void initialize(){
        bookAppointmentController = new BookAppointmentController();
    }

    private void showMyAppointments() {
        try {
            vbAppointmentsInScroll.getChildren().clear();
            bookAppointmentController.getAppointments(customerBeanSecondUI);
            List<AppointmentBeanInterface> allAppointments = customerBeanSecondUI.getAllBookedAppointments();
            for(int i = 0; i< allAppointments.size(); i++){
                int appN = i;
                String appointment = allAppointments.get(appN).getShopName() + ", " + allAppointments.get(appN).getStartTime().toLocalDate() + " at " + allAppointments.get(appN).getStartTime().toLocalTime();
                Label l = JavaFXNodeFactory.getInstance().createCardLabel2(appointment, LABEL_STYLES);
                l.setOnMouseClicked(mouseEvent -> showAppointmentInfo(allAppointments.get(appN).getStartTime(), allAppointments.get(appN).getEndTime(), allAppointments.get(appN).getServiceName(), allAppointments.get(appN).getShopName()));
                vbAppointmentsInScroll.getChildren().add(l);
            }
        } catch (RecordNotFoundException rnfException) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), rnfException.getMessage());
            alert.showAndWait();
        } catch (DBConnectionException dbe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sqlEx){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        }
    }

    private void showAppointmentInfo(LocalDateTime startTime, LocalDateTime endTime, String serviceName, String shopName) {
        vbAppointmentInfo.getChildren().clear();
        Label shop = JavaFXNodeFactory.getInstance().createLabel2(shopName, TITLE_FONT_SIZE);
        String appointmentDay = startTime.toLocalDate().toString();
        Label date = JavaFXNodeFactory.getInstance().createLabel2(appointmentDay, NORMAL_LABEL_FONT_SIZE);
        String timeSlot = startTime.toLocalTime() + " - " + endTime.toLocalTime();
        Label time = JavaFXNodeFactory.getInstance().createLabel2(timeSlot, NORMAL_LABEL_FONT_SIZE);
        Label service = JavaFXNodeFactory.getInstance().createLabel2(serviceName, NORMAL_LABEL_FONT_SIZE);
        vbAppointmentInfo.getChildren().addAll(shop, date, time, service);
    }


    public void fillView(CustomerBeanInterface customerBeanSecondUI){
        this.customerBeanSecondUI = customerBeanSecondUI;
        showMyAppointments();
    }
}
