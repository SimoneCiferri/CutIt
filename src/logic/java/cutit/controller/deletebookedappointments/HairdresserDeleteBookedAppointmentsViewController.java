package cutit.controller.deletebookedappointments;

import cutit.bean.AppointmentBean;
import cutit.bean.DeleteAppointmentBean;
import cutit.bean.ShopBeanInterface;
import cutit.bean.firstui.DeleteAppointmentBeanFirstUI;
import cutit.exception.DBConnectionException;
import cutit.exception.RecordNotFoundException;
import cutit.exception.WrongCredentialsException;
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
    private DeleteAppointmentBean deleteAppointmentBeanFirstUI;
    private DeleteBookedAppointmentController deleteBookedAppointmentController;
    private static final String LABEL_STYLE = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private static final Double TITLE_FONT_SIZE = 30.0;
    private static final Double NORMAL_LABEL_FONT_SIZE = 14.0;
    private static final String CONNECTION_ERROR_TITLE = "Connection error";
    private static final String WARNING_TITLE = "Warning";
    private static final String IO_ERROR_TITLE = "Error";
    private static final String CONNECTION_ERROR_MESSAGE = "Please check your internet connection. If problem persists try to restart the application.";
    private static final String SQL_ERROR_MESSAGE = "Please check your internet connection. If problem persists contact us at cutitapp@support.com.";
    private static final String IO_ERROR_MESSAGE = "Impossible to load some files. If problem persists try again later or contact us at cutitapp@support.com";

    @FXML
    private VBox vbInScrollHApp;

    @FXML
    public void initialize(){
        deleteAppointmentBeanFirstUI = new DeleteAppointmentBeanFirstUI();
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
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, WARNING_TITLE, e.getMessage());
            alert.showAndWait();
        } catch(DBConnectionException dbe){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, CONNECTION_ERROR_MESSAGE);
            alert.showAndWait();
        } catch (SQLException sqle) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, SQL_ERROR_MESSAGE);
            alert.showAndWait();
        } catch (IOException ioe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, IO_ERROR_TITLE, IO_ERROR_MESSAGE);
            alert.showAndWait();
        }
    }

    private void deleteForm(AppointmentBean appointmentBeanFirstUI) {
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
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Appointment successfully deleted!");
            alert.showAndWait();
            notifyCustomer(customer);
        } catch (DBConnectionException dbe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, CONNECTION_ERROR_MESSAGE);
            alert.showAndWait();
        } catch (SQLException sqle){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR,CONNECTION_ERROR_TITLE, SQL_ERROR_MESSAGE );
            alert.showAndWait();
        }
        catch (WrongInputDataException e){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, WARNING_TITLE, e.getMessage());
            alert.showAndWait();
        }
        showAppointments();
    }

    private void notifyCustomer(String customer) {
        //piccione viaggiatore
    }

    public void fillView(ShopBeanInterface shopBean){
        this.shopBean = shopBean;
        showAppointments();
    }



}
