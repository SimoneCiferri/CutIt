package cutit.controller.managepromotions;

import cutit.bean.ManagePromotionBean;
import cutit.bean.ManagePromotionBeanInterface;
import cutit.bean.ShopBeanInterface;
import cutit.exception.*;
import cutit.factory.AlertFactory;
import cutit.factory.JavaFXNodeFactory;
import cutit.utils.TextFieldCheck;
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
import java.util.StringTokenizer;

public class HairdresserManagePromotionsViewController2 {

    private ShopBeanInterface shopBeanSecondUI;
    private ManagePromotionBeanInterface managePromotionBeanSecondUI;
    private ManagePromotionController managePromotionController;
    private static final String LABEL_STYLE = "-fx-border-color: grey; -fx-border-radius: 5;";
    private static final Double TITLE_FONT_SIZE = 30.0;
    private static final Double NORMAL_LABEL_FONT_SIZE = 14.0;

    @FXML
    private VBox vbPromotionsInScroll;

    @FXML
    private VBox vbAddAndDelete;

    @FXML
    public void initialize(){
        managePromotionBeanSecondUI = new ManagePromotionBean();
        managePromotionController = new ManagePromotionController();
        vbPromotionsInScroll.setSpacing(15);
    }

    private void showPromotions() {
        try {
            managePromotionController.getAllPromotions(managePromotionBeanSecondUI, shopBeanSecondUI);
            vbPromotionsInScroll.getChildren().clear();
            Button add = JavaFXNodeFactory.getInstance().createButton("Add Promotion");
            add.setOnMouseClicked(mouseEvent -> showAddPromotionForm());
            vbPromotionsInScroll.getChildren().add(add);
            List<ManagePromotionBeanInterface> allPromotionBeans = managePromotionBeanSecondUI.getPromotionsBeanList();
            for (ManagePromotionBeanInterface promotionBean : allPromotionBeans) {
                String promotionCode = promotionBean.getPromotionCode();
                Label l = JavaFXNodeFactory.getInstance().createCardLabel2(promotionCode, LABEL_STYLE);
                l.setOnMouseClicked(mouseEvent -> showPromotionInfo(promotionCode, promotionBean.getPromOffValue(), promotionBean.getPromServiceName(), promotionBean.getPromExpireDate().toString()));
                vbPromotionsInScroll.getChildren().add(l);
            }
        } catch (DBConnectionException dbe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sqle){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        }
        catch (RecordNotFoundException e){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), e.getMessage());
            alert.showAndWait();
        }
    }

    private void showAddPromotionForm() {
        vbAddAndDelete.getChildren().clear();
        Label title = JavaFXNodeFactory.getInstance().createLabel2("Add Promotion", TITLE_FONT_SIZE);
        List<Label> leftLabelList = new ArrayList<>();
        Label name = JavaFXNodeFactory.getInstance().createLabel2("Promotion Name And Off Value:", NORMAL_LABEL_FONT_SIZE);
        leftLabelList.add(name);
        Label service = JavaFXNodeFactory.getInstance().createLabel2("Service:", NORMAL_LABEL_FONT_SIZE);
        leftLabelList.add(service);
        Label expire = JavaFXNodeFactory.getInstance().createLabel2("yyyy-MM-dd:", NORMAL_LABEL_FONT_SIZE);
        leftLabelList.add(expire);
        List<Node> rightList = new ArrayList<>();
        TextField promotionCodeAndOffValue = new TextField();
        promotionCodeAndOffValue.setPromptText("Promotion Name-Off Value");
        promotionCodeAndOffValue.setMaxSize(200, 25);
        rightList.add(promotionCodeAndOffValue);
        ChoiceBox<String> promotionServices = new ChoiceBox<>();
        promotionServices.setMaxSize(180,25);
        try {
            this.managePromotionBeanSecondUI = managePromotionController.getAllServices(shopBeanSecondUI);
        } catch (DBConnectionException dbe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sqle){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        }
        for(int j = 0; j< managePromotionBeanSecondUI.getServiceList().size(); j++){
            promotionServices.getItems().add(managePromotionBeanSecondUI.getServiceList().get(j));
        }
        rightList.add(promotionServices);
        TextField date = new TextField();
        date.setMaxSize(180, 25);
        date.setText(LocalDate.now().toString());
        rightList.add(date);
        HBox form = JavaFXNodeFactory.getInstance().createLRForm(leftLabelList, rightList, true);
        Button addButton = JavaFXNodeFactory.getInstance().createButton("Add");
        addButton.setOnMouseClicked(mouseEvent -> addPromotion(promotionCodeAndOffValue.getText(), date.getText(), promotionServices.getValue()));
        vbAddAndDelete.getChildren().addAll(title, form, addButton);
    }

    private void showPromotionInfo(String promotionCode, Integer promOffValue, String promotionService, String expireDate) {
        vbAddAndDelete.getChildren().clear();
        Label name = JavaFXNodeFactory.getInstance().createLabel2(promotionCode, TITLE_FONT_SIZE);
        Label promValue = JavaFXNodeFactory.getInstance().createLabel2(promOffValue.toString(), TITLE_FONT_SIZE);
        Label promService = JavaFXNodeFactory.getInstance().createLabel2(promotionService, TITLE_FONT_SIZE);
        List<Label> leftLabelList = new ArrayList<>();
        Label expire = JavaFXNodeFactory.getInstance().createLabel2("Expire on:", NORMAL_LABEL_FONT_SIZE);
        leftLabelList.add(expire);
        List<Node> rightList = new ArrayList<>();
        Label expireValue = JavaFXNodeFactory.getInstance().createLabel2(expireDate, NORMAL_LABEL_FONT_SIZE);
        rightList.add(expireValue);
        HBox form = JavaFXNodeFactory.getInstance().createLRForm(leftLabelList, rightList, false);
        Button deleteButton = JavaFXNodeFactory.getInstance().createButton("Delete");
        deleteButton.setOnMouseClicked(mouseEvent -> removePromotion(promotionCode, promOffValue, expireDate));
        vbAddAndDelete.getChildren().addAll(name, promValue, promService, form, deleteButton);
    }

    private void removePromotion(String promCode, Integer offValue, String expireDate){
        managePromotionBeanSecondUI.setPromotionCode(promCode);
        managePromotionBeanSecondUI.setPromOffValue(offValue);
        managePromotionBeanSecondUI.setPromExpireDate(LocalDate.parse(expireDate));
        try {
            managePromotionController.removePromotion(this.managePromotionBeanSecondUI);
            showPromotions();
            vbAddAndDelete.getChildren().clear();
        } catch (DBConnectionException dbe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sqle){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        }
    }

    private void addPromotion(String promotionCodeAndOffValue, String expireDate, String serviceName) {
        if (!Objects.equals(promotionCodeAndOffValue, "") && checkPromotionInput(promotionCodeAndOffValue)) {
            StringTokenizer st = new StringTokenizer(promotionCodeAndOffValue, "-");
            String promotionCode = st.nextToken();
            String offValue = st.nextToken();
            if (TextFieldCheck.isInteger(offValue, "Off value field must be an integer number (5, 10, 25...).") && TextFieldCheck.isDateFormat(expireDate, "Expire date is not correct. Please follow the syntax yyyy-MM-dd")) {
                managePromotionBeanSecondUI.setPromotionCode(promotionCode);
                managePromotionBeanSecondUI.setPromOffValue(Integer.valueOf(offValue));
                managePromotionBeanSecondUI.setPromExpireDate(LocalDate.parse(expireDate));
                managePromotionBeanSecondUI.setPromServiceName(serviceName);
                managePromotionBeanSecondUI.setPromShopName(shopBeanSecondUI.getShopName());
                try {
                    managePromotionController.addPromotion(this.managePromotionBeanSecondUI);
                    showPromotions();
                    showPromotionInfo(promotionCode, Integer.valueOf(offValue), serviceName, expireDate);
                } catch (DuplicatedRecordException | WrongInputDataException | RecordNotFoundException e) {
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

    private boolean checkPromotionInput(String promotionInput){
        StringTokenizer st = new StringTokenizer(promotionInput, "-");
        if(st.countTokens() == 2){
            return true;
        }else{
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.INFORMATION, ExceptionText.getInformationTitle(), "Not Panic!", "Input is not correct. Please follow the syntax ' Promotion Code-Off Value '");
            alert.showAndWait();
            return false;
        }
    }

    public void fillView(ShopBeanInterface shopBeanSecondUI){
        this.shopBeanSecondUI = shopBeanSecondUI;
        showPromotions();
    }
}
