package cutit.controller.bookappointment.secondui;

import cutit.bean.CustomerBean;
import cutit.bean.ShopListBean;
import cutit.bean.ShopListBeanUQ;
import cutit.controller.bookappointment.BookAppointmentController;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class HomeViewController2 {

    private CustomerBean customerBeanSecondUI;
    private ShopListBean shopListBeanSecondUI;
    private BookAppointmentController bookAppointmentController;

    @FXML
    private VBox vbInScroll;


    @FXML
    public void initialize(){
        System.out.println(" Starting ---> HomeViewController2");
        shopListBeanSecondUI = new ShopListBeanUQ();
        bookAppointmentController = new BookAppointmentController();
    }

    private void showAllShops(){
        vbInScroll.getChildren().clear();
        try {
            bookAppointmentController.getShops(shopListBeanSecondUI);
            for(int i=0;i<shopListBeanSecondUI.getShopBeanList().size();i++){
                VBox shop = JavaFXNodeFactory.getInstance().createFavouritesShopCard(shopListBeanSecondUI.getShopBeanList().get(i).getShopName(), shopListBeanSecondUI.getShopBeanList().get(i).getShopAddress());
                vbInScroll.getChildren().add(shop);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillView(CustomerBean customerBeanSecondUI){
        this.customerBeanSecondUI = customerBeanSecondUI;
        showAllShops();
    }
}
