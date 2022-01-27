package cutit.controller.deletebookedappointments;

import cutit.bean.AppointmentBean;
import cutit.bean.DeleteAppointmentBeanInterface;
import cutit.bean.ShopBeanInterface;
import cutit.bean.firstui.DeleteAppointmentBean;
import cutit.exception.DBConnectionException;
import cutit.exception.ExceptionText;
import cutit.exception.RecordNotFoundException;
import cutit.exception.WrongInputDataException;
import cutit.factory.AlertFactory;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class HairdresserDeleteBookedAppointmentsViewController2 {

    private ShopBeanInterface shopBean;
    private DeleteAppointmentBeanInterface deleteAppointmentBeanSecondUI;
    private DeleteBookedAppointmentController deleteBookedAppointmentController;
    private static final String LABEL_STYLE = "-fx-border-color: grey; -fx-border-radius: 5;";
    private static final Double TITLE_FONT_SIZE = 30.0;
    private static final Double NORMAL_LABEL_FONT_SIZE = 14.0;
    private static final String WARNING_TITLE = "Warning";
    private static final String DELETED_APPOINTMENT_MESSAGE = "Appointment successfully deleted!";

    @FXML
    private VBox vbAppointmentsInScroll;

    @FXML
    private VBox vbDeleteAppointments;

    @FXML
    public void initialize(){
        deleteAppointmentBeanSecondUI = new DeleteAppointmentBean();
        deleteBookedAppointmentController = new DeleteBookedAppointmentController();
    }

    public void fillView(ShopBeanInterface shopBean){
        this.shopBean = shopBean;
        showAllAppointments();
    }

    private void showAllAppointments() {
        vbAppointmentsInScroll.getChildren().clear();
        deleteAppointmentBeanSecondUI.setShopName(shopBean.getShopName());
        try {
            deleteBookedAppointmentController.getAllShopAppointments(deleteAppointmentBeanSecondUI);
            List<AppointmentBean> allAppointments = deleteAppointmentBeanSecondUI.getAllBookedAppointments();
            for(int i = 0; i< allAppointments.size(); i++){
                String appointmentTitle = allAppointments.get(i).getStartTime().toLocalDate().toString() + " at " + allAppointments.get(i).getStartTime().toLocalTime().toString();
                Label card = JavaFXNodeFactory.getInstance().createCardLabel2(appointmentTitle, LABEL_STYLE);
                int n = i;
                card.setOnMouseClicked(mouseEvent -> showDeleteForm(allAppointments.get(n)));
                vbAppointmentsInScroll.getChildren().add(card);
            }
        } catch (RecordNotFoundException e) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), e.getMessage());
            alert.showAndWait();
        } catch(DBConnectionException dbe){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sql) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        } catch (IOException ioe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getIoErrorTitle(), ExceptionText.getIoErrorMessage());
            alert.showAndWait();
        }
    }

    private void showDeleteForm(AppointmentBean appointmentBean) {
        vbDeleteAppointments.getChildren().clear();
        Label appDate = JavaFXNodeFactory.getInstance().createLabel2(appointmentBean.getStartTime().toLocalDate().toString(), TITLE_FONT_SIZE);
        Label appTime = JavaFXNodeFactory.getInstance().createLabel2(appointmentBean.getStartTime().toLocalTime().toString(), TITLE_FONT_SIZE);
        Label clientName = JavaFXNodeFactory.getInstance().createLabel2(appointmentBean.getCustomer(), NORMAL_LABEL_FONT_SIZE);
        Label serviceName = JavaFXNodeFactory.getInstance().createLabel2(appointmentBean.getServiceName(), NORMAL_LABEL_FONT_SIZE);
        Button deleteButton = JavaFXNodeFactory.getInstance().createButton("Delete");
        deleteButton.setOnMouseClicked(mouseEvent -> deleteAppointment(appointmentBean.getStartTime(), appointmentBean.getCustomer()));
        vbDeleteAppointments.getChildren().addAll(appDate, appTime, clientName, serviceName, deleteButton);
    }

    private void deleteAppointment(LocalDateTime appointmentDateAndTime, String customer){
        deleteAppointmentBeanSecondUI.setStartTime(appointmentDateAndTime);
        deleteAppointmentBeanSecondUI.setShopName(shopBean.getShopName());
        try {
            deleteBookedAppointmentController.deleteAppointment(deleteAppointmentBeanSecondUI);
            showAllAppointments();
            vbDeleteAppointments.getChildren().clear();
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.CONFIRMATION, ExceptionText.getConfirmationTitle(), DELETED_APPOINTMENT_MESSAGE, "Notifying customer " + customer);
            alert.showAndWait();
            notifyCustomer();
        } catch(DBConnectionException dbe){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sql) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        }
        catch (WrongInputDataException e){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, WARNING_TITLE, e.getMessage());
            alert.showAndWait();
        }
    }

    private void notifyCustomer() {
        //piccione viaggiatore
    }
}
