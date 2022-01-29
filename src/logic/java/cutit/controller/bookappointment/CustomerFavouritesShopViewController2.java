package cutit.controller.bookappointment;

import cutit.bean.interfaces.CustomerBeanInterface;
import cutit.bean.interfaces.ShopBeanInterface;
import cutit.bean.interfaces.ShopListBeanInterface;
import cutit.bean.ShopListBean;
import cutit.controller.bookappointment.BookAppointmentController;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

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

                vbInScrollCFav.getChildren().add(shop);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
