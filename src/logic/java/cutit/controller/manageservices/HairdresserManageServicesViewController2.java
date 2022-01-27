package cutit.controller.manageservices;

import cutit.bean.ManageServiceBean;
import cutit.bean.ManageServiceBeanInterface;
import cutit.bean.ShopBeanInterface;
import cutit.exception.DBConnectionException;
import cutit.exception.DuplicatedRecordException;
import cutit.exception.ExceptionText;
import cutit.factory.AlertFactory;
import cutit.factory.JavaFXNodeFactory;
import cutit.utils.TextFieldCheck;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class HairdresserManageServicesViewController2 {

    private ShopBeanInterface shopBeanSecondUI;
    private ManageServiceBeanInterface manageServicesBeanSecondUI;
    private ManageServicesController manageServicesController;
    private static final String LABEL_STYLE = "-fx-border-color: grey; -fx-border-radius: 5;";
    private static final Double TITLE_FONT_SIZE = 30.0;
    private static final Double NORMAL_LABEL_FONT_SIZE = 14.0;

    @FXML
    private VBox vbServicesInScroll;

    @FXML
    private VBox vbInScrollToDo;


    @FXML
    public void initialize(){
        manageServicesController = new ManageServicesController();
        manageServicesBeanSecondUI = new ManageServiceBean();
    }


    private void showServices() {
        try {
            manageServicesController.getAllServices(manageServicesBeanSecondUI, shopBeanSecondUI);
            vbServicesInScroll.getChildren().clear();
            Button add = JavaFXNodeFactory.getInstance().createButton("Add Service");
            add.setOnMouseClicked(mouseEvent -> showAddServiceForm());
            vbServicesInScroll.getChildren().add(add);
            List<String> services = manageServicesBeanSecondUI.getAllServicesList();
            for (String serviceName : services) {
                Label l = JavaFXNodeFactory.getInstance().createCardLabel2(serviceName, LABEL_STYLE);
                l.setOnMouseClicked(mouseEvent -> showServiceInfo(serviceName, manageServicesBeanSecondUI.getServiceList().get(serviceName)));
                vbServicesInScroll.getChildren().add(l);
            }
        } catch(DBConnectionException dbe){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sqle) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        }
    }

    private void showAddServiceForm() {
        vbInScrollToDo.getChildren().clear();
        List<Label> labelList = new ArrayList<>();
        Label title = JavaFXNodeFactory.getInstance().createLabel("Add new Service", TITLE_FONT_SIZE);
        Label name = JavaFXNodeFactory.getInstance().createLabel("Name-Price:", NORMAL_LABEL_FONT_SIZE);
        labelList.add(name);
        List<Node> nodeList = new ArrayList<>();
        TextField serviceData = new TextField();
        serviceData.setPromptText("Service Name-Service Price");
        serviceData.setMaxSize(180, 25);
        nodeList.add(serviceData);
        HBox form = JavaFXNodeFactory.getInstance().createLRForm(labelList, nodeList, true);
        Button add = JavaFXNodeFactory.getInstance().createButton("Add");
        add.setOnMouseClicked(mouseEvent -> addService(serviceData));
        vbInScrollToDo.getChildren().addAll(title, form, add);
    }

    private void showServiceInfo(String serviceName, Float servicePrice) {
        vbInScrollToDo.getChildren().clear();
        Label name = JavaFXNodeFactory.getInstance().createLabel2(serviceName, TITLE_FONT_SIZE);
        Label price = JavaFXNodeFactory.getInstance().createLabel2(servicePrice.toString(), NORMAL_LABEL_FONT_SIZE);
        Button deleteButton = JavaFXNodeFactory.getInstance().createButton("Delete");
        deleteButton.setOnMouseClicked(mouseEvent -> deleteService(serviceName, servicePrice));
        vbInScrollToDo.getChildren().addAll(name, price, deleteButton);
    }

    private void deleteService(String serviceName, Float servicePrice) {
        manageServicesBeanSecondUI.setServiceName(serviceName);
        manageServicesBeanSecondUI.setServicePrice(servicePrice);
        manageServicesBeanSecondUI.setServiceShopName(shopBeanSecondUI.getShopName());
        try {
            manageServicesController.deleteService(this.manageServicesBeanSecondUI);
            showServices();
            vbInScrollToDo.getChildren().clear();
        }  catch(DBConnectionException dbe){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sqle) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        }
    }

    private void addService(TextField serviceData) {
        if (!Objects.equals(serviceData.getText(), "") && checkServiceInput(serviceData.getText())) {
            StringTokenizer st = new StringTokenizer(serviceData.getText(), "-");
            String name = st.nextToken();
            String price = st.nextToken();
            if (TextFieldCheck.isNumeric(price, "Input is not correct. Please follow the syntax ' Name-Price '")) {
                manageServicesBeanSecondUI.setServiceName(name);
                manageServicesBeanSecondUI.setServicePrice(Float.valueOf(price));
                manageServicesBeanSecondUI.setServiceShopName(shopBeanSecondUI.getShopName());
                try {
                    manageServicesController.addService(manageServicesBeanSecondUI);
                    showServices();
                    showServiceInfo(name, Float.valueOf(price));
                } catch (DuplicatedRecordException e) {
                    Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), e.getMessage());
                    alert.showAndWait();
                } catch (DBConnectionException dbe) {
                    Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
                    alert.showAndWait();
                } catch (SQLException sqle) {
                    Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
                    alert.showAndWait();
                }
            }
        }
    }

    private boolean checkServiceInput(String serviceInput){
        StringTokenizer st = new StringTokenizer(serviceInput, "-");
        if(st.countTokens() == 2){
            return true;
        }else{
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), "Not Panic!", "Input is not correct. Please follow the syntax ' Name-Price '");
            alert.showAndWait();
            return false;
        }
    }

    public void fillView(ShopBeanInterface shopBeanSecondUI){
        this.shopBeanSecondUI = shopBeanSecondUI;
        showServices();
    }
}
