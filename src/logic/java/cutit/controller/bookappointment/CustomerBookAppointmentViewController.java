package cutit.controller.bookappointment;

import cutit.bean.CustomerBean;
import cutit.bean.PayOnlineBean;
import cutit.bean.RateShopBean;
import cutit.bean.ShopBean;
import cutit.bean.firstui.AppointmentBeanFirstUI;
import cutit.bean.firstui.RateShopBeanUQ;
import cutit.decorator.ViewLayout;
import cutit.decorator.concreteDecorator.CustomerFavouritesShopView;
import cutit.exception.DBConnectionException;
import cutit.exception.DuplicatedRecordException;
import cutit.facade.Facade;
import cutit.factory.AlertFactory;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.time.LocalDate;

public class CustomerBookAppointmentViewController {

    private CustomerBean customerBeanFirstUI;
    private AppointmentBeanFirstUI appointmentBeanFirstUI;
    private BookAppointmentController bookAppointmentController;
    private RateShopBean rateShopBean;
    private ShopBean shopBeanUQ;

    @FXML
    private BorderPane bpInBookApp;

    @FXML
    private Label labelDate, label830, labelService, lblTitleShopName;

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
        /*if(bookAppointmentController.compileAppointment(this.appointmentBeanFirstUI)){
            showPayedAndBooked();
        }*/
        showPayedAndBooked();
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

    private void addToFavourites() {
        try {
            if(bookAppointmentController.addShopToFavourites(customerBeanFirstUI.getcEmail(), shopBeanUQ.getShopName() )){
                Facade.getInstance().decorateView(ViewLayout.FAVSHOP);
                CustomerFavouritesShopView view = (CustomerFavouritesShopView) Facade.getInstance().getViewMap().get(ViewLayout.FAVSHOP);
                CustomerFavouritesShopViewController viewController = (CustomerFavouritesShopViewController) view.getLoadedViewController(ViewLayout.FAVSHOP);
                viewController.fillView(customerBeanFirstUI);
            }
        } catch (DuplicatedRecordException de) {
            AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, "Information", de.getMessage());
            Facade.getInstance().decorateView(ViewLayout.FAVSHOP);
        } catch(DBConnectionException dce){
            AlertFactory.getInstance().generateAlert(Alert.AlertType.WARNING, "Connection error", "Please check your internet connection.");
        } catch (Exception e) {
            e.printStackTrace();
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

    public void fillView(CustomerBean customerBeanFirstUI, ShopBean shopBeanUQ){
        this.customerBeanFirstUI = customerBeanFirstUI;
        this.shopBeanUQ = shopBeanUQ;
        lblTitleShopName.setText(shopBeanUQ.getShopName());
    }

}
