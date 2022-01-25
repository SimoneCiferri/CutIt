package cutit.controller.manageservices;

import cutit.bean.ManageServiceBeanInterface;
import cutit.bean.ManageServiceBean;
import cutit.bean.ShopBeanInterface;
import cutit.bean.ShopBean;
import cutit.exception.DBConnectionException;
import cutit.exception.DuplicatedRecordException;
import cutit.utils.TextFieldCheck;
import cutit.factory.AlertFactory;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HairdresserManageServicesViewController {

    private ShopBeanInterface shopBeanFirstUI;
    private ManageServiceBeanInterface manageServicesBean;
    private ManageServicesController manageServicesController;
    private static final String CONNECTION_ERROR_TITLE = "Connection error";
    private static final String CONNECTION_ERROR_MESSAGE = "Please check your internet connection.";
    private static final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private static final Double titleFontSize = 30.0;
    private static final Double normalLabelFontSize = 14.0;

    @FXML
    private VBox vbInScrollHS;

    @FXML
    public void initialize(){
        manageServicesBean = new ManageServiceBean();
        manageServicesController = new ManageServicesController();
        vbInScrollHS.setSpacing(15);
    }

    private void showHairServices() {
        try {
            manageServicesController.getAllServices(manageServicesBean, shopBeanFirstUI);
            vbInScrollHS.getChildren().clear();
            Button add = JavaFXNodeFactory.getInstance().createButton("Add Service");
            add.setOnMouseClicked(MouseEvent -> showAddForm());
            vbInScrollHS.getChildren().add(add);
            for(int i = 0; i< manageServicesBean.getAllServicesList().size(); i++) {
                String serviceName = manageServicesBean.getAllServicesList().get(i);
                Label l = JavaFXNodeFactory.getInstance().createCardLabel(serviceName, labelStyle);
                l.setOnMouseClicked(mouseEvent -> showDeleteForm(serviceName, manageServicesBean.getServiceList().get(serviceName)));
                vbInScrollHS.getChildren().add(l);
            }
        } catch(DBConnectionException dce){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, CONNECTION_ERROR_TITLE, CONNECTION_ERROR_MESSAGE);
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAddForm() {
        vbInScrollHS.getChildren().clear();
        List<Label> labelList = new ArrayList<>();
        Label title = JavaFXNodeFactory.getInstance().createLabel("Add Service", titleFontSize);
        Label name = JavaFXNodeFactory.getInstance().createLabel("Name:", normalLabelFontSize);
        labelList.add(name);
        Label price = JavaFXNodeFactory.getInstance().createLabel("Price:", normalLabelFontSize);
        labelList.add(price);
        List<Node> nodeList = new ArrayList<>();
        TextField serviceName = new TextField();
        serviceName.setPromptText("Service Name");
        serviceName.setMaxSize(180, 25);
        nodeList.add(serviceName);
        TextField servicePrice = new TextField();
        servicePrice.setPromptText("Service Price");
        servicePrice.setMaxSize(180, 25);
        nodeList.add(servicePrice);
        HBox form = JavaFXNodeFactory.getInstance().createLRForm(labelList, nodeList, true);
        Button back = JavaFXNodeFactory.getInstance().createButton("Back");
        back.setPrefHeight(55);
        back.setOnMouseClicked(mouseEvent -> showHairServices());
        Button add = JavaFXNodeFactory.getInstance().createButton("Add");
        add.setPrefHeight(55);
        add.setOnMouseClicked(mouseEvent -> addService(serviceName,servicePrice));
        HBox buttonsHB = JavaFXNodeFactory.getInstance().createBottomButtons(back, add);
        vbInScrollHS.getChildren().addAll(title, form, buttonsHB);
    }
    @FXML
    private void addService(TextField serviceName, TextField servicePrice){
        if (!Objects.equals(serviceName.getText(), "") && TextFieldCheck.isNumeric(servicePrice.getText(), "Price field must be a number (correct format is 4.5 instead of 4,5).")) {
            manageServicesBean.setServiceName(serviceName.getText());
            manageServicesBean.setServicePrice(Float.valueOf(servicePrice.getText()));
            manageServicesBean.setServiceShopName(shopBeanFirstUI.getShopName());
            try {
                manageServicesController.addService(manageServicesBean);
                showHairServices();
            } catch (DuplicatedRecordException de) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.INFORMATION, "Information", de.getMessage());
                alert.showAndWait();
            } catch(DBConnectionException dce){
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, CONNECTION_ERROR_TITLE, CONNECTION_ERROR_MESSAGE);
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showDeleteForm(String serviceName, Float servicePrice) {
        vbInScrollHS.getChildren().clear();
        Label name = JavaFXNodeFactory.getInstance().createLabel(serviceName, titleFontSize);
        Label price = JavaFXNodeFactory.getInstance().createLabel(servicePrice.toString(), normalLabelFontSize);
        Button back = JavaFXNodeFactory.getInstance().createButton("Back");
        back.setPrefHeight(55);
        back.setOnMouseClicked(mouseEvent -> showHairServices());
        Button delete = JavaFXNodeFactory.getInstance().createButton("Delete");
        delete.setPrefHeight(55);
        delete.setOnMouseClicked(mouseEvent -> deleteService(serviceName, servicePrice));
        HBox buttonsHB = JavaFXNodeFactory.getInstance().createBottomButtons(back, delete);
        vbInScrollHS.getChildren().addAll(name, price, buttonsHB);
    }

    private void deleteService(String serviceName, Float servicePrice){
        manageServicesBean.setServiceName(serviceName);
        manageServicesBean.setServicePrice(servicePrice);
        manageServicesBean.setServiceShopName(shopBeanFirstUI.getShopName());
        try {
            manageServicesController.deleteService(this.manageServicesBean);
        } catch(DBConnectionException dce){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, CONNECTION_ERROR_TITLE, CONNECTION_ERROR_MESSAGE);
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
        showHairServices();
    }

    public void fillView(ShopBeanInterface shopBeanFirstUI){
        this.shopBeanFirstUI = shopBeanFirstUI;
        showHairServices();
    }

}
