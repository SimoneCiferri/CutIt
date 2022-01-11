package cutit.controller.bookappointment;

import cutit.bean.PayOnlineBean;
import cutit.bean.RateShopBean;
import cutit.bean.firstui.AppointmentBeanFirstUI;
import cutit.bean.firstui.RateShopBeanUQ;
import cutit.decorator.ViewLayout;
import cutit.facade.Facade;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.time.LocalDate;

public class CustomerBookAppointmentViewController {

    private AppointmentBeanFirstUI appointmentBeanFirstUI;
    private BookAppointmentController bookAppointmentController;
    private RateShopBean rateShopBean;

    @FXML
    private BorderPane bpInBookApp;

    @FXML
    private Label labelDate, label830, labelService;

    @FXML
    private DatePicker dtPicker;

    @FXML
    private HBox hBoxTop, hBoxCentre, hBoxBottom;

    @FXML
    public void initialize(){
        dtPicker.setValue(LocalDate.now());
        appointmentBeanFirstUI = new AppointmentBeanFirstUI();
        rateShopBean = new RateShopBeanUQ();
        bookAppointmentController = new BookAppointmentController();
        System.out.println("CONTROLLER GRAFICO CUSTOMERBOOKAPPOINTMENTVIEWCONTROLLER");
    }

    @FXML
    public boolean backToShopInfo() {
        Facade.getInstance().decorateView(ViewLayout.SHOPINFO);
        return true;
    }

    @FXML
    public void bookAppointment() {
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        //DA RIVEDERE BENE LE FUNZIONI (RIVEDERE IL VOPC)!
        if(bookAppointmentController.compileAppointment(this.appointmentBeanFirstUI)){
            showPayedAndBooked();
        }
    }

    @FXML
    private void set830(){
        labelDate.setText(dtPicker.getValue().toString() + "T" + "08:30:00");
        label830.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
    }

    private void showPayedAndBooked() {
        VBox cont = new VBox();
        cont.setPrefWidth(1024);
        cont.setPrefHeight(713);
        cont.setAlignment(Pos.CENTER);
        cont.setSpacing(10);
        Label l = new Label("Appointment Booked!!");
        l.setTextFill(Color.WHITE);
        l.setFont(labelDate.getFont());
        Label l1 = new Label("Go to the Appointments Section to review your booking! ");
        l1.setTextFill(Color.WHITE);
        l1.setFont(labelService.getFont());
        HBox hCont = new HBox();
        hCont.setPrefWidth(700);
        hCont.setPrefHeight(125);
        hCont.setAlignment(Pos.CENTER);
        hCont.setSpacing(55);
        Button btnGetDir = new Button("Get Directions");
        btnGetDir.setPrefHeight(55);
        btnGetDir.setOnMouseClicked((MouseEvent) -> getDirections());
        Button btnAddToFav = new Button("Add Shop To Favourites");
        btnAddToFav.setPrefHeight(55);
        btnAddToFav.setOnMouseClicked((MouseEvent) -> addToFavourites());
        Button btnAddToCalendar = new Button("Add Appointment To Calendar");
        btnAddToCalendar.setPrefHeight(55);
        btnAddToCalendar.setOnMouseClicked((MouseEvent) -> addAppToCalendar());
        Button btnRateShop = new Button("Rate Shop");
        btnRateShop.setPrefHeight(55);
        btnRateShop.setOnMouseClicked((MouseEvent) -> rateShop());
        hCont.getChildren().addAll(btnGetDir, btnAddToFav, btnAddToCalendar, btnRateShop);
        cont.getChildren().addAll(l, l1, hCont);
        bpInBookApp.getChildren().clear();
        bpInBookApp.setCenter(cont);
    }

    private void getDirections(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
    }

    private void rateShop(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        Facade.getInstance().decorateView(ViewLayout.CUSTOMERRATESHOP);
    }

    private void addToFavourites(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        if(bookAppointmentController.addShopToFavourites("shopName")){
            Facade.getInstance().decorateView(ViewLayout.FAVSHOP);
        }


    }

    private void addAppToCalendar(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        if(bookAppointmentController.addToCalendar(this.appointmentBeanFirstUI)){
            Facade.getInstance().decorateView(ViewLayout.HOME);
        }
    }
    @FXML
    private void payTheApp(){
        Facade.getInstance().decorateView(ViewLayout.PAYONLINEPAYPAL);
    }

}
