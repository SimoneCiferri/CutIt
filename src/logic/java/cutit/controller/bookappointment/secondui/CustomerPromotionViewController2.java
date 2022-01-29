package cutit.controller.bookappointment.secondui;

import cutit.bean.interfaces.CustomerBeanInterface;
import cutit.bean.interfaces.PromotionBeanInterface;
import cutit.bean.PromotionBean;
import cutit.controller.bookappointment.BookAppointmentController;
import cutit.exception.DBConnectionException;
import cutit.exception.ExceptionText;
import cutit.exception.RecordNotFoundException;
import cutit.factory.AlertFactory;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.List;

public class CustomerPromotionViewController2 {

    private CustomerBeanInterface customerBeanSecondUI;
    private PromotionBeanInterface promotionBean;
    private BookAppointmentController bookAppointmentController;
    private static final String LABEL_STYLE = "-fx-border-color: grey; -fx-border-radius: 5;";
    private static final Double TITLE_FONT_SIZE = 30.0;
    private static final Double NORMAL_LABEL_FONT_SIZE = 14.0;

    @FXML
    private VBox vbPromotionsInScroll;

    @FXML
    private VBox vbPromotionInfo;

    @FXML
    public void initialize(){
        bookAppointmentController = new BookAppointmentController();
        promotionBean = new PromotionBean();
    }

    public void fillView(CustomerBeanInterface customerBeanSecondUI){
        this.customerBeanSecondUI = customerBeanSecondUI;
        showMyPromotions();
    }

    private void showMyPromotions() {
        vbPromotionsInScroll.getChildren().clear();
        try {
            bookAppointmentController.getPersonalPromotions(customerBeanSecondUI);
            List<String> allPromotion = customerBeanSecondUI.getAllPersonalPromotions();
            for (String promotion : allPromotion) {
                Label card = JavaFXNodeFactory.getInstance().createCardLabel2(promotion, LABEL_STYLE);
                card.setOnMouseClicked(mouseEvent -> showPromotionInfo(promotion));
                vbPromotionsInScroll.getChildren().add(card);
            }
        } catch(DBConnectionException dce){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sqle) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        } catch (RecordNotFoundException e) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), e.getMessage());
            alert.showAndWait();
        }
    }

    private void showPromotionInfo(String promotion) {
        try {
            vbPromotionInfo.getChildren().clear();
            promotionBean.setPromotionCode(promotion);
            bookAppointmentController.getPromotion(promotionBean);
            Label shop = JavaFXNodeFactory.getInstance().createLabel2(promotionBean.getShopName(), TITLE_FONT_SIZE);
            Label service = JavaFXNodeFactory.getInstance().createLabel2(promotionBean.getServiceName(), NORMAL_LABEL_FONT_SIZE);
            Label off = JavaFXNodeFactory.getInstance().createLabel2(promotionBean.getOffValue().toString() + " %off", NORMAL_LABEL_FONT_SIZE);
            Label expire = JavaFXNodeFactory.getInstance().createLabel2("Expire on: " + promotionBean.getExpireDate().toString(), NORMAL_LABEL_FONT_SIZE);
            Label promotionCode = JavaFXNodeFactory.getInstance().createLabel2("Code: " + promotionBean.getPromotionCode(), NORMAL_LABEL_FONT_SIZE);
            vbPromotionInfo.getChildren().addAll(shop, service, off, expire, promotionCode);
        } catch (RecordNotFoundException re) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), re.getMessage());
            alert.showAndWait();
        } catch(DBConnectionException dException){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException se) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        }
    }
}
