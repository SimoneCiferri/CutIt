package cutit.cutit.logic.controller.viewController;

import cutit.cutit.logic.bean.ManageServiceBean;
import cutit.cutit.logic.controller.applController.ManageServicesController;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class HairdresserManageServicesViewController {

    private ManageServiceBean serviceBean;
    private ManageServicesController manageServicesController;
    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";

    @FXML
    private VBox vbInScrollHS;

    @FXML
    public void initialize(){
        serviceBean = new ManageServiceBean();
        manageServicesController = new ManageServicesController();
        vbInScrollHS.setSpacing(15);
        showHairServ();
    }

    private void showHairServ() {
        vbInScrollHS.getChildren().clear();
        Button add = new Button("Add Service");
        add.setOnMouseClicked((MouseEvent) -> {
            addForm();
        });
        vbInScrollHS.getChildren().add(add);
        //prendo i dati dalla bean e compongo le label (adesso sono composte a caso)
        for(Integer i=0; i<4; i++) {
            Label l = new Label("Service"+i.toString());
            l.setPrefSize(895, 130);
            l.setMinSize(895, 130);
            l.setMaxSize(895, 130);
            l.setStyle(labelStyle);
            l.setPadding(new Insets(0, 0, 10, 20));
            l.setOnMouseClicked((MouseEvent) -> {
                deleteForm();
            });
            vbInScrollHS.getChildren().add(l);
        }
    }

    private void addForm() {
        vbInScrollHS.getChildren().clear();
        Label title = new Label("Add Service");
        HBox form = new HBox();
        form.setMaxSize(500,250);
        form.setMinSize(500, 250);
        VBox leftVBox = new VBox();
        leftVBox.setMaxSize(250, 250);
        leftVBox.setMinSize(250, 250);
        leftVBox.setAlignment(Pos.TOP_RIGHT);
        leftVBox.setSpacing(25);
        leftVBox.setPadding(new Insets(0, 10, 0,0));
        Label name = new Label("Name:");
        name.setTextFill(Color.WHITE);
        Label price = new Label("Price:");
        price.setTextFill(Color.WHITE);
        Label descriptor = new Label("Description");
        descriptor.setTextFill(Color.WHITE);
        leftVBox.getChildren().addAll(name, price, descriptor);
        VBox rightVBox = new VBox();
        rightVBox.setMinSize(250,250);
        rightVBox.setMaxSize(250, 250);
        rightVBox.setAlignment(Pos.TOP_LEFT);
        rightVBox.setSpacing(15);
        TextField serviceName = new TextField();
        serviceName.setPromptText("Service Name");
        serviceName.setMaxSize(180, 25);
        TextField servicePrice = new TextField();
        servicePrice.setPromptText("Service Price");
        servicePrice.setMaxSize(180, 25);
        TextArea serviceDescription = new TextArea();
        serviceDescription.setPromptText("Description");
        serviceDescription.setMaxSize(235, 155);
        rightVBox.getChildren().addAll(serviceName, servicePrice, serviceDescription);
        form.getChildren().addAll(leftVBox, rightVBox);
        HBox buttonsHB = new HBox();
        buttonsHB.setMaxSize(600,55);
        buttonsHB.setMinSize(600, 55);
        HBox backButtonHB = new HBox();
        backButtonHB.setMaxSize(300,55);
        backButtonHB.setMinSize(300, 55);
        backButtonHB.setAlignment(Pos.CENTER_LEFT);
        Button back = new Button("Back");
        back.setPrefHeight(55);
        back.setOnMouseClicked((MouseEvent) -> showHairServ());
        backButtonHB.getChildren().add(back);
        HBox addButtonHB = new HBox();
        addButtonHB.setMaxSize(300,55);
        addButtonHB.setMinSize(300, 55);
        addButtonHB.setAlignment(Pos.CENTER_RIGHT);
        Button add = new Button("Add");
        add.setPrefHeight(55);
        add.setOnMouseClicked((MouseEvent) -> addService());
        addButtonHB.getChildren().add(add);
        buttonsHB.getChildren().addAll(backButtonHB, addButtonHB);
        vbInScrollHS.getChildren().addAll(title, form, buttonsHB);
    }

    private void addService(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        manageServicesController.addService(this.serviceBean);
        showHairServ();
    }

    private void deleteForm() {
        vbInScrollHS.getChildren().clear();
        Label name = new Label("Service Name");
        name.setTextFill(Color.WHITE);
        Label price = new Label("Price $$");
        price.setTextFill(Color.WHITE);
        Label descriptor = new Label("Description");
        descriptor.setTextFill(Color.WHITE);
        HBox buttonsHB = new HBox();
        buttonsHB.setMaxSize(600,55);
        buttonsHB.setMinSize(600, 55);
        HBox backButtonHB = new HBox();
        backButtonHB.setMaxSize(300,55);
        backButtonHB.setMinSize(300, 55);
        backButtonHB.setAlignment(Pos.CENTER_LEFT);
        Button back = new Button("Back");
        back.setPrefHeight(55);
        back.setOnMouseClicked((MouseEvent) -> showHairServ());
        backButtonHB.getChildren().add(back);
        HBox addButtonHB = new HBox();
        addButtonHB.setMaxSize(300,55);
        addButtonHB.setMinSize(300, 55);
        addButtonHB.setAlignment(Pos.CENTER_RIGHT);
        Button add = new Button("Delete");
        add.setPrefHeight(55);
        add.setOnMouseClicked((MouseEvent) -> deleteService());
        addButtonHB.getChildren().add(add);
        buttonsHB.getChildren().addAll(backButtonHB, addButtonHB);
        vbInScrollHS.getChildren().addAll(name, price, descriptor, buttonsHB);
    }

    private void deleteService(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        manageServicesController.deleteService(this.serviceBean);
        showHairServ();
    }


    public void fillView(ManageServiceBean bean){
        serviceBean = bean;
        System.out.println("Filling View from ManageServiceBean data passedBY TopBarHairdresserViewController");
        //quì riempirò i campi delle TextFile/TextArea/Label dell'fxml grazie ai getter della bean che mi è stata passata in ingresso
    }

}
