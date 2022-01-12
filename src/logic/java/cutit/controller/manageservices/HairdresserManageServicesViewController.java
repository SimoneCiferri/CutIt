package cutit.controller.manageservices;

import cutit.bean.ManageServiceBean;
import cutit.bean.firstui.ManageServiceBeanFirstUI;
import cutit.bean.ShopBean;
import cutit.bean.firstui.ShopBeanUQ;
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

public class HairdresserManageServicesViewController {

    private ShopBean shopBeanFirstUI;
    private ManageServiceBean manageServicesBean;
    private ManageServicesController manageServicesController;
    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private final Double titleFontSize = 30.0;
    private final Double normalLabelFontSize = 14.0;

    @FXML
    private VBox vbInScrollHS;

    @FXML
    public void initialize(){
        shopBeanFirstUI = new ShopBeanUQ();
        manageServicesBean = new ManageServiceBeanFirstUI();
        manageServicesController = new ManageServicesController();
        vbInScrollHS.setSpacing(15);
        System.out.println("CONTROLLER GRAFICO HAIRDRESSERMANAGESERVICESVIEWCONTROLLER");
    }

    private void showHairServ() {
        try {
            manageServicesController.getAllServices(manageServicesBean, shopBeanFirstUI);
            vbInScrollHS.getChildren().clear();
            Button add = JavaFXNodeFactory.getInstance().createButton("Add Service");
            add.setOnMouseClicked((MouseEvent) -> showAddForm());
            vbInScrollHS.getChildren().add(add);
            for(int i = 0; i< manageServicesBean.getAllServicesList().size(); i++) {
                String serviceName = manageServicesBean.getAllServicesList().get(i);
                Label l = JavaFXNodeFactory.getInstance().createCardLabel(serviceName, labelStyle);
                l.setOnMouseClicked((MouseEvent) -> deleteForm(serviceName, manageServicesBean.getServiceList().get(serviceName)));
                vbInScrollHS.getChildren().add(l);
            }
        } catch(DBConnectionException dce){
            AlertFactory.getInstance().generateAlert(Alert.AlertType.WARNING, "Connection error", "Please check your internet connection.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAddForm() {
        vbInScrollHS.getChildren().clear();
        List<Label> labelList = new ArrayList<Label>();
        Label title = JavaFXNodeFactory.getInstance().createLabel("Add Service", titleFontSize);
        Label name = JavaFXNodeFactory.getInstance().createLabel("Name:", normalLabelFontSize);
        labelList.add(name);
        Label price = JavaFXNodeFactory.getInstance().createLabel("Price:", normalLabelFontSize);
        labelList.add(price);
        List<Node> nodeList = new ArrayList<Node>();
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
        back.setOnMouseClicked((MouseEvent) -> showHairServ());
        Button add = JavaFXNodeFactory.getInstance().createButton("Add");
        add.setPrefHeight(55);
        add.setOnMouseClicked((MouseEvent) -> addService(serviceName,servicePrice));
        HBox buttonsHB = JavaFXNodeFactory.getInstance().createBottomButtons(back, add);
        vbInScrollHS.getChildren().addAll(title, form, buttonsHB);
    }
    @FXML
    private void addService(TextField serviceName, TextField servicePrice){
        if (TextFieldCheck.isNumeric(servicePrice.getText(), "Information", "Not Panic!", "Price field must be a number (correct format is 4.5 instead of 4,5).")) {
            manageServicesBean.setServiceName(serviceName.getText());
            manageServicesBean.setServicePrice(Float.valueOf(servicePrice.getText()));
            manageServicesBean.setServiceShopName(shopBeanFirstUI.getShopName());
            try {
                manageServicesController.addService(manageServicesBean);
                showHairServ();
            } catch (DuplicatedRecordException de) {
                AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, "Information", de.getMessage());
            } catch(DBConnectionException dce){
                AlertFactory.getInstance().generateAlert(Alert.AlertType.WARNING, "Connection error", "Please check your internet connection.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, "Error!", "Not Panic!", "You have to insert numbers in the price field");
            return false;
        }
        return true;
    }

    private void deleteForm(String serviceName, Float servicePrice) {
        vbInScrollHS.getChildren().clear();
        Label name = JavaFXNodeFactory.getInstance().createLabel(serviceName, titleFontSize);
        Label price = JavaFXNodeFactory.getInstance().createLabel(servicePrice.toString(), normalLabelFontSize);
        Button back = JavaFXNodeFactory.getInstance().createButton("Back");
        back.setPrefHeight(55);
        back.setOnMouseClicked((MouseEvent) -> showHairServ());
        Button delete = JavaFXNodeFactory.getInstance().createButton("Delete");
        delete.setPrefHeight(55);
        delete.setOnMouseClicked((MouseEvent) -> deleteService(serviceName, servicePrice));
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
            AlertFactory.getInstance().generateAlert(Alert.AlertType.WARNING, "Connection error", "Please check your internet connection.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        showHairServ();
    }

    public void fillView(ShopBean shopBeanFirstUI){
        this.shopBeanFirstUI = shopBeanFirstUI;
        System.out.println("Filling View from HairdresserBean data passedBY TopBarHairdresserViewController");
        showHairServ();
    }

}
