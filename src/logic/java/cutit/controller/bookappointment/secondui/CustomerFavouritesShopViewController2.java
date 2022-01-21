package cutit.controller.bookappointment.secondui;

import cutit.bean.CustomerBean;
import cutit.controller.bookappointment.BookAppointmentController;
import javafx.fxml.FXML;

public class CustomerFavouritesShopViewController2 {

    private CustomerBean customerBeanSecondUI;
    private BookAppointmentController bookAppointmentController;

    @FXML
    public void initialize(){
        System.out.println(" Starting ---> CustomerFavouritesShopViewController2");
        bookAppointmentController = new BookAppointmentController();
    }

    public void fillView(CustomerBean customerBeanSecondUI){
        this.customerBeanSecondUI = customerBeanSecondUI;
        showFavouritesShops();
    }

    private void showFavouritesShops() {

    }
}
