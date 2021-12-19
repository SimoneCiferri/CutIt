package cutit.cutit.logic.controller.managepromotions;

import cutit.cutit.logic.bean.HairdresserBean;
import cutit.cutit.logic.bean.ManagePromotionBean;
import cutit.cutit.logic.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;


public class HairdresserManagePromotionsViewController {

    private HairdresserBean hairdresserBean;
    private ManagePromotionBean managePromotionBean;
    private ManagePromotionController managePromotionController;
    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private final Double titleFontSize = 30.0;
    private final Double normalLabelFontSize = 14.0;

    @FXML
    private VBox vbInScrollHProm;

    @FXML
    public void initialize(){
        managePromotionBean = new ManagePromotionBean();
        managePromotionController = new ManagePromotionController();
        vbInScrollHProm.setSpacing(15);
        System.out.println("CONTROLLER GRAFICO HAIRDRESSERMANAGEPROMOTIONSVIEWCONTROLLER");
    }

    private void showHairProm() {
        try {
            this.managePromotionBean = managePromotionController.getAllPromotions(this.hairdresserBean);
            vbInScrollHProm.getChildren().clear();
            Button add = JavaFXNodeFactory.getInstance().createButton("Add Promotion");
            add.setOnMouseClicked((MouseEvent) -> addForm());
            vbInScrollHProm.getChildren().add(add);
            for(int i = 0; i<managePromotionBean.getPromotionsList().size(); i++) {
                String promotionName = managePromotionBean.getPromotionName(i);
                Integer promotionOffValue = managePromotionBean.getPromotionOffValue(i);
                Label l = JavaFXNodeFactory.getInstance().createCardLabel(promotionName, labelStyle);
                l.setOnMouseClicked((MouseEvent) -> deleteForm(promotionName, promotionOffValue));
                vbInScrollHProm.getChildren().add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addForm() {
        vbInScrollHProm.getChildren().clear();
        Label title = JavaFXNodeFactory.getInstance().createLabel("Add Promotion", titleFontSize);
        List<Label> leftLabelList = new ArrayList<>();
        Label name = JavaFXNodeFactory.getInstance().createLabel("Name:", normalLabelFontSize);
        leftLabelList.add(name);
        Label service = JavaFXNodeFactory.getInstance().createLabel("service:", normalLabelFontSize);
        leftLabelList.add(service);
        Label valueOFF = JavaFXNodeFactory.getInstance().createLabel("%off:", normalLabelFontSize);
        leftLabelList.add(valueOFF);
        List<Node> rightList = new ArrayList<>();
        TextField promName = new TextField();
        promName.setPromptText("Promotion's Name");
        promName.setMaxSize(180, 25);
        rightList.add(promName);
        ChoiceBox promService = new ChoiceBox();
        promService.setMaxSize(180,25);
        promService.getItems().add("Taglio: 15$");
        promService.getItems().add("Taglio = Shampo: 20$");
        promService.getItems().add("Barba: 10$");
        rightList.add(promService);
        TextArea promValue = new TextArea();
        promValue.setPromptText("5.50");
        promValue.setMaxSize(60,25);
        rightList.add(promValue);
        HBox form = JavaFXNodeFactory.getInstance().createLRForm(leftLabelList, rightList, true);
        Button back = JavaFXNodeFactory.getInstance().createButton("Back");
        back.setPrefHeight(55);
        back.setOnMouseClicked((MouseEvent) -> showHairProm());
        Button add = JavaFXNodeFactory.getInstance().createButton("Add");
        add.setPrefHeight(55);
        add.setOnMouseClicked((MouseEvent) -> addPromotion());
        HBox buttonsHB = JavaFXNodeFactory.getInstance().createBottomButtons(back, add);
       vbInScrollHProm.getChildren().addAll(title, form, buttonsHB);
    }

    private void addPromotion(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        managePromotionController.addPromotion(this.managePromotionBean);
        showHairProm();
    }

    private void deleteForm(String promName, Integer promOffValue) {
       vbInScrollHProm.getChildren().clear();
        Label name = JavaFXNodeFactory.getInstance().createLabel(promName, titleFontSize);
        Label promValue = JavaFXNodeFactory.getInstance().createLabel(promOffValue.toString(), titleFontSize);
        List<Label> leftLabelList = new ArrayList<>();
        Label validFrom = JavaFXNodeFactory.getInstance().createLabel("From:", normalLabelFontSize);
        leftLabelList.add(validFrom);
        Label validTo = JavaFXNodeFactory.getInstance().createLabel("To:", normalLabelFontSize);
        leftLabelList.add(validTo);
        List<Node> rightList = new ArrayList<>();
        Label validFromValue = JavaFXNodeFactory.getInstance().createLabel("yyyy-MM-dd", normalLabelFontSize);
        rightList.add(validFromValue);
        Label validToValue = JavaFXNodeFactory.getInstance().createLabel("yyyy-MM-dd", normalLabelFontSize);
        rightList.add(validToValue);
        HBox form = JavaFXNodeFactory.getInstance().createLRForm(leftLabelList, rightList, false);
        Button back = JavaFXNodeFactory.getInstance().createButton("Back");
        back.setPrefHeight(55);
        back.setOnMouseClicked((MouseEvent) -> showHairProm());
        Button delete = JavaFXNodeFactory.getInstance().createButton("Delete");
        delete.setPrefHeight(55);
        delete.setOnMouseClicked((MouseEvent) -> removePromotion());
        HBox buttonsHB = JavaFXNodeFactory.getInstance().createBottomButtons(back, delete);
        vbInScrollHProm.getChildren().addAll(name, promValue, form, buttonsHB);
    }

    private void removePromotion(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        managePromotionController.removePromotion(this.managePromotionBean);
        showHairProm();
    }

    public void fillView(HairdresserBean hairdresserBean){
        this.hairdresserBean = hairdresserBean;
        System.out.println("Filling View from HairdresserBean data passedBY TopBarHairdresserViewController");
        showHairProm();
    }

}
