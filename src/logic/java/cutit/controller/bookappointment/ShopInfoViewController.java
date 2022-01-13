package cutit.controller.bookappointment;

import cutit.bean.CustomerBean;
import cutit.bean.firstui.ShopBeanUQ;
import cutit.decorator.ViewLayout;
import cutit.decorator.concreteDecorator.ClientBookAppointmentView;
import cutit.facade.Facade;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShopInfoViewController {

    private ShopBeanUQ shopBeanUQ;
    private CustomerBean customerBeanFirstUI;
    private BookAppointmentController bookAppointmentController;
    private List<ImageView> ivList = new ArrayList<>();

    @FXML
    private Label lShopName, lShopPhone;

    @FXML
    private VBox vboxShopInfo;

    @FXML
    private ImageView ivShop1, ivShop2, ivShop3, ivShop4, ivShop5, ivShop6, ivShop7, ivShop8;


    public boolean initialize() throws IOException {
        System.out.println("CONTROLLER GRAFICO SHOPINFOVIEWCONTROLLER");
        bookAppointmentController = new BookAppointmentController();
        ivList.add(ivShop1);
        ivList.add(ivShop2);
        ivList.add(ivShop3);
        ivList.add(ivShop4);
        ivList.add(ivShop5);
        ivList.add(ivShop6);
        ivList.add(ivShop7);
        ivList.add(ivShop8);
        return true;
    }

    public void showShopInfo(){
        vboxShopInfo.getChildren().clear();
        Label lblShopName = JavaFXNodeFactory.getInstance().createLabel(shopBeanUQ.getShopName(), 30.0);
        //ImageView del titolo da mettere
        Label lblPhone = JavaFXNodeFactory.getInstance().createLabel(shopBeanUQ.getPhoneNumber(), 12.0);
        vboxShopInfo.getChildren().addAll(lblShopName, lblPhone);
        /*for(int i = 0; i< shopBeanUQ.getImages().size(); i++){
            System.out.println("immagine");
            ivList.get(i).setImage(new Image(String.valueOf(shopBeanUQ.getImages().get(i).toURI())));
        }*/
    }

    @FXML
    public boolean bookAppointment() {
        Facade.getInstance().decorateView(ViewLayout.CLIENTBOOKAPPOINTMENT);
        ClientBookAppointmentView view = (ClientBookAppointmentView) Facade.getInstance().getViewMap().get(ViewLayout.CLIENTBOOKAPPOINTMENT);
        CustomerBookAppointmentViewController viewController = (CustomerBookAppointmentViewController) view.getLoadedViewController(ViewLayout.CLIENTBOOKAPPOINTMENT);
        viewController.fillView(customerBeanFirstUI, shopBeanUQ);
        return true;
    }

    @FXML
    public boolean backToHome(){
        Facade.getInstance().decorateView(ViewLayout.HOME);
        return true;
    }

    @FXML
    public boolean goToDirections(){
        Facade.getInstance().decorateView(ViewLayout.GMAPS);
        return true;
    }

    public void fillView(CustomerBean customerBeanFirstUI, String shopName){
        try {
            this.customerBeanFirstUI = customerBeanFirstUI;
            shopBeanUQ = bookAppointmentController.getShop(shopName);
            showShopInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
