package cutit.controller.bookappointment;

import cutit.bean.CustomerBean;
import cutit.bean.RateShopBean;
import cutit.bean.ShopBean;
import cutit.bean.firstui.AppointmentBeanFirstUI;
import cutit.bean.firstui.RateShopBeanUQ;
import cutit.controller.topbarviewcontrollers.TopBarCustomerViewController;
import cutit.decorator.ViewLayout;
import cutit.decorator.concreteDecorator.TopBarCustomerView;
import cutit.exception.DBConnectionException;
import cutit.exception.DuplicatedRecordException;
import cutit.exception.WrongInputDataException;
import cutit.facade.Facade;
import cutit.factory.AlertFactory;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
    private VBox vbDateAndTime;

    @FXML
    private ChoiceBox<LocalTime> cbTimeSlot;

    @FXML
    private ChoiceBox<String> cbServices;

    @FXML
    public void initialize(){
        dtPicker.setValue(LocalDate.now());
        appointmentBeanFirstUI = new AppointmentBeanFirstUI();
        rateShopBean = new RateShopBeanUQ();
        bookAppointmentController = new BookAppointmentController();

        cbTimeSlot.setOnAction((event) -> {
            LocalTime selectedItem = cbTimeSlot.getSelectionModel().getSelectedItem();
            if(selectedItem != null){
                labelDate.setText(dtPicker.getValue().toString() + " " + selectedItem);
            }else{
                labelDate.setText(dtPicker.getValue().toString());
            }

        });
        System.out.println("CONTROLLER GRAFICO CUSTOMERBOOKAPPOINTMENTVIEWCONTROLLER");
    }

    @FXML
    public boolean backToShopInfo() {
        Facade.getInstance().decorateView(ViewLayout.SHOPINFO);
        return true;
    }

    @FXML
    public void bookAppointment() {
        if(bookAppointmentController.compileAppointment(this.appointmentBeanFirstUI)){
            if(bookAppointmentController.payAppointment(appointmentBeanFirstUI)){
                showPayedAndBooked();
            } else {
                AlertFactory.getInstance().generateAlert(Alert.AlertType.WARNING, "Warning", "Payment rejected!");
            }
        } else {
            AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, "Information", "Please check your data.");
        }
    }

    @FXML
    private void showAvailableTimeSlots(){
        try {
            LocalDate day = dtPicker.getValue();
            appointmentBeanFirstUI.setSelectedDay(day);
            bookAppointmentController.getAvailableSlots(appointmentBeanFirstUI, shopBeanUQ.getShopName());
            List<LocalTime> availableSlots = appointmentBeanFirstUI.getAvailableSlots();
            cbTimeSlot.getItems().clear();
            for (LocalTime availableSlot : availableSlots) {
                cbTimeSlot.getItems().add(availableSlot);
            }
            cbTimeSlot.setDisable(availableSlots.isEmpty());
            //differenza e ho quelli liberi
        } catch (WrongInputDataException wde) {
            AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, "Information", wde.getMessage());
            dtPicker.setValue(LocalDate.now());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showAvailableServices() {
        try {
            bookAppointmentController.getAvailableServices(appointmentBeanFirstUI, shopBeanUQ.getShopName());
            for(int i = 0; i<appointmentBeanFirstUI.getAvailableServices().size(); i++){
                String service = appointmentBeanFirstUI.getAvailableServices().get(i);
                cbServices.getItems().add(service);
            }
            cbServices.setDisable(appointmentBeanFirstUI.getAvailableServices().isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                TopBarCustomerView view = (TopBarCustomerView) Facade.getInstance().getViewMap().get(ViewLayout.TOPBARCUSTOMER);
                TopBarCustomerViewController viewController = (TopBarCustomerViewController) view.getLoadedViewController(ViewLayout.TOPBARCUSTOMER);
                viewController.goFav();
            }
        } catch (DuplicatedRecordException de) {
            AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, "Information", de.getMessage());
            TopBarCustomerView view = (TopBarCustomerView) Facade.getInstance().getViewMap().get(ViewLayout.TOPBARCUSTOMER);
            TopBarCustomerViewController viewController = (TopBarCustomerViewController) view.getLoadedViewController(ViewLayout.TOPBARCUSTOMER);
            viewController.goFav();
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

    public void fillView(CustomerBean customerBeanFirstUI, ShopBean shopBeanUQ){
        this.customerBeanFirstUI = customerBeanFirstUI;
        this.shopBeanUQ = shopBeanUQ;
        lblTitleShopName.setText(shopBeanUQ.getShopName());
        showAvailableTimeSlots();
        showAvailableServices();
    }

}
