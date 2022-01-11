package cutit.controller.rateshop;

import cutit.bean.RateShopBean;
import cutit.bean.firstui.RateShopBeanUQ;
import cutit.controller.bookappointment.BookAppointmentController;
import cutit.decorator.ViewLayout;
import cutit.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;

public class CustomerRateShopViewController {

    private final String star = "/cutit/cutit/files/star.png";
    private BookAppointmentController bookAppointmentController;
    private RateShopBean rateShopBeanUQ;

    @FXML
    private ImageView ivStar1, ivStar2, ivStar3, ivStar4, ivStar5;

    public boolean initialize() throws IOException {
        bookAppointmentController = new BookAppointmentController();
        rateShopBeanUQ = new RateShopBeanUQ();
        URL starURL = getClass().getResource(star);
        if(starURL != null){
            Image image = new Image(starURL.toString());
            ivStar1.setImage(image);
            ivStar2.setImage(image);
            ivStar3.setImage(image);
            ivStar4.setImage(image);
            ivStar5.setImage(image);
        }
        System.out.println("CONTROLLER GRAFICO CUSTOMERRATESHOPVIEWCONTROLLER");
        return true;
    }

    @FXML
    public void rate5(){
        System.out.println("            5 star pressed");
    }

    @FXML
    public boolean rateShop(){
        if(bookAppointmentController.rateShop(this.rateShopBeanUQ)) {
            Facade.getInstance().decorateView(ViewLayout.HOME);
            return true;
        }
        return false;
    }

}