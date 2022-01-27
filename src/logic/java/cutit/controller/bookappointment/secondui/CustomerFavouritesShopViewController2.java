package cutit.controller.bookappointment.secondui;

import cutit.bean.CustomerBean;
import cutit.bean.ShopBeanInterface;
import cutit.bean.ShopListBean;
import cutit.bean.ShopListBeanUQ;
import cutit.controller.bookappointment.BookAppointmentController;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.List;

public class CustomerFavouritesShopViewController2 {

    private CustomerBean customerBeanSecondUI;
    private ShopListBean shopListBean;
    private BookAppointmentController bookAppointmentController;

    @FXML
    private VBox vbInScrollCFav;

    @FXML
    public void initialize(){
        shopListBean = new ShopListBeanUQ();
        bookAppointmentController = new BookAppointmentController();
    }

    public void fillView(CustomerBean customerBeanSecondUI){
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

                vbInScrollCFav.getChildren().add(shop);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
