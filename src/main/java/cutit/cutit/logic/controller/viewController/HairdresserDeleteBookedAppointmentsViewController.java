package cutit.cutit.logic.controller.viewController;

import cutit.cutit.logic.bean.DeleteAppointmentBean;
import cutit.cutit.logic.bean.ManagePromotionBean;
import cutit.cutit.logic.bean.ManageServiceBean;
import cutit.cutit.logic.controller.applController.DeleteBookedAppointmentController;
import cutit.cutit.logic.controller.applController.ManageServicesController;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class HairdresserDeleteBookedAppointmentsViewController {

    private DeleteAppointmentBean deleteAppointmentBean;
    private DeleteBookedAppointmentController deleteBookedAppointmentController;
    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";

    @FXML
    private VBox vbInScrollHApp;

    @FXML
    public void initialize(){
        deleteAppointmentBean = new DeleteAppointmentBean();
        deleteBookedAppointmentController = new DeleteBookedAppointmentController();
        vbInScrollHApp.setSpacing(15);
        showAppointments();
    }

    private void showAppointments() {
        vbInScrollHApp.getChildren().clear();
        for(Integer i=0; i<6; i++){
            Label l = new Label("Appointment"+i.toString());
            l.setPrefSize(895,130);
            l.setMinSize(895,130);
            l.setMaxSize(895,130);
            l.setStyle(labelStyle);
            l.setPadding(new Insets(0,0,10,20));
            l.setOnMouseClicked((MouseEvent) -> {
                deleteForm();
            });
            vbInScrollHApp.getChildren().add(l);
        }
    }

    private void deleteForm() {
       vbInScrollHApp.getChildren().clear();
        Label name = new Label("yyyy-MM-dd");
        name.setTextFill(Color.WHITE);
        Label clientName = new Label("Client1");
        clientName.setTextFill(Color.WHITE);
        Label serviceName = new Label("service");
        serviceName.setTextFill(Color.WHITE);
        Label appNotes = new Label("Notes....");
        appNotes.setTextFill(Color.WHITE);
        HBox buttonsHB = new HBox();
        buttonsHB.setMaxSize(600,55);
        buttonsHB.setMinSize(600, 55);
        HBox backButtonHB = new HBox();
        backButtonHB.setMaxSize(300,55);
        backButtonHB.setMinSize(300, 55);
        backButtonHB.setAlignment(Pos.CENTER_LEFT);
        Button back = new Button("Back");
        back.setPrefHeight(55);
        back.setOnMouseClicked((MouseEvent) -> showAppointments());
        backButtonHB.getChildren().add(back);
        HBox addButtonHB = new HBox();
        addButtonHB.setMaxSize(300,55);
        addButtonHB.setMinSize(300, 55);
        addButtonHB.setAlignment(Pos.CENTER_RIGHT);
        Button add = new Button("Delete");
        add.setPrefHeight(55);
        add.setOnMouseClicked((MouseEvent) -> deleteAppointment());
        addButtonHB.getChildren().add(add);
        buttonsHB.getChildren().addAll(backButtonHB, addButtonHB);
        vbInScrollHApp.getChildren().addAll(name, clientName, appNotes, buttonsHB);
    }

    private void deleteAppointment(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        deleteBookedAppointmentController.deleteAppointment(this.deleteAppointmentBean);
        showAppointments();
    }

    public void fillView(DeleteAppointmentBean bean){
        deleteAppointmentBean = bean;
        System.out.println("Filling View from DeleteAppointmentBean data passedBY TopBarHairdresserViewController");
        //quì riempirò i campi delle TextFile/TextArea/Label dell'fxml grazie ai getter della bean che mi è stata passata in ingresso
    }

}
