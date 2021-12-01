package cutit.cutit.logic.controller.viewController;

import cutit.cutit.logic.bean.*;
import cutit.cutit.logic.controller.applController.BookAppointmentController;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.time.LocalDate;

public class CustomerBookAppointmentViewController {

    private AppointmentBean appointmentBean;
    private CustomerBean customerBean;
    private PromotionBookAppBean promotionBookAppBean;
    private ServiceBookAppBean serviceBookAppBean;
    private ShopBookAppBean shopBookAppBean;
    private BookAppointmentController bookAppointmentController;

    @FXML
    private BorderPane bpInBookApp;

    @FXML
    private Label labelDate, label830;

    @FXML
    private DatePicker dtPicker;

    @FXML
    public void initialize(){
        dtPicker.setValue(LocalDate.now());

        appointmentBean = new AppointmentBean();
        customerBean = new CustomerBean();
        promotionBookAppBean = new PromotionBookAppBean();
        serviceBookAppBean = new ServiceBookAppBean();
        shopBookAppBean = new ShopBookAppBean();
        bookAppointmentController = new BookAppointmentController();
    }

    @FXML
    public boolean backToShopInfo() {
        Facade.getInstance().decorateView(ViewLayout.SHOPINFO);
        return true;
    }

    @FXML
    public void bookAppointment() {
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        //DA RIVEDERE BENE LE FUNZIONI (RIVEDERE IL VOPC)!!
        bookAppointmentController.compileAppointment(this.appointmentBean, this.customerBean, this.promotionBookAppBean, this.serviceBookAppBean, this.shopBookAppBean);
    }

    @FXML
    public void getDirections(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
    }

    @FXML
    public void rateShop(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
    }

    @FXML
    public void addToFavourites(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
    }

    @FXML
    public void addAppToCalendar(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
    }



    @FXML
    private void set830(){
        labelDate.setText(dtPicker.getValue().toString() + "T" + "08:30:00");
        label830.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
    }

}
