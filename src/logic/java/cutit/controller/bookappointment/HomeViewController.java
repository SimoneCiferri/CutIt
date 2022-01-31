package cutit.controller.bookappointment;

import cutit.bean.*;
import cutit.bean.interfaces.CustomerBeanInterface;
import cutit.bean.interfaces.ShopBeanInterface;
import cutit.bean.interfaces.ShopListBeanInterface;
import cutit.decorator.decorator1.ViewLayout1;
import cutit.decorator.decorator1.concrete_decorator.ShopInfoView1;
import cutit.exception.DBConnectionException;
import cutit.utils.ExceptionText;
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

    private CustomerBeanInterface customerBeanFirstUI;
    private ShopListBeanInterface shopListBeanFirstUI;
    private BookAppointmentController bookAppointmentController;
    private static final String LABEL_STYLE = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";

    @FXML
    private TextField tfSearchName;

    @FXML
    private TextField tfSearchPlace;

    @FXML
    private VBox vbInScroll;

    public boolean initialize() throws IOException {
        shopListBeanFirstUI = new ShopListBean();
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
        } catch (DBConnectionException dbe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sql){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        } catch (IOException ioe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getIoErrorTitle(), ExceptionText.getIoErrorMessage());
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
                filterByName();
            } else if(!Objects.equals(tfSearchPlace.getText(), "")){
                filterByPlace();
            }
        }
    }

    private void filterByName(){
        for(int i = 0; i< shopListBeanFirstUI.getShopBeanList().size(); i++){
            if(shopListBeanFirstUI.getShopBeanList().get(i).getShopName().contains(tfSearchName.getText())){
                HBox card = createBox(shopListBeanFirstUI.getShopBeanList().get(i));
                vbInScroll.getChildren().add(card);
            }
        }
    }

    private void filterByPlace() {
        for (int i = 0; i < shopListBeanFirstUI.getShopBeanList().size(); i++) {
            try {
                if (shopListBeanFirstUI.getShopBeanList().get(i).getShopAddress().contains(tfSearchPlace.getText())) {
                    HBox card = createBox(shopListBeanFirstUI.getShopBeanList().get(i));
                    vbInScroll.getChildren().add(card);
                }
            } catch (NullPointerException ignored){
                //Next step
            }
        }
    }

    public void fillView(CustomerBeanInterface customerBeanFirstUI){
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
