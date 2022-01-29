package cutit.controller.bookappointment;

import cutit.bean.interfaces.CustomerBeanInterface;
import cutit.bean.interfaces.ShopListBeanInterface;
import cutit.bean.ShopListBean;
import cutit.decorator.ViewLayout1;
import cutit.decorator.concrete_decorator.ShopInfoView1;
import cutit.exception.DBConnectionException;
import cutit.exception.ExceptionText;
import cutit.exception.RecordNotFoundException;
import cutit.facade.Facade;
import cutit.factory.AlertFactory;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class CustomerFavouritesShopViewController {

    private CustomerBeanInterface customerBeanFirstUI;
    private ShopListBeanInterface shopListBeanFirstUI;
    private BookAppointmentController bookAppointmentController;
    private static final String LABEL_STYLE = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";

    @FXML
    private VBox vbInScrollCFav;

    public boolean initialize(){
        shopListBeanFirstUI =  new ShopListBean();
        bookAppointmentController = new BookAppointmentController();
        vbInScrollCFav.setSpacing(15);
        return true;
    }

    private void showClientFav() {
        vbInScrollCFav.getChildren().clear();
        try {
            bookAppointmentController.getFavouritesShop(shopListBeanFirstUI, customerBeanFirstUI.getcEmail());
            for (int i = 0; i < shopListBeanFirstUI.getShopBeanList().size(); i++) {
                HBox card;
                File im;
                if(!shopListBeanFirstUI.getShopBeanList().get(i).getImages().isEmpty()){
                    im = shopListBeanFirstUI.getShopBeanList().get(i).getImages().get(0);
                    card = JavaFXNodeFactory.getInstance().createImageCard(shopListBeanFirstUI.getShopBeanList().get(i).getShopName(), shopListBeanFirstUI.getShopBeanList().get(i).getShopAddress(), LABEL_STYLE, im);
                } else {
                    card = JavaFXNodeFactory.getInstance().createImageCard(shopListBeanFirstUI.getShopBeanList().get(i).getShopName(), shopListBeanFirstUI.getShopBeanList().get(i).getShopAddress(), LABEL_STYLE);

                }
                int n = i;
                card.setOnMouseClicked(mouseEvent -> goShopInfo(shopListBeanFirstUI.getShopBeanList().get(n).getShopName()));
                vbInScrollCFav.getChildren().add(card);
            }
        } catch (RecordNotFoundException e) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), e.getMessage());
            alert.showAndWait();
        } catch (DBConnectionException dbe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sqlException){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        } catch (IOException ioe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getIoErrorTitle(), ExceptionText.getIoErrorMessage());
            alert.showAndWait();
        }
    }

    private void goShopInfo(String shopName) {
        Facade.getInstance().decorateView(ViewLayout1.SHOPINFO);
        ShopInfoView1 view = (ShopInfoView1) Facade.getInstance().getViewMap().get(ViewLayout1.SHOPINFO);
        ShopInfoViewController viewController = (ShopInfoViewController) view.getLoadedViewController1(ViewLayout1.SHOPINFO);
        viewController.fillView(customerBeanFirstUI, shopName);
    }

    public void fillView(CustomerBeanInterface customerBeanFirstUI){
        this.customerBeanFirstUI = customerBeanFirstUI;
        showClientFav();
    }

}
