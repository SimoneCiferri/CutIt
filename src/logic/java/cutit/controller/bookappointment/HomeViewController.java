package cutit.controller.bookappointment;

import cutit.bean.CustomerBean;
import cutit.bean.ShopListBean;
import cutit.bean.firstui.ShopListBeanFirstUI;
import cutit.decorator.ViewLayout;
import cutit.decorator.concreteDecorator.ShopInfoView;
import cutit.facade.Facade;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.layout.*;

import java.io.IOException;

public class HomeViewController {

    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private CustomerBean customerBeanFirstUI;
    private ShopListBean shopListBeanFirstUI;
    private BookAppointmentController bookAppointmentController;

    @FXML
    private VBox vbInScroll;

    public boolean initialize() throws IOException {
        shopListBeanFirstUI = new ShopListBeanFirstUI();
        bookAppointmentController = new BookAppointmentController();
        vbInScroll.setSpacing(15);
        System.out.println("CONTROLLER GRAFICO HOMEVIEWCONTROLLER");
        return true;
    }

    private void showShops(){
        vbInScroll.getChildren().clear();
        try {
            bookAppointmentController.getShops(shopListBeanFirstUI);
            for(int i = 0; i< shopListBeanFirstUI.getShopBeanList().size(); i++){
                String path = "/cutit/cutit/files/barberlogo.jpg";
                int n = i;
                HBox card = JavaFXNodeFactory.getInstance().createCard(shopListBeanFirstUI.getShopBeanList().get(i).getShopName(), shopListBeanFirstUI.getShopBeanList().get(i).getAddress(), labelStyle, path);
                card.setOnMouseClicked((MouseEvent) -> goShopInfo(shopListBeanFirstUI.getShopBeanList().get(n).getShopName()));
                vbInScroll.getChildren().add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goShopInfo(String shopName){
        Facade.getInstance().decorateView(ViewLayout.SHOPINFO);
        ShopInfoView view = (ShopInfoView) Facade.getInstance().getViewMap().get(ViewLayout.SHOPINFO);
        ShopInfoViewController viewController = (ShopInfoViewController) view.getLoadedViewController(ViewLayout.SHOPINFO);
        viewController.fillView(shopName);

    }

    public void fillView(CustomerBean customerBeanFirstUI){
        this.customerBeanFirstUI = customerBeanFirstUI;
        System.out.println("Filling View from ShopBean data passedBY TopBarCustomerViewController");
        showShops();
    }

}
