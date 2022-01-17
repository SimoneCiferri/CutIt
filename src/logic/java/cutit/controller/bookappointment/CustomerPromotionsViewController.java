package cutit.controller.bookappointment;

import cutit.bean.CustomerBean;
import cutit.bean.firstui.ShopBeanUQ;
import cutit.decorator.ViewLayout;
import cutit.facade.Facade;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CustomerPromotionsViewController {

    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private CustomerBean customerBeanFirstUI;
    private BookAppointmentController bookAppointmentController;

    @FXML
    private VBox vbInScrollCProm;

    public boolean initialize() throws IOException {
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
                vbInScrollCProm.getChildren().add(card);
            }
            Button add = new Button("Bring Friend");
            vbInScrollCProm.getChildren().add(add);
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
