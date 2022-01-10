package cutit.controller.bookappointment;

import cutit.bean.ShopBean;
import cutit.database.dao.ShopDAO;
import cutit.decorator.ViewLayout;
import cutit.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.IOException;

public class ShopInfoViewController {

    private ShopBean shopBean;
    private BookAppointmentController bookAppointmentController;

    @FXML
    private Label lShopName, lShopPhone;

    @FXML
    private ImageView ivShop1, ivShop2, ivShop3, ivShop4, ivShop5, ivShop6, ivShop7, ivShop8;


    public boolean initialize() throws IOException {
        System.out.println("CONTROLLER GRAFICO SHOPINFOVIEWCONTROLLER");
        bookAppointmentController = new BookAppointmentController();
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
            this.shopBean = bookAppointmentController.getShop(shopName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
