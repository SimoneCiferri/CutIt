package cutit.controller.bookappointment;

import cutit.bean.CustomerBean;
import cutit.bean.RateShopBean;
import cutit.bean.ShopBean;
import cutit.bean.AppointmentBeanUQ;
import cutit.bean.firstui.RateShopBeanUQ;
import cutit.controller.topbarviewcontrollers.TopBarCustomerViewController;
import cutit.decorator.ViewLayout1;
import cutit.decorator.concrete_decorator.TopBarCustomerView1;
import cutit.exception.DBConnectionException;
import cutit.exception.DuplicatedRecordException;
import cutit.exception.RecordNotFoundException;
import cutit.exception.WrongInputDataException;
import cutit.facade.Facade;
import cutit.factory.AlertFactory;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class CustomerBookAppointmentViewController {

    private CustomerBean customerBeanFirstUI;
    private AppointmentBeanUQ appointmentBeanUQ;
    private BookAppointmentController bookAppointmentController;
    private RateShopBean rateShopBean;
    private ShopBean shopBeanUQ;

    @FXML
    private BorderPane bpInBookApp;

    @FXML
    private Label labelDate, labelService, lblTitleShopName, lblPromotionApplied;

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
    private TextField tfPromotionCode;

    @FXML
    private TextArea taNotes;

    @FXML
    private Button checkPromotion;

    @FXML
    public void initialize(){
        appointmentBeanUQ = new AppointmentBeanUQ();
        rateShopBean = new RateShopBeanUQ();
        bookAppointmentController = new BookAppointmentController();

        dtPicker.setValue(LocalDate.now());
        cbTimeSlot.setOnAction((event) -> {
            LocalTime selectedTime = cbTimeSlot.getSelectionModel().getSelectedItem();
            if(selectedTime != null){
                labelDate.setText(dtPicker.getValue().toString() + " " + selectedTime);
            }else{
                labelDate.setText(dtPicker.getValue().toString());
            }
        });

        cbServices.setOnAction((event) -> {
            String selectedService = cbServices.getSelectionModel().getSelectedItem();
            tfPromotionCode.setDisable(selectedService == null);
            checkPromotion.setDisable(selectedService == null);
        });
        System.out.println("CONTROLLER GRAFICO CUSTOMERBOOKAPPOINTMENTVIEWCONTROLLER");
    }

    @FXML
    public boolean backToShopInfo() {
        Facade.getInstance().decorateView(ViewLayout1.SHOPINFO);
        return true;
    }

    @FXML
    public void bookAppointment() {
        if(checkInput()){
            appointmentBeanUQ.setStartTime(getAppointmentStartTime());
            appointmentBeanUQ.setCustomer(customerBeanFirstUI.getcEmail());
            if(!Objects.equals(tfPromotionCode.getText(), "")){
                if(checkPromotion()){
                    appointmentBeanUQ.setPromotionCode(tfPromotionCode.getText());
                } else{
                    appointmentBeanUQ.setPromotionCode(null);
                }
            } else {
                appointmentBeanUQ.setPromotionCode(null);
            }
            appointmentBeanUQ.setServiceName(cbServices.getValue());
            appointmentBeanUQ.setShopName(shopBeanUQ.getShopName());
            if(!Objects.equals(taNotes.getText(), "")){
                appointmentBeanUQ.setAppNotes(taNotes.getText());
            }
            try {
                bookAppointmentController.bookAppointment(appointmentBeanUQ);
                //Facade.getInstance().decorateView(ViewLayout.PAYONLINEPAYPAL);
                showPayedAndBooked();
            } catch (DuplicatedRecordException de){
                AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, "Information", de.getMessage());
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private boolean checkInput() {
        return cbTimeSlot.getValue() != null && cbServices.getValue()  != null;
    }

    private LocalDateTime getAppointmentStartTime() {
        String dateAndTime = dtPicker.getValue().toString() + "T" + cbTimeSlot.getValue().toString();
        return LocalDateTime.parse(dateAndTime);
    }

    @FXML
    private void showAvailableTimeSlots(){
        try {
            LocalDate day = dtPicker.getValue();
            appointmentBeanUQ.setSelectedDay(day);
            bookAppointmentController.getAvailableSlots(appointmentBeanUQ, shopBeanUQ.getShopName());
            List<LocalTime> availableSlots = appointmentBeanUQ.getAvailableSlots();
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
            bookAppointmentController.getAvailableServices(appointmentBeanUQ, shopBeanUQ.getShopName());
            for(int i = 0; i< appointmentBeanUQ.getAvailableServices().size(); i++){
                String service = appointmentBeanUQ.getAvailableServices().get(i);
                cbServices.getItems().add(service);
            }
            cbServices.setDisable(appointmentBeanUQ.getAvailableServices().isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private boolean checkPromotion(){
        try {
            if(!Objects.equals(tfPromotionCode.getText(), "")){
                System.out.println("codice non nullo = '" + tfPromotionCode.getText() + "'");
                appointmentBeanUQ.setPromotionCode(tfPromotionCode.getText());
                appointmentBeanUQ.setCustomer(customerBeanFirstUI.getcEmail());
                appointmentBeanUQ.setServiceName(cbServices.getValue());
                appointmentBeanUQ.setShopName(shopBeanUQ.getShopName());
                bookAppointmentController.checkPromotion(appointmentBeanUQ);
                tfPromotionCode.setDisable(true);
                checkPromotion.setDisable(true);
                lblPromotionApplied.setVisible(true);
                return true;
            } else {
                return false;
            }
        } catch (RecordNotFoundException | WrongInputDataException exception){
            AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, "Information", exception.getMessage());
            tfPromotionCode.setText("");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
        hCont.getChildren().addAll(btnGetDir, btnAddToFav);
        cont.getChildren().addAll(l, l1, hCont);
        bpInBookApp.getChildren().clear();
        bpInBookApp.setCenter(cont);
    }

    private void getDirections(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
    }

    private void addToFavourites() {
        try {
            if(bookAppointmentController.addShopToFavourites(customerBeanFirstUI.getcEmail(), shopBeanUQ.getShopName() )){
                TopBarCustomerView1 view = (TopBarCustomerView1) Facade.getInstance().getViewMap().get(ViewLayout1.TOPBARCUSTOMER);
                TopBarCustomerViewController viewController = (TopBarCustomerViewController) view.getLoadedViewController1(ViewLayout1.TOPBARCUSTOMER);
                viewController.goFav();
            }
        } catch (DuplicatedRecordException de) {
            AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, "Information", de.getMessage());
            TopBarCustomerView1 view = (TopBarCustomerView1) Facade.getInstance().getViewMap().get(ViewLayout1.TOPBARCUSTOMER);
            TopBarCustomerViewController viewController = (TopBarCustomerViewController) view.getLoadedViewController1(ViewLayout1.TOPBARCUSTOMER);
            viewController.goFav();
        } catch(DBConnectionException dce){
            AlertFactory.getInstance().generateAlert(Alert.AlertType.WARNING, "Connection error", "Please check your internet connection.");
        } catch (Exception e) {
            e.printStackTrace();
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
