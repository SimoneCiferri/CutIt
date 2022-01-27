package cutit.controller.bookappointment.secondui;

import cutit.bean.*;
import cutit.controller.bookappointment.BookAppointmentController;
import cutit.exception.DBConnectionException;
import cutit.exception.RecordNotFoundException;
import cutit.factory.AlertFactory;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;

public class HomeViewController2 {

    private ShopBeanInterface shopBean;
    private ShopListBean shopListBeanSecondUI;
    private BookAppointmentController bookAppointmentController;
    private static final String CONNECTION_ERROR_TITLE = "Connection error";
    private static final String WARNING_TITLE = "Warning";
    private static final String IO_ERROR_TITLE = "Error";
    private static final String CONNECTION_ERROR_MESSAGE = "Please check your internet connection. If problem persists try to restart the application.";
    private static final String SQL_ERROR_MESSAGE = "Please check your internet connection. If problem persists contact us at cutitapp@support.com.";
    private static final String IO_ERROR_MESSAGE = "Impossible to load some files. If problem persists try again later or contact us at cutitapp@support.com";

    @FXML
    private VBox vbAllShopsInScroll;

    @FXML
    private Label lblShopNameInScroll;


    @FXML
    public void initialize(){
        shopBean = new ShopBean();
        shopListBeanSecondUI = new ShopListBeanUQ();
        bookAppointmentController = new BookAppointmentController();
    }

    private void showAllShops(){
        vbAllShopsInScroll.getChildren().clear();
        try {
            bookAppointmentController.getShops(shopListBeanSecondUI);
            for(int i=0;i<shopListBeanSecondUI.getShopBeanList().size();i++){
                VBox shop = JavaFXNodeFactory.getInstance().createFavouritesShopCard(shopListBeanSecondUI.getShopBeanList().get(i).getShopName(), shopListBeanSecondUI.getShopBeanList().get(i).getShopAddress());
                int n = i;
                shop.setOnMouseClicked(mouseEvent -> showShopInfo(shopListBeanSecondUI.getShopBeanList().get(n).getShopName()));
                vbAllShopsInScroll.getChildren().add(shop);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showShopInfo(String shopName) {
        try {
            bookAppointmentController.getShop(shopBean, shopName);
            showShop();
        } catch (RecordNotFoundException e) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, WARNING_TITLE, e.getMessage());
            alert.showAndWait();
        } catch(DBConnectionException dbe){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, CONNECTION_ERROR_MESSAGE);
            alert.showAndWait();
        } catch (SQLException sqle) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, SQL_ERROR_MESSAGE);
            alert.showAndWait();
        } catch (IOException ioe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, IO_ERROR_TITLE, IO_ERROR_MESSAGE);
            alert.showAndWait();
        }
    }

    private void showShop() {
        lblShopNameInScroll.setText(shopBean.getShopName());
    }

    public void fillView(){
        showAllShops();
    }
}
