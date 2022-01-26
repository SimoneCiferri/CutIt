package cutit.controller.bookappointment;

import cutit.bean.*;
import cutit.decorator.ViewLayout1;
import cutit.decorator.concrete_decorator.ShopInfoView1;
import cutit.exception.DBConnectionException;
import cutit.facade.Facade;
import cutit.factory.AlertFactory;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class HomeViewController {

    private CustomerBean customerBeanFirstUI;
    private ShopListBean shopListBeanFirstUI;
    private BookAppointmentController bookAppointmentController;
    private static final String LABEL_STYLE = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private static final String CONNECTION_ERROR_TITLE = "Connection error";
    private static final String IO_ERROR_TITLE = "Error";
    private static final String CONNECTION_ERROR_MESSAGE = "Please check your internet connection. If problem persists try to restart the application.";
    private static final String SQL_ERROR_MESSAGE = "Please check your internet connection. If problem persists contact us at cutitapp@support.com.";
    private static final String IO_ERROR_MESSAGE = "Impossible to load some files. If problem persists try again later or contact us at cutitapp@support.com";

    @FXML
    private TextField tfSearchName;

    @FXML
    private TextField tfSearchPlace;

    @FXML
    private VBox vbInScroll;

    public boolean initialize() throws IOException {
        shopListBeanFirstUI = new ShopListBeanUQ();
        bookAppointmentController = new BookAppointmentController();
        vbInScroll.setSpacing(15);
        return true;
    }

    private void showShops(){
        vbInScroll.getChildren().clear();
        try {
            bookAppointmentController.getShops(shopListBeanFirstUI);
            for(int i = 0; i< shopListBeanFirstUI.getShopBeanList().size(); i++){
                HBox card;
                if(!shopListBeanFirstUI.getShopBeanList().get(i).getImages().isEmpty()){
                    File im = shopListBeanFirstUI.getShopBeanList().get(i).getImages().get(0);
                    card = JavaFXNodeFactory.getInstance().createImageCard(shopListBeanFirstUI.getShopBeanList().get(i).getShopName(), shopListBeanFirstUI.getShopBeanList().get(i).getShopAddress(), LABEL_STYLE, im);
                } else {
                    card = JavaFXNodeFactory.getInstance().createImageCard(shopListBeanFirstUI.getShopBeanList().get(i).getShopName(), shopListBeanFirstUI.getShopBeanList().get(i).getShopAddress(), LABEL_STYLE);
                }
                int n = i;
                card.setOnMouseClicked(mouseEvent -> goShopInfo(shopListBeanFirstUI.getShopBeanList().get(n).getShopName()));
                vbInScroll.getChildren().add(card);
            }
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

    public void goShopInfo(String shopName){
        Facade.getInstance().decorateView(ViewLayout1.SHOPINFO);
        ShopInfoView1 view = (ShopInfoView1) Facade.getInstance().getViewMap().get(ViewLayout1.SHOPINFO);
        ShopInfoViewController viewController = (ShopInfoViewController) view.getLoadedViewController1(ViewLayout1.SHOPINFO);
        viewController.fillView(customerBeanFirstUI, shopName);
    }

    @FXML
    private void filterShops(){
        if(Objects.equals(tfSearchName.getText(), "") && Objects.equals(tfSearchPlace.getText(), "")){
            showShops();
        } else {
            vbInScroll.getChildren().clear();
            if(!Objects.equals(tfSearchName.getText(), "")){
                for(int i = 0; i< shopListBeanFirstUI.getShopBeanList().size(); i++){
                    if(shopListBeanFirstUI.getShopBeanList().get(i).getShopName().contains(tfSearchName.getText())){
                        HBox card = createBox(shopListBeanFirstUI.getShopBeanList().get(i));
                        vbInScroll.getChildren().add(card);
                    }
                }
            } else if(!Objects.equals(tfSearchPlace.getText(), "")){
                for(int i = 0; i< shopListBeanFirstUI.getShopBeanList().size(); i++){
                    if(shopListBeanFirstUI.getShopBeanList().get(i).getShopAddress().contains(tfSearchPlace.getText())){
                        HBox card = createBox(shopListBeanFirstUI.getShopBeanList().get(i));
                        vbInScroll.getChildren().add(card);
                    }
                }
            }
        }
    }

    public void fillView(CustomerBean customerBeanFirstUI){
        this.customerBeanFirstUI = customerBeanFirstUI;
        showShops();
    }

    private HBox createBox(ShopBeanInterface shopBean){
        HBox card;
        if(!shopBean.getImages().isEmpty()){
            File im = shopBean.getImages().get(0);
            card = JavaFXNodeFactory.getInstance().createImageCard(shopBean.getShopName(), shopBean.getShopAddress(), LABEL_STYLE, im);
        } else {
            card = JavaFXNodeFactory.getInstance().createImageCard(shopBean.getShopName(), shopBean.getShopAddress(), LABEL_STYLE);
        }
        card.setOnMouseClicked(mouseEvent -> goShopInfo(shopBean.getShopName()));
        return card;

    }

}
