package cutit.cutit.logic.controller.deletebookedappointments;

import cutit.cutit.logic.bean.DeleteAppointmentBean;
import cutit.cutit.logic.factory.JavaFXNodeFactory;
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
    private final Double titleFontSize = 30.0;
    private final Double normalLabelFontSize = 14.0;

    @FXML
    private VBox vbInScrollHApp;

    @FXML
    public void initialize(){
        deleteAppointmentBean = new DeleteAppointmentBean();
        deleteBookedAppointmentController = new DeleteBookedAppointmentController();
        vbInScrollHApp.setSpacing(15);
        showAppointments();
        System.out.println("CONTROLLER GRAFICO HAIRDRESSERDELETEBOOKEDAPPOINTMENTSVIEWCONTROLLER");
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
        deleteBookedAppointmentController.deleteAppointment(this.deleteAppointmentBean);
        showAppointments();
    }

    public void fillView(DeleteAppointmentBean bean){
        deleteAppointmentBean = bean;
        System.out.println("Filling View from DeleteAppointmentBean data passedBY TopBarHairdresserViewController");
        //quì riempirò i campi delle TextFile/TextArea/Label dell'fxml grazie ai getter della bean che mi è stata passata in ingresso
    }

}
