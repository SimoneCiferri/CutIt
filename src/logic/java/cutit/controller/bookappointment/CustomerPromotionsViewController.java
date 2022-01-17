package cutit.controller.bookappointment;

import cutit.bean.CustomerBean;
import cutit.bean.PromotionBean;
import cutit.bean.firstui.PromotionBeanUQ;
import cutit.decorator.ViewLayout;
import cutit.decorator.concreteDecorator.CustomerPromotionInfoView;
import cutit.decorator.concreteDecorator.CustomerPromotionsView;
import cutit.exception.RecordNotFoundException;
import cutit.facade.Facade;
import cutit.factory.AlertFactory;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import javax.swing.text.View;
import java.io.IOException;

public class CustomerPromotionsViewController {

    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private CustomerBean customerBeanFirstUI;
    private PromotionBean promotionBeanUQ;
    private BookAppointmentController bookAppointmentController;

    @FXML
    private VBox vbInScrollCProm;

    public boolean initialize() throws IOException {
        promotionBeanUQ = new PromotionBeanUQ();
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
                Label card = JavaFXNodeFactory.getInstance().createCardLabel(promotion, labelStyle);
                card.setOnMouseClicked((MouseEvent) -> showPromotion(promotion));
                vbInScrollCProm.getChildren().add(card);
            }
            Button add = new Button("Bring Friend");
            vbInScrollCProm.getChildren().add(add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showPromotion(String promotion) {
        try {
            promotionBeanUQ.setPromotionCode(promotion);
            bookAppointmentController.getPromotion(promotionBeanUQ);
            Facade.getInstance().decorateView(ViewLayout.CUSTOMERPROMOTIONINFO);
            CustomerPromotionInfoView view = (CustomerPromotionInfoView) Facade.getInstance().getViewMap().get(ViewLayout.CUSTOMERPROMOTIONINFO);
            CustomerPromotionInfoViewController viewController = (CustomerPromotionInfoViewController) view.getLoadedViewController(ViewLayout.CUSTOMERPROMOTIONINFO);
            viewController.fillView(customerBeanFirstUI, promotionBeanUQ);
        } catch (RecordNotFoundException rne) {
            AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, "Information", rne.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillView(CustomerBean customerBeanFirstUI){
        this.customerBeanFirstUI = customerBeanFirstUI;
        System.out.println("Filling View from ShopBean data passedBY TopBarCustomerViewController");
        showClientProm();
    }


}
