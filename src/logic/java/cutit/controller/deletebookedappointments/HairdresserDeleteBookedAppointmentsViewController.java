package cutit.controller.deletebookedappointments;

import cutit.bean.interfaces.AppointmentBeanInterface;
import cutit.bean.interfaces.DeleteAppointmentBeanInterface;
import cutit.bean.interfaces.ShopBeanInterface;
import cutit.bean.DeleteAppointmentBean;
import cutit.exception.DBConnectionException;
import cutit.utils.ExceptionText;
import cutit.exception.RecordNotFoundException;
import cutit.exception.WrongInputDataException;
import cutit.factory.AlertFactory;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class HairdresserDeleteBookedAppointmentsViewController {

    private ShopBeanInterface shopBean;
    private DeleteAppointmentBeanInterface deleteAppointmentBeanFirstUI;
    private DeleteBookedAppointmentController deleteBookedAppointmentController;
    private static final String DELETED_APPOINTMENT_MESSAGE = "Appointment successfully deleted!";
    private static final String LABEL_STYLE = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private static final Double TITLE_FONT_SIZE = 30.0;
    private static final Double NORMAL_LABEL_FONT_SIZE = 14.0;

    @FXML
    private VBox vbInScrollHApp;

    @FXML
    public void initialize(){
        deleteAppointmentBeanFirstUI = new DeleteAppointmentBean();
        deleteBookedAppointmentController = new DeleteBookedAppointmentController();
        vbInScrollHApp.setSpacing(15);
    }

    private void showAppointments() {
        vbInScrollHApp.getChildren().clear();
        deleteAppointmentBeanFirstUI.setShopName(shopBean.getShopName());
        try {
            deleteBookedAppointmentController.getAllShopAppointments(deleteAppointmentBeanFirstUI);
            for(int i = 0; i< deleteAppointmentBeanFirstUI.getAllBookedAppointments().size(); i++){
                String appointmentTitle = deleteAppointmentBeanFirstUI.getAllBookedAppointments().get(i).getStartTime().toLocalDate().toString() + " at " + deleteAppointmentBeanFirstUI.getAllBookedAppointments().get(i).getStartTime().toLocalTime().toString();
                Label card = JavaFXNodeFactory.getInstance().createCardLabel(appointmentTitle, LABEL_STYLE);
                int n = i;
                card.setOnMouseClicked(mouseEvent -> deleteForm(deleteAppointmentBeanFirstUI.getAllBookedAppointments().get(n)));
                vbInScrollHApp.getChildren().add(card);
            }
        } catch (RecordNotFoundException e) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), e.getMessage());
            alert.showAndWait();
        } catch(DBConnectionException dbe){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException se) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        } catch (IOException ioe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getIoErrorTitle(), ExceptionText.getIoErrorMessage());
            alert.showAndWait();
        }
    }

    private void deleteForm(AppointmentBeanInterface appointmentBeanFirstUI) {
       vbInScrollHApp.getChildren().clear();
       Label appDate = JavaFXNodeFactory.getInstance().createLabel(appointmentBeanFirstUI.getStartTime().toLocalDate().toString(), TITLE_FONT_SIZE);
       Label appTime = JavaFXNodeFactory.getInstance().createLabel(appointmentBeanFirstUI.getStartTime().toLocalTime().toString(), TITLE_FONT_SIZE);
       Label clientName = JavaFXNodeFactory.getInstance().createLabel(appointmentBeanFirstUI.getCustomer(), NORMAL_LABEL_FONT_SIZE);
       Label serviceName = JavaFXNodeFactory.getInstance().createLabel(appointmentBeanFirstUI.getServiceName(), NORMAL_LABEL_FONT_SIZE);
       Button back = JavaFXNodeFactory.getInstance().createButton("Back");
       back.setPrefHeight(55);
       back.setOnMouseClicked(mouseEvent -> showAppointments());
       Button delete = JavaFXNodeFactory.getInstance().createButton("Delete");
       delete.setPrefHeight(55);
       delete.setOnMouseClicked(mouseEvent -> deleteAppointment(appointmentBeanFirstUI.getStartTime(), appointmentBeanFirstUI.getCustomer()));
       HBox buttonsHB = JavaFXNodeFactory.getInstance().createBottomButtons(back, delete);
       vbInScrollHApp.getChildren().addAll(appDate, appTime, clientName, serviceName, buttonsHB);
    }

    private void deleteAppointment(LocalDateTime appointmentDateAndTime, String customer){
        deleteAppointmentBeanFirstUI.setStartTime(appointmentDateAndTime);
        deleteAppointmentBeanFirstUI.setShopName(shopBean.getShopName());
        try {
            deleteBookedAppointmentController.deleteAppointment(this.deleteAppointmentBeanFirstUI);
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.CONFIRMATION, ExceptionText.getConfirmationTitle(), DELETED_APPOINTMENT_MESSAGE, "Notifying customer " + customer);
            alert.showAndWait();
            notifyCustomer();
        } catch (DBConnectionException dbe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException se){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        }
        catch (WrongInputDataException e){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), e.getMessage());
            alert.showAndWait();
        }
        showAppointments();
    }

    private void notifyCustomer() {
        //piccione viaggiatore
    }

    public void fillView(ShopBeanInterface shopBean){
        this.shopBean = shopBean;
        showAppointments();
    }



}
