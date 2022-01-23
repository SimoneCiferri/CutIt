package cutit.controller.deletebookedappointments;

import cutit.bean.AppointmentBean;
import cutit.bean.DeleteAppointmentBean;
import cutit.bean.ShopBean;
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

    private ShopBean shopBean;
    private DeleteAppointmentBean deleteAppointmentBeanFirstUI;
    private DeleteBookedAppointmentController deleteBookedAppointmentController;
    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private final Double titleFontSize = 30.0;
    private final Double normalLabelFontSize = 14.0;

    @FXML
    private VBox vbInScrollHApp;

    @FXML
    public void initialize(){
        deleteAppointmentBeanFirstUI = new DeleteAppointmentBeanFirstUI();
        deleteBookedAppointmentController = new DeleteBookedAppointmentController();
        vbInScrollHApp.setSpacing(15);
        System.out.println("CONTROLLER GRAFICO HAIRDRESSERDELETEBOOKEDAPPOINTMENTSVIEWCONTROLLER");
    }

    private void showAppointments() {
        vbInScrollHApp.getChildren().clear();
        deleteAppointmentBeanFirstUI.setShopName(shopBean.getShopName());
        try {
            deleteBookedAppointmentController.getAllShopAppointments(deleteAppointmentBeanFirstUI);
            for(int i = 0; i< deleteAppointmentBeanFirstUI.getAllBookedAppointments().size(); i++){
                String appointmentTitle = deleteAppointmentBeanFirstUI.getAllBookedAppointments().get(i).getStartTime().toLocalDate().toString() + " at " + deleteAppointmentBeanFirstUI.getAllBookedAppointments().get(i).getStartTime().toLocalTime().toString();
                Label card = JavaFXNodeFactory.getInstance().createCardLabel(appointmentTitle, labelStyle);
                int n = i;
                card.setOnMouseClicked((MouseEvent) -> deleteForm(deleteAppointmentBeanFirstUI.getAllBookedAppointments().get(n)));
                vbInScrollHApp.getChildren().add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteForm(AppointmentBean appointmentBeanFirstUI) {
       vbInScrollHApp.getChildren().clear();
       Label appDate = JavaFXNodeFactory.getInstance().createLabel(appointmentBeanFirstUI.getStartTime().toLocalDate().toString(),titleFontSize);
       Label appTime = JavaFXNodeFactory.getInstance().createLabel(appointmentBeanFirstUI.getStartTime().toLocalTime().toString(),titleFontSize);
       Label clientName = JavaFXNodeFactory.getInstance().createLabel(appointmentBeanFirstUI.getCustomer(), normalLabelFontSize);
       Label serviceName = JavaFXNodeFactory.getInstance().createLabel(appointmentBeanFirstUI.getServiceName(), normalLabelFontSize);
       Button back = JavaFXNodeFactory.getInstance().createButton("Back");
       back.setPrefHeight(55);
       back.setOnMouseClicked((MouseEvent) -> showAppointments());
       Button delete = JavaFXNodeFactory.getInstance().createButton("Delete");
       delete.setPrefHeight(55);
       delete.setOnMouseClicked((MouseEvent) -> deleteAppointment(appointmentBeanFirstUI.getStartTime(), appointmentBeanFirstUI.getCustomer()));
       HBox buttonsHB = JavaFXNodeFactory.getInstance().createBottomButtons(back, delete);
       vbInScrollHApp.getChildren().addAll(appDate, appTime, clientName, serviceName, buttonsHB);
    }

    private void deleteAppointment(LocalDateTime appointmentDateAndTime, String customer){
        deleteAppointmentBeanFirstUI.setStartTime(appointmentDateAndTime);
        deleteAppointmentBeanFirstUI.setShopName(shopBean.getShopName());
        try {
            deleteBookedAppointmentController.deleteAppointment(this.deleteAppointmentBeanFirstUI);
            notifyCustomer(customer);
        } catch (WrongInputDataException wde) {
            Alert alert = AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, "Information", wde.getMessage());
            alert.showAndWait();
        }catch (Exception e) {
            e.printStackTrace();
        }
        showAppointments();
    }

    private void notifyCustomer(String customer) {
        //piccione viaggiatore
    }

    public void fillView(ShopBean shopBean){
        this.shopBean = shopBean;
        System.out.println("Filling View from DeleteAppointmentBean data passedBY TopBarHairdresserViewController");
        showAppointments();
    }



}
