package cutit.controller.deletebookedappointments;

import cutit.bean.AppointmentBean;
import cutit.bean.DeleteAppointmentBean;
import cutit.bean.ShopBeanInterface;
import cutit.bean.firstui.DeleteAppointmentBeanFirstUI;
import cutit.exception.WrongInputDataException;
import cutit.factory.AlertFactory;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;

public class HairdresserDeleteBookedAppointmentsViewController {

    private ShopBeanInterface shopBean;
    private DeleteAppointmentBean deleteAppointmentBeanFirstUI;
    private DeleteBookedAppointmentController deleteBookedAppointmentController;
    private static final String LABEL_STYLE = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private static final Double TITLE_FONT_SIZE = 30.0;
    private static final Double NORMAL_LABEL_FONT_SIZE = 14.0;

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
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (WrongInputDataException wde) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.INFORMATION, "Information", wde.getMessage());
            alert.showAndWait();
        }catch (Exception e) {
            e.printStackTrace();
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
