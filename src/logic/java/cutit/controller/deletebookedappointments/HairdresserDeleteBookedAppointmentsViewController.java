package cutit.controller.deletebookedappointments;

import cutit.bean.DeleteAppointmentBean;
import cutit.bean.ShopBean;
import cutit.bean.firstui.DeleteAppointmentBeanFirstUI;
import cutit.bean.firstui.ShopBeanUQ;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;

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
            for(int i = 0; i< deleteAppointmentBeanFirstUI.getAllAppointments().size(); i++){
                String appointmentTitle = deleteAppointmentBeanFirstUI.getAllAppointments().get(i);
                Label card = JavaFXNodeFactory.getInstance().createCardLabel(appointmentTitle, labelStyle);
                //int n = i;
                //card.setOnMouseClicked((MouseEvent) -> goShopInfo(shopListBeanFirstUI.getShopBeanList().get(n).getShopName()));
                vbInScrollHApp.getChildren().add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteForm() {
       vbInScrollHApp.getChildren().clear();
        Label appDate = JavaFXNodeFactory.getInstance().createLabel("dd-MM-yyyy",titleFontSize);
        Label clientName = JavaFXNodeFactory.getInstance().createLabel("Customer Name", normalLabelFontSize);
        Label serviceName = JavaFXNodeFactory.getInstance().createLabel("Service", normalLabelFontSize);
        Label appNotes = JavaFXNodeFactory.getInstance().createLabel("Notes..", normalLabelFontSize);
        Button back = JavaFXNodeFactory.getInstance().createButton("Back");
        back.setPrefHeight(55);
        back.setOnMouseClicked((MouseEvent) -> showAppointments());
        Button delete = JavaFXNodeFactory.getInstance().createButton("Delete");
        delete.setPrefHeight(55);
        delete.setOnMouseClicked((MouseEvent) -> deleteAppointment());
        HBox buttonsHB = JavaFXNodeFactory.getInstance().createBottomButtons(back, delete);
        vbInScrollHApp.getChildren().addAll(appDate, clientName, serviceName, appNotes, buttonsHB);
    }

    private void deleteAppointment(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        deleteBookedAppointmentController.deleteAppointment(this.deleteAppointmentBeanFirstUI);
        showAppointments();
    }

    public void fillView(ShopBean shopBean){
        this.shopBean = shopBean;
        System.out.println("Filling View from DeleteAppointmentBean data passedBY TopBarHairdresserViewController");
        showAppointments();
    }

}
