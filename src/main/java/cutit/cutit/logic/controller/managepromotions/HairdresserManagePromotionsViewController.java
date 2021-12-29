package cutit.cutit.logic.controller.managepromotions;

import cutit.cutit.logic.bean.HairdresserBean;
import cutit.cutit.logic.bean.ManagePromotionBean;
import cutit.cutit.logic.bean.ShopBean;
import cutit.cutit.logic.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class HairdresserManagePromotionsViewController {

    private HairdresserBean hairdresserBean;
    private ShopBean shopBean;
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
            this.managePromotionBean = managePromotionController.getAllPromotions(shopBean);
            vbInScrollHProm.getChildren().clear();
            Button add = JavaFXNodeFactory.getInstance().createButton("Add Promotion");
            add.setOnMouseClicked((MouseEvent) -> addForm());
            vbInScrollHProm.getChildren().add(add);
            for(int i = 0; i<managePromotionBean.getPromotionsList().size(); i++) {
                String promotionName = managePromotionBean.getPromotionCodeI(i);
                Integer promotionOffValue = managePromotionBean.getPromotionOffValue(i);
                String promotionServiceName = managePromotionBean.getPromotionServiceNameI(i);
                String expire = managePromotionBean.getPromExpireDataI(i);
                Label l = JavaFXNodeFactory.getInstance().createCardLabel(promotionName, labelStyle);
                l.setOnMouseClicked((MouseEvent) -> deleteForm(promotionName, promotionOffValue, promotionServiceName, expire));
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
        Label service = JavaFXNodeFactory.getInstance().createLabel("Service:", normalLabelFontSize);
        leftLabelList.add(service);
        Label valueOFF = JavaFXNodeFactory.getInstance().createLabel("%Off:", normalLabelFontSize);
        leftLabelList.add(valueOFF);
        Label expire = JavaFXNodeFactory.getInstance().createLabel("Valid until:", normalLabelFontSize);
        leftLabelList.add(expire);
        List<Node> rightList = new ArrayList<>();
        TextField promName = new TextField();
        promName.setPromptText("Promotion's Name");
        promName.setMaxSize(180, 25);
        rightList.add(promName);
        ChoiceBox<String> promService = new ChoiceBox<>();
        promService.setMaxSize(180,25);
        try {
            this.managePromotionBean = managePromotionController.getAllServices(this.hairdresserBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int j=0; j<managePromotionBean.getServiceList().size(); j++){
            promService.getItems().add(managePromotionBean.getServiceName(j));
        }
        rightList.add(promService);
        TextArea promValue = new TextArea();
        promValue.setPromptText("5.50");
        promValue.setMaxSize(60,25);
        rightList.add(promValue);
        TextField tfExpireDate = new TextField();
        tfExpireDate.setPromptText("yyyy--MM-dd");
        tfExpireDate.setMaxSize(180,25);
        rightList.add(tfExpireDate);
        HBox form = JavaFXNodeFactory.getInstance().createLRForm(leftLabelList, rightList, true);
        Button back = JavaFXNodeFactory.getInstance().createButton("Back");
        back.setPrefHeight(55);
        back.setOnMouseClicked((MouseEvent) -> showHairProm());
        Button add = JavaFXNodeFactory.getInstance().createButton("Add");
        add.setPrefHeight(55);
        add.setOnMouseClicked((MouseEvent) -> addPromotion(promName.getText(), 5, tfExpireDate.getText(), promService.getValue()));
        HBox buttonsHB = JavaFXNodeFactory.getInstance().createBottomButtons(back, add);
       vbInScrollHProm.getChildren().addAll(title, form, buttonsHB);
    }

    private void addPromotion(String promName, Integer offValue,  String expireDate, String serviceName){
        managePromotionBean.setPromotionCode(promName);
        managePromotionBean.setPromOffValue(offValue);
        managePromotionBean.setPromExpireDate(expireDate);
        managePromotionBean.setPromServiceName(serviceName);
        managePromotionBean.setPromShopName(hairdresserBean.getShopName());
        try {
            managePromotionController.addPromotion(this.managePromotionBean);
            showHairProm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private LocalDateTime dateFromString(String expireDate) {
        return LocalDateTime.parse(expireDate + "T00:00");
    }

    private void deleteForm(String promName, Integer promOffValue, String promServiceName, String expireDate) {
       vbInScrollHProm.getChildren().clear();
        Label name = JavaFXNodeFactory.getInstance().createLabel(promName, titleFontSize);
        Label promValue = JavaFXNodeFactory.getInstance().createLabel(promOffValue.toString(), titleFontSize);
        Label promService = JavaFXNodeFactory.getInstance().createLabel(promServiceName, titleFontSize);
        List<Label> leftLabelList = new ArrayList<>();
        Label validTo = JavaFXNodeFactory.getInstance().createLabel("Valid until:", normalLabelFontSize);
        leftLabelList.add(validTo);
        List<Node> rightList = new ArrayList<>();
        Label validToValue = JavaFXNodeFactory.getInstance().createLabel(expireDate, normalLabelFontSize);
        rightList.add(validToValue);
        HBox form = JavaFXNodeFactory.getInstance().createLRForm(leftLabelList, rightList, false);
        Button back = JavaFXNodeFactory.getInstance().createButton("Back");
        back.setPrefHeight(55);
        back.setOnMouseClicked((MouseEvent) -> showHairProm());
        Button delete = JavaFXNodeFactory.getInstance().createButton("Delete");
        delete.setPrefHeight(55);
        delete.setOnMouseClicked((MouseEvent) -> removePromotion());
        HBox buttonsHB = JavaFXNodeFactory.getInstance().createBottomButtons(back, delete);
        vbInScrollHProm.getChildren().addAll(name, promValue, promService, form, buttonsHB);
    }

    private void removePromotion(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        managePromotionController.removePromotion(this.managePromotionBean);
        showHairProm();
    }

    public void fillView(ShopBean shopBean){
        this.shopBean = shopBean;
        System.out.println("Filling View from HairdresserBean data passedBY TopBarHairdresserViewController");
        showHairProm();
    }

}
