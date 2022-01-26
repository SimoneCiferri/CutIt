package cutit.controller.managepromotions;

import cutit.bean.ManagePromotionBeanInterface;
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

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HairdresserManagePromotionsViewController {

    private ShopBeanInterface shopBeanFirstUI;
    private ManagePromotionBeanInterface managePromotionBeanFirstUI;
    private ManagePromotionController managePromotionController;
    private static final String CONNECTION_ERROR_TITLE = "Connection error";
    private static final String WARNING_TITLE = "Warning";
    private static final String CONNECTION_ERROR_MESSAGE = "Please check your internet connection. If problem persists try to restart the application.";
    private static final String SQL_ERROR_MESSAGE = "Please check your internet connection. If problem persists contact us at cutitapp@support.com.";
    private static final String LABEL_STYLE = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private static final Double TITLE_FONT_SIZE = 30.0;
    private static final Double NORMAL_LABEL_FONT_SIZE = 14.0;

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
            add.setOnMouseClicked(mouseEvent -> showAddForm());
            vbInScrollHProm.getChildren().add(add);
            for(int i = 0; i< managePromotionBeanFirstUI.getPromotionsBeanList().size(); i++) {
                ManagePromotionBeanInterface promotionBean = managePromotionBeanFirstUI.getPromotionsBeanList().get(i);
                String promotionCode = promotionBean.getPromotionCode();
                Integer promotionOffValue = promotionBean.getPromOffValue();
                String promotionServiceName = promotionBean.getPromServiceName();
                LocalDate expire = promotionBean.getPromExpireDate();
                Label l = JavaFXNodeFactory.getInstance().createCardLabel(promotionCode, LABEL_STYLE);
                l.setOnMouseClicked(mouseEvent -> showDeleteForm(promotionCode, promotionOffValue, promotionServiceName, expire.toString()));
                vbInScrollHProm.getChildren().add(l);
            }
        } catch (DBConnectionException dbe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, CONNECTION_ERROR_MESSAGE);
            alert.showAndWait();
        } catch (SQLException sqle){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR,CONNECTION_ERROR_TITLE, SQL_ERROR_MESSAGE );
            alert.showAndWait();
        }
        catch (RecordNotFoundException e){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, WARNING_TITLE, e.getMessage());
            alert.showAndWait();
        }
    }

    private void showAddForm() {
        vbInScrollHProm.getChildren().clear();
        Label title = JavaFXNodeFactory.getInstance().createLabel("Add Promotion", TITLE_FONT_SIZE);
        List<Label> leftLabelList = new ArrayList<>();
        Label name = JavaFXNodeFactory.getInstance().createLabel("Name:", NORMAL_LABEL_FONT_SIZE);
        leftLabelList.add(name);
        Label service = JavaFXNodeFactory.getInstance().createLabel("Service:", NORMAL_LABEL_FONT_SIZE);
        leftLabelList.add(service);
        Label valueOFF = JavaFXNodeFactory.getInstance().createLabel("%Off:", NORMAL_LABEL_FONT_SIZE);
        leftLabelList.add(valueOFF);
        Label expire = JavaFXNodeFactory.getInstance().createLabel("Valid until:", NORMAL_LABEL_FONT_SIZE);
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
        } catch (DBConnectionException dbe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, CONNECTION_ERROR_MESSAGE);
            alert.showAndWait();
        } catch (SQLException sqle){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, SQL_ERROR_MESSAGE);
            alert.showAndWait();
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
        back.setOnMouseClicked(mouseEvent -> showHairProm());
        Button add = JavaFXNodeFactory.getInstance().createButton("Add");
        add.setPrefHeight(55);
        add.setOnMouseClicked(mouseEvent -> addPromotion(promName.getText(), promValue.getText(), dp.getValue() , promService.getValue()));
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
            } catch (DuplicatedRecordException | WrongInputDataException | RecordNotFoundException e) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, WARNING_TITLE, e.getMessage());
                alert.showAndWait();
            } catch(DBConnectionException dbe){
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, CONNECTION_ERROR_MESSAGE);
                alert.showAndWait();
            } catch (SQLException sqle) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, SQL_ERROR_MESSAGE);
                alert.showAndWait();
            }
        }
    }

    private void showDeleteForm(String promCode, Integer promOffValue, String promServiceName, String expireDate) {
       vbInScrollHProm.getChildren().clear();
        Label name = JavaFXNodeFactory.getInstance().createLabel(promCode, TITLE_FONT_SIZE);
        Label promValue = JavaFXNodeFactory.getInstance().createLabel(promOffValue.toString(), TITLE_FONT_SIZE);
        Label promService = JavaFXNodeFactory.getInstance().createLabel(promServiceName, TITLE_FONT_SIZE);
        List<Label> leftLabelList = new ArrayList<>();
        Label validTo = JavaFXNodeFactory.getInstance().createLabel("Valid until:", NORMAL_LABEL_FONT_SIZE);
        leftLabelList.add(validTo);
        List<Node> rightList = new ArrayList<>();
        Label validToValue = JavaFXNodeFactory.getInstance().createLabel(expireDate, NORMAL_LABEL_FONT_SIZE);
        rightList.add(validToValue);
        HBox form = JavaFXNodeFactory.getInstance().createLRForm(leftLabelList, rightList, false);
        Button back = JavaFXNodeFactory.getInstance().createButton("Back");
        back.setPrefHeight(55);
        back.setOnMouseClicked(mouseEvent -> showHairProm());
        Button delete = JavaFXNodeFactory.getInstance().createButton("Delete");
        delete.setPrefHeight(55);
        delete.setOnMouseClicked(mouseEvent -> removePromotion(promCode, promOffValue, expireDate));
        HBox buttonsHB = JavaFXNodeFactory.getInstance().createBottomButtons(back, delete);
        vbInScrollHProm.getChildren().addAll(name, promValue, promService, form, buttonsHB);
    }

    private void removePromotion(String promCode, Integer offValue, String expireDate){
        managePromotionBeanFirstUI.setPromotionCode(promCode);
        managePromotionBeanFirstUI.setPromOffValue(offValue);
        managePromotionBeanFirstUI.setPromExpireDate(LocalDate.parse(expireDate));
        try {
            managePromotionController.removePromotion(this.managePromotionBeanFirstUI);
        } catch (DBConnectionException dbe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, CONNECTION_ERROR_MESSAGE);
            alert.showAndWait();
        } catch (SQLException sqle){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, SQL_ERROR_MESSAGE);
            alert.showAndWait();
        }
        showHairProm();
    }

    public void fillView(ShopBeanInterface shopBeanFirstUI){
        this.shopBeanFirstUI = shopBeanFirstUI;
        showHairProm();
    }

}
