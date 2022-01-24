package cutit.controller.managepromotions;

import cutit.bean.ManagePromotionBeanInterface;
import cutit.bean.HairdresserBeanUQ;
import cutit.bean.ManagePromotionBean;
import cutit.bean.ShopBeanInterface;
import cutit.exception.DBConnectionException;
import cutit.exception.DuplicatedRecordException;
import cutit.exception.RecordNotFoundException;
import cutit.exception.WrongInputDataException;
import cutit.factory.AlertFactory;
import cutit.utils.TextFieldCheck;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HairdresserManagePromotionsViewController {

    private HairdresserBeanUQ hairdresserBeanFirstUI;
    private ShopBeanInterface shopBeanFirstUI;
    private ManagePromotionBeanInterface managePromotionBeanFirstUI;
    private ManagePromotionController managePromotionController;
    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private final Double titleFontSize = 30.0;
    private final Double normalLabelFontSize = 14.0;

    @FXML
    private VBox vbInScrollHProm;

    @FXML
    public void initialize(){
        managePromotionBeanFirstUI = new ManagePromotionBean();
        managePromotionController = new ManagePromotionController();
        vbInScrollHProm.setSpacing(15);
    }

    private void showHairProm() {
        try {
            managePromotionController.getAllPromotions(managePromotionBeanFirstUI, shopBeanFirstUI);
            vbInScrollHProm.getChildren().clear();
            Button add = JavaFXNodeFactory.getInstance().createButton("Add Promotion");
            add.setOnMouseClicked((MouseEvent) -> showAddForm());
            vbInScrollHProm.getChildren().add(add);
            for(int i = 0; i< managePromotionBeanFirstUI.getPromotionsBeanList().size(); i++) {
                ManagePromotionBeanInterface promotionBean = managePromotionBeanFirstUI.getPromotionsBeanList().get(i);
                String promotionCode = promotionBean.getPromotionCode();
                Integer promotionOffValue = promotionBean.getPromOffValue();
                String promotionServiceName = promotionBean.getPromServiceName();
                LocalDate expire = promotionBean.getPromExpireDate();
                Label l = JavaFXNodeFactory.getInstance().createCardLabel(promotionCode, labelStyle);
                l.setOnMouseClicked((MouseEvent) -> showDeleteForm(promotionCode, promotionOffValue, promotionServiceName, expire.toString()));
                vbInScrollHProm.getChildren().add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAddForm() {
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
            this.managePromotionBeanFirstUI = managePromotionController.getAllServices(shopBeanFirstUI);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int j = 0; j< managePromotionBeanFirstUI.getServiceList().size(); j++){
            promService.getItems().add(managePromotionBeanFirstUI.getServiceList().get(j));
        }
        rightList.add(promService);
        TextArea promValue = new TextArea();
        promValue.setPromptText("5.50");
        promValue.setMaxSize(60,25);
        rightList.add(promValue);
        DatePicker dp = new DatePicker();
        dp.setMaxSize(180,25);
        dp.setValue(LocalDate.now());
        rightList.add(dp);
        HBox form = JavaFXNodeFactory.getInstance().createLRForm(leftLabelList, rightList, true);
        Button back = JavaFXNodeFactory.getInstance().createButton("Back");
        back.setPrefHeight(55);
        back.setOnMouseClicked((MouseEvent) -> showHairProm());
        Button add = JavaFXNodeFactory.getInstance().createButton("Add");
        add.setPrefHeight(55);
        add.setOnMouseClicked((MouseEvent) -> addPromotion(promName.getText(), promValue.getText(), dp.getValue() , promService.getValue()));
        HBox buttonsHB = JavaFXNodeFactory.getInstance().createBottomButtons(back, add);
       vbInScrollHProm.getChildren().addAll(title, form, buttonsHB);
    }

    private void addPromotion(String promCode, String offValue,  LocalDate expireDate, String serviceName){
        if(!Objects.equals(promCode, "") && TextFieldCheck.isInteger(offValue, "Off value field must be an integer number (5, 10, 25...)")){
            managePromotionBeanFirstUI.setPromotionCode(promCode);
            managePromotionBeanFirstUI.setPromOffValue(Integer.valueOf(offValue));
            managePromotionBeanFirstUI.setPromExpireDate(expireDate);
            managePromotionBeanFirstUI.setPromServiceName(serviceName);
            managePromotionBeanFirstUI.setPromShopName(shopBeanFirstUI.getShopName());
            try {
                managePromotionController.addPromotion(this.managePromotionBeanFirstUI);
                showHairProm();
            } catch (DuplicatedRecordException | WrongInputDataException | RecordNotFoundException exception) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.INFORMATION, "Information", exception.getMessage());
                alert.showAndWait();
            } catch(DBConnectionException dce){
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, "Connection error", "Please check your internet connection.");
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showDeleteForm(String promCode, Integer promOffValue, String promServiceName, String expireDate) {
       vbInScrollHProm.getChildren().clear();
        Label name = JavaFXNodeFactory.getInstance().createLabel(promCode, titleFontSize);
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
        delete.setOnMouseClicked((MouseEvent) -> removePromotion(promCode, promOffValue, expireDate));
        HBox buttonsHB = JavaFXNodeFactory.getInstance().createBottomButtons(back, delete);
        vbInScrollHProm.getChildren().addAll(name, promValue, promService, form, buttonsHB);
    }

    private void removePromotion(String promCode, Integer offValue, String expireDate){
        managePromotionBeanFirstUI.setPromotionCode(promCode);
        managePromotionBeanFirstUI.setPromOffValue(offValue);
        managePromotionBeanFirstUI.setPromExpireDate(LocalDate.parse(expireDate));
        try {
            managePromotionController.removePromotion(this.managePromotionBeanFirstUI);
        } catch (Exception e) {
            e.printStackTrace();
        }
        showHairProm();
    }

    public void fillView(ShopBeanInterface shopBeanFirstUI){
        this.shopBeanFirstUI = shopBeanFirstUI;
        showHairProm();
    }

}
