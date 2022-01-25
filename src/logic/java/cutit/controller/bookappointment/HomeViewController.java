package cutit.controller.bookappointment;

import cutit.bean.CustomerBean;
import cutit.bean.ShopListBean;
import cutit.bean.ShopListBeanUQ;
import cutit.decorator.ViewLayout1;
import cutit.decorator.concrete_decorator.ShopInfoView1;
import cutit.facade.Facade;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class HomeViewController {

    private static final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private CustomerBean customerBeanFirstUI;
    private ShopListBean shopListBeanFirstUI;
    private BookAppointmentController bookAppointmentController;

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
                    card = JavaFXNodeFactory.getInstance().createImageCard(shopListBeanFirstUI.getShopBeanList().get(i).getShopName(), shopListBeanFirstUI.getShopBeanList().get(i).getShopAddress(), labelStyle, im);
                } else {
                    card = JavaFXNodeFactory.getInstance().createImageCard(shopListBeanFirstUI.getShopBeanList().get(i).getShopName(), shopListBeanFirstUI.getShopBeanList().get(i).getShopAddress(), labelStyle);
                }
                int n = i;
                card.setOnMouseClicked(MouseEvent -> goShopInfo(shopListBeanFirstUI.getShopBeanList().get(n).getShopName()));
                vbInScroll.getChildren().add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            if(!Objects.equals(tfSearchName.getText(), "")){
                vbInScroll.getChildren().clear();
                for(int i = 0; i< shopListBeanFirstUI.getShopBeanList().size(); i++){
                    if(shopListBeanFirstUI.getShopBeanList().get(i).getShopName().contains(tfSearchName.getText())){
                        HBox card;
                        if(!shopListBeanFirstUI.getShopBeanList().get(i).getImages().isEmpty()){
                            File im = shopListBeanFirstUI.getShopBeanList().get(i).getImages().get(0);
                            card = JavaFXNodeFactory.getInstance().createImageCard(shopListBeanFirstUI.getShopBeanList().get(i).getShopName(), shopListBeanFirstUI.getShopBeanList().get(i).getShopAddress(), labelStyle, im);
                        } else {
                            card = JavaFXNodeFactory.getInstance().createImageCard(shopListBeanFirstUI.getShopBeanList().get(i).getShopName(), shopListBeanFirstUI.getShopBeanList().get(i).getShopAddress(), labelStyle);
                        }
                        int n = i;
                        card.setOnMouseClicked(mouseEvent -> goShopInfo(shopListBeanFirstUI.getShopBeanList().get(n).getShopName()));
                        vbInScroll.getChildren().add(card);
                    }
                }
            } else if(!Objects.equals(tfSearchPlace.getText(), "")){
                vbInScroll.getChildren().clear();
                for(int i = 0; i< shopListBeanFirstUI.getShopBeanList().size(); i++){
                    if(shopListBeanFirstUI.getShopBeanList().get(i).getShopAddress().contains(tfSearchPlace.getText())){
                        HBox card;
                        if(!shopListBeanFirstUI.getShopBeanList().get(i).getImages().isEmpty()){
                            File im = shopListBeanFirstUI.getShopBeanList().get(i).getImages().get(0);
                            card = JavaFXNodeFactory.getInstance().createImageCard(shopListBeanFirstUI.getShopBeanList().get(i).getShopName(), shopListBeanFirstUI.getShopBeanList().get(i).getShopAddress(), labelStyle, im);
                        } else {
                            card = JavaFXNodeFactory.getInstance().createImageCard(shopListBeanFirstUI.getShopBeanList().get(i).getShopName(), shopListBeanFirstUI.getShopBeanList().get(i).getShopAddress(), labelStyle);
                        }
                        int n = i;
                        card.setOnMouseClicked(mouseEvent -> goShopInfo(shopListBeanFirstUI.getShopBeanList().get(n).getShopName()));
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

}
