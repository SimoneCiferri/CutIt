package cutit.controller.bookappointment;

import cutit.bean.interfaces.CustomerBeanInterface;
import cutit.bean.interfaces.PromotionBeanInterface;
import cutit.bean.PromotionBean;
import cutit.decorator.decorator1.ViewLayout1;
import cutit.decorator.decorator1.concrete_decorator.CustomerPromotionInfoView1;
import cutit.exception.DBConnectionException;
import cutit.exception.RecordNotFoundException;
import cutit.facade.Facade;
import cutit.factory.AlertFactory;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerPromotionsViewController {

    private CustomerBeanInterface customerBeanFirstUI;
    private PromotionBeanInterface promotionBeanUQ;
    private BookAppointmentController bookAppointmentController;
    private static final String LABEL_STYLE = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private static final String CONNECTION_ERROR_TITLE = "Connection error";
    private static final String WARNING_TITLE = "Warning";
    private static final String CONNECTION_ERROR_MESSAGE = "Please check your internet connection. If problem persists try to restart the application.";
    private static final String SQL_ERROR_MESSAGE = "Please check your internet connection. If problem persists contact us at cutitapp@support.com.";

    @FXML
    private VBox vbInScrollCProm;

    public boolean initialize() throws IOException {
        promotionBeanUQ = new PromotionBean();
        bookAppointmentController = new BookAppointmentController();
        vbInScrollCProm.setSpacing(15);
        return true;
    }

    private void showClientProm() {
        vbInScrollCProm.getChildren().clear();
        try {
            bookAppointmentController.getPersonalPromotions(customerBeanFirstUI);
            for(int i=0;i<customerBeanFirstUI.getAllPersonalPromotions().size();i++){
                String promotion = customerBeanFirstUI.getAllPersonalPromotions().get(i);
                Label card = JavaFXNodeFactory.getInstance().createCardLabel(promotion, LABEL_STYLE);
                card.setOnMouseClicked(mouseEvent -> showPromotion(promotion));
                vbInScrollCProm.getChildren().add(card);
            }
        } catch(DBConnectionException dbe){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, CONNECTION_ERROR_MESSAGE);
            alert.showAndWait();
        } catch (SQLException sqle) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, SQL_ERROR_MESSAGE);
            alert.showAndWait();
        } catch (RecordNotFoundException e) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, WARNING_TITLE, e.getMessage());
            alert.showAndWait();
        }
    }

    private void showPromotion(String promotion) {
        try {
            promotionBeanUQ.setPromotionCode(promotion);
            bookAppointmentController.getPromotion(promotionBeanUQ);
            Facade.getInstance().decorateView(ViewLayout1.CUSTOMERPROMOTIONINFO);
            CustomerPromotionInfoView1 view = (CustomerPromotionInfoView1) Facade.getInstance().getViewMap().get(ViewLayout1.CUSTOMERPROMOTIONINFO);
            CustomerPromotionInfoViewController viewController = (CustomerPromotionInfoViewController) view.getLoadedViewController1(ViewLayout1.CUSTOMERPROMOTIONINFO);
            viewController.fillView(customerBeanFirstUI, promotionBeanUQ);
        } catch (RecordNotFoundException e) {
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

    public void fillView(CustomerBeanInterface customerBeanFirstUI){
        this.customerBeanFirstUI = customerBeanFirstUI;
        showClientProm();
    }


}
