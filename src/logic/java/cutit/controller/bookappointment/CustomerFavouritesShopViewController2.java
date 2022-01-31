package cutit.controller.bookappointment;

import cutit.bean.interfaces.CustomerBeanInterface;
import cutit.bean.interfaces.ShopBeanInterface;
import cutit.bean.interfaces.ShopListBeanInterface;
import cutit.bean.ShopListBean;
import cutit.decorator.decorator2.ViewLayout2;
import cutit.decorator.decorator2.concrete_decorator2.HomeView2;
import cutit.exception.DBConnectionException;
import cutit.utils.ExceptionText;
import cutit.exception.RecordNotFoundException;
import cutit.facade.Facade2;
import cutit.factory.AlertFactory;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerFavouritesShopViewController2 {

    private CustomerBeanInterface customerBeanSecondUI;
    private ShopListBeanInterface shopListBean;
    private BookAppointmentController bookAppointmentController;

    @FXML
    private VBox vbInScrollCFav;

    @FXML
    public void initialize(){
        shopListBean = new ShopListBean();
        bookAppointmentController = new BookAppointmentController();
    }

    public void fillView(CustomerBeanInterface customerBeanSecondUI){
        this.customerBeanSecondUI = customerBeanSecondUI;
        showFavouritesShops();
    }

    private void showFavouritesShops() {
        vbInScrollCFav.getChildren().clear();
        try {
            bookAppointmentController.getFavouritesShop(shopListBean, customerBeanSecondUI.getcEmail());
            List<ShopBeanInterface> shopList = shopListBean.getShopBeanList();
            for (ShopBeanInterface shopBeanInterface : shopList) {
                VBox shop = JavaFXNodeFactory.getInstance().createFavouritesShopCard(shopBeanInterface.getShopName(), shopBeanInterface.getShopAddress());
                shop.setOnMouseClicked(mouseEvent -> goShop(shopBeanInterface.getShopName()));
                vbInScrollCFav.getChildren().add(shop);
            }
        } catch (RecordNotFoundException nfE) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), nfE.getMessage());
            alert.showAndWait();
        } catch (DBConnectionException db) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sExc){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        } catch (IOException ioe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getIoErrorTitle(), ExceptionText.getIoErrorMessage());
            alert.showAndWait();
        }
    }

    private void goShop(String shopName){
        Facade2.getInstance().decorateView2(ViewLayout2.HOME2);
        HomeView2 view = (HomeView2) Facade2.getInstance().getViewMap().get(ViewLayout2.HOME2);
        HomeViewController2 viewController = (HomeViewController2) view.getLoadedViewController2(ViewLayout2.HOME2);
        viewController.fillView(customerBeanSecondUI);
        viewController.showShopInfo(shopName);
    }
}
