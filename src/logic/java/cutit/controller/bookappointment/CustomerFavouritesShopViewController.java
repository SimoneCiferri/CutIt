package cutit.controller.bookappointment;

import cutit.bean.CustomerBean;
import cutit.bean.ShopListBean;
import cutit.bean.firstui.ShopBeanUQ;
import cutit.bean.firstui.ShopListBeanFirstUI;
import cutit.decorator.ViewLayout;
import cutit.decorator.concreteDecorator.ShopInfoView;
import cutit.facade.Facade;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;

public class CustomerFavouritesShopViewController {

    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private static final String defaultLogo = "/cutit/cutit/files/barberlogo.jpg";
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
                File im;
                if(!shopListBeanFirstUI.getShopBeanList().get(i).getImages().isEmpty()){
                    im = shopListBeanFirstUI.getShopBeanList().get(i).getImages().get(0);
                } else {
                    im = new File(defaultLogo);
                }
                int n = i;
                HBox card = JavaFXNodeFactory.getInstance().createImageCard(shopListBeanFirstUI.getShopBeanList().get(i).getShopName(), shopListBeanFirstUI.getShopBeanList().get(i).getAddress(), labelStyle, im);
                card.setOnMouseClicked((MouseEvent) -> goShopInfo(shopListBeanFirstUI.getShopBeanList().get(n).getShopName()));
                vbInScrollCFav.getChildren().add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goShopInfo(String shopName) {
        Facade.getInstance().decorateView(ViewLayout.SHOPINFO);
        ShopInfoView view = (ShopInfoView) Facade.getInstance().getViewMap().get(ViewLayout.SHOPINFO);
        ShopInfoViewController viewController = (ShopInfoViewController) view.getLoadedViewController(ViewLayout.SHOPINFO);
        viewController.fillView(customerBeanFirstUI, shopName);
    }

    public void fillView(CustomerBean customerBeanFirstUI){
        this.customerBeanFirstUI = customerBeanFirstUI;
        showClientFav();
        System.out.println("Filling View from ShopBean data passedBY TopBarCustomerViewController");
    }

}
