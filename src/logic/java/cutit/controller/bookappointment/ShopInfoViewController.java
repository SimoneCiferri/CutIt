package cutit.controller.bookappointment;

import cutit.bean.firstui.ShopBeanUQ;
import cutit.decorator.ViewLayout;
import cutit.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShopInfoViewController {

    private ShopBeanUQ shopBeanUQ;
    private BookAppointmentController bookAppointmentController;
    private List<ImageView> ivList = new ArrayList<>();

    @FXML
    private Label lShopName, lShopPhone;

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

    @FXML
    public boolean bookAppointment() {
        Facade.getInstance().decorateView(ViewLayout.CLIENTBOOKAPPOINTMENT);
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

    public void fillView(String shopName){
        try {
            this.shopBeanUQ = bookAppointmentController.getShop(shopName);
            lShopName.setText(shopBeanUQ.getShopName());
            lShopPhone.setText(shopBeanUQ.getPhoneNumber());
            for(int i = 0; i< shopBeanUQ.getImages().size(); i++){
                System.out.println("immagine");
                ivList.get(i).setImage(new Image(String.valueOf(shopBeanUQ.getImages().get(i).toURI())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}