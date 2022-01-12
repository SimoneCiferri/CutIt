package cutit.controller.bookappointment;

import cutit.bean.CustomerBean;
import cutit.bean.FavoriteShopBean;
import cutit.bean.ShopListBean;
import cutit.bean.firstui.FavoriteShopBeanUQ;
import cutit.bean.firstui.ShopBeanUQ;
import cutit.bean.firstui.ShopListBeanFirstUI;
import cutit.decorator.ViewLayout;
import cutit.facade.Facade;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CustomerFavouritesShopViewController {

    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private ShopBeanUQ shopBeanUQ;
    private CustomerBean customerBeanFirstUI;
    private ShopListBean shopListBeanFirstUI;
    private BookAppointmentController bookAppointmentController;

    @FXML
    private VBox vbInScrollCFav;

    public boolean initialize(){
        shopListBeanFirstUI =  new ShopListBeanFirstUI();
        bookAppointmentController = new BookAppointmentController();
        vbInScrollCFav.setSpacing(15);
        System.out.println("CONTROLLER GRAFICO CUSTOMERFAVOURITESSHOPVIEWCONTROLLER");
        return true;
    }

    private void showClientFav() {
        vbInScrollCFav.getChildren().clear();
        try {
            bookAppointmentController.getFavouritesShop(shopListBeanFirstUI, customerBeanFirstUI.getcEmail());
            for (int i = 0; i < shopListBeanFirstUI.getShopBeanList().size(); i++) {
                String path = "/cutit/cutit/files/barberlogo.jpg";
                int n = i;
                HBox card = JavaFXNodeFactory.getInstance().createCard(shopListBeanFirstUI.getShopBeanList().get(i).getShopName(), shopListBeanFirstUI.getShopBeanList().get(i).getAddress(), labelStyle, path);
                //card.setOnMouseClicked((MouseEvent) -> goShopInfo(shopListBeanFirstUI.getShopBeanList().get(n).getShopName()));
                vbInScrollCFav.getChildren().add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillView(CustomerBean customerBeanFirstUI){
        this.customerBeanFirstUI = customerBeanFirstUI;
        showClientFav();
        System.out.println("Filling View from ShopBean data passedBY TopBarCustomerViewController");
    }

}
