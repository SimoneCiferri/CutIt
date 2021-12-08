package cutit.cutit.logic.controller.viewController;

import cutit.cutit.logic.bean.ManagePromotionBean;
import cutit.cutit.logic.bean.ManageServiceBean;
import cutit.cutit.logic.controller.applController.ManagePromotionController;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class HairdresserManagePromotionsViewController {

    private ManagePromotionBean managePromotionBean;
    private ManagePromotionController managePromotionController;
    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";

    @FXML
    private VBox vbInScrollHProm;

    @FXML
    public void initialize(){
        managePromotionBean = new ManagePromotionBean();
        managePromotionController = new ManagePromotionController();
        vbInScrollHProm.setSpacing(15);
        showHairProm();
        System.out.println("CONTROLLER GRAFICO HAIRDRESSERMANAGEPROMOTIONSVIEWCONTROLLER");
    }

    private void showHairProm() {
        vbInScrollHProm.getChildren().clear();
        Button add = new Button("Add Promotion");
        add.setOnMouseClicked((MouseEvent) -> {
            addForm();
        });
        vbInScrollHProm.getChildren().add(add);
        for(Integer i=0; i<4; i++) {
            Label l = new Label("Promotion"+i.toString());
            l.setPrefSize(895, 130);
            l.setMinSize(895, 130);
            l.setMaxSize(895, 130);
            l.setStyle(labelStyle);
            l.setPadding(new Insets(0, 0, 10, 20));
            l.setOnMouseClicked((MouseEvent) -> {
                deleteForm();
            });
            vbInScrollHProm.getChildren().add(l);
        }
    }

    private void addForm() {
        vbInScrollHProm.getChildren().clear();
        Label title = new Label("Add Promotion");
        HBox form = new HBox();
        form.setMaxSize(400,120);
        form.setMinSize(400,120);
        VBox leftVBox = new VBox();
        leftVBox.setMaxSize(200,120);
        leftVBox.setMinSize(200,120);
        leftVBox.setAlignment(Pos.TOP_RIGHT);
        leftVBox.setSpacing(25);
        leftVBox.setPadding(new Insets(0, 10, 0,0));
        Label name = new Label("Name:");
        name.setTextFill(Color.WHITE);
        Label price = new Label("Service:");
        price.setTextFill(Color.WHITE);
        Label descriptor = new Label("%off");
        descriptor.setTextFill(Color.WHITE);
        leftVBox.getChildren().addAll(name, price, descriptor);
        VBox rightVBox = new VBox();
        rightVBox.setMaxSize(200,120);
        rightVBox.setMinSize(200,120);
        rightVBox.setAlignment(Pos.TOP_LEFT);
        rightVBox.setSpacing(15);
        TextField promName = new TextField();
        promName.setPromptText("Promotion's Name");
        promName.setMaxSize(180, 25);
        ChoiceBox promService = new ChoiceBox();
        promService.setMaxSize(180,25);
        TextArea promValue = new TextArea();
        promValue.setPromptText("5");
        promValue.setMaxSize(60,25);
        rightVBox.getChildren().addAll(promName, promService, promValue);
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
        back.setOnMouseClicked((MouseEvent) -> showHairProm());
        backButtonHB.getChildren().add(back);
        HBox addButtonHB = new HBox();
        addButtonHB.setMaxSize(300,55);
        addButtonHB.setMinSize(300, 55);
        addButtonHB.setAlignment(Pos.CENTER_RIGHT);
        Button add = new Button("Add");
        add.setPrefHeight(55);
        add.setOnMouseClicked((MouseEvent) -> addPromotion());
        addButtonHB.getChildren().add(add);
        buttonsHB.getChildren().addAll(backButtonHB, addButtonHB);
       vbInScrollHProm.getChildren().addAll(title, form, buttonsHB);
    }

    private void addPromotion(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        managePromotionController.addPromotion(this.managePromotionBean);
        showHairProm();
    }

    private void deleteForm() {
       vbInScrollHProm.getChildren().clear();
        Label name = new Label("Promotion's Name");
        name.setTextFill(Color.WHITE);
        Label promValue = new Label("X %off");
        promValue.setTextFill(Color.WHITE);
        HBox form = new HBox();
        form.setMaxSize(300, 100);
        form.setMinSize(300, 100);
        VBox leftVBox = new VBox();
        leftVBox.setMaxSize(150,100);
        leftVBox.setMinSize(150,100);
        leftVBox.setAlignment(Pos.TOP_RIGHT);
        leftVBox.setSpacing(15);
        leftVBox.setPadding(new Insets(0, 10, 0,0));
        Label validFrom = new Label("From:");
        validFrom.setTextFill(Color.WHITE);
        Label validTo = new Label("To:");
        validTo.setTextFill(Color.WHITE);
        leftVBox.getChildren().addAll(validFrom, validTo);
        VBox rightVBox = new VBox();
        rightVBox.setMinSize(150,100);
        rightVBox.setMaxSize(150,100);
        rightVBox.setAlignment(Pos.TOP_LEFT);
        rightVBox.setSpacing(15);
        Label validFromValue = new Label("yyyy-MM-dd");
        validFromValue.setTextFill(Color.WHITE);
        Label validToValue = new Label("yyyy-MM-dd");
        validToValue.setTextFill(Color.WHITE);
        rightVBox.getChildren().addAll(validFromValue, validToValue);
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
        back.setOnMouseClicked((MouseEvent) -> showHairProm());
        backButtonHB.getChildren().add(back);
        HBox addButtonHB = new HBox();
        addButtonHB.setMaxSize(300,55);
        addButtonHB.setMinSize(300, 55);
        addButtonHB.setAlignment(Pos.CENTER_RIGHT);
        Button add = new Button("Delete");
        add.setPrefHeight(55);
        add.setOnMouseClicked((MouseEvent) -> removePromotion());
        addButtonHB.getChildren().add(add);
        buttonsHB.getChildren().addAll(backButtonHB, addButtonHB);
        vbInScrollHProm.getChildren().addAll(name, promValue, form, buttonsHB);
    }

    private void removePromotion(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        managePromotionController.removePromotion(this.managePromotionBean);
        showHairProm();
    }

    public void fillView(ManagePromotionBean bean){
        managePromotionBean = bean;
        System.out.println("Filling View from ManagePromotionBean data passedBY TopBarHairdresserViewController");
        //quì riempirò i campi delle TextFile/TextArea/Label dell'fxml grazie ai getter della bean che mi è stata passata in ingresso
    }

}
