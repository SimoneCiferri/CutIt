package cutit.controller.bookappointment;

import cutit.bean.interfaces.CustomerBeanInterface;
import cutit.bean.interfaces.ShopBeanInterface;
import cutit.bean.AppointmentBean;
import cutit.controller.getlocationdirections.GetLocationDirectionsGoogleMapsViewController1;
import cutit.controller.topbarviewcontrollers.TopBarCustomerViewController;
import cutit.decorator.decorator1.ViewLayout1;
import cutit.decorator.decorator1.concrete_decorator.GetLocationDirectionsGoogleMapsView1;
import cutit.decorator.decorator1.concrete_decorator.TopBarCustomerView1;
import cutit.exception.*;
import cutit.facade.Facade;
import cutit.factory.AlertFactory;
import cutit.utils.ExceptionText;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class CustomerBookAppointmentViewController {

    private CustomerBeanInterface customerBeanFirstUI;
    private AppointmentBean appointmentBean;
    private BookAppointmentController bookAppointmentController;
    private ShopBeanInterface shopBeanUQ;

    @FXML
    private BorderPane bpInBookApp;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelService;

    @FXML
    private Label lblTitleShopName;

    @FXML
    private Label lblPromotionApplied;

    @FXML
    private DatePicker dtPicker;

    @FXML
    private ChoiceBox<LocalTime> cbTimeSlot;

    @FXML
    private ChoiceBox<String> cbServices;

    @FXML
    private TextField tfPromotionCode;

    @FXML
    private Button checkPromotion;

    @FXML
    public void initialize(){
        appointmentBean = new AppointmentBean();
        bookAppointmentController = new BookAppointmentController();

        dtPicker.setValue(LocalDate.now());
        cbTimeSlot.setOnAction(event -> {
            LocalTime selectedTime = cbTimeSlot.getSelectionModel().getSelectedItem();
            if(selectedTime != null){
                labelDate.setText(dtPicker.getValue().toString() + " " + selectedTime);
            }else{
                labelDate.setText(dtPicker.getValue().toString());
            }
        });

        cbServices.setOnAction(event -> {
            String selectedService = cbServices.getSelectionModel().getSelectedItem();
            tfPromotionCode.setDisable(selectedService == null);
            checkPromotion.setDisable(selectedService == null);
        });
    }

    @FXML
    public boolean backToShopInfo() {
        Facade.getInstance().decorateView(ViewLayout1.SHOPINFO);
        return true;
    }

    @FXML
    public void bookAppointment() {
        if(checkInput()){
            appointmentBean.setStartTime(getAppointmentStartTime());
            appointmentBean.setCustomer(customerBeanFirstUI.getcEmail());
            setPromotion();
            appointmentBean.setServiceName(cbServices.getValue());
            appointmentBean.setShopName(shopBeanUQ.getShopName());
            try {
                bookAppointmentController.bookAppointment(appointmentBean);
                showPayedAndBooked();
            } catch (RecordNotFoundException | DuplicatedRecordException | PaymentException e) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), e.getMessage());
                alert.showAndWait();
            } catch (DBConnectionException dbe) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
                alert.showAndWait();
            } catch (SQLException sqe){
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
                alert.showAndWait();
            } catch (IOException ie) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getIoErrorTitle(), ExceptionText.getIoErrorMessage());
                alert.showAndWait();
            }
        }

    }

    private boolean checkInput() {
        return cbTimeSlot.getValue() != null && cbServices.getValue()  != null;
    }

    private void setPromotion(){
        if(!Objects.equals(tfPromotionCode.getText(), "")){
            if(checkPromotion()){
                appointmentBean.setPromotionCode(tfPromotionCode.getText());
            } else{
                appointmentBean.setPromotionCode(null);
            }
        } else {
            appointmentBean.setPromotionCode(null);
        }
    }

    private LocalDateTime getAppointmentStartTime() {
        String dateAndTime = dtPicker.getValue().toString() + "T" + cbTimeSlot.getValue().toString();
        return LocalDateTime.parse(dateAndTime);
    }

    @FXML
    private void showAvailableTimeSlots(){
        try {
            LocalDate day = dtPicker.getValue();
            appointmentBean.setSelectedDay(day);
            bookAppointmentController.getAvailableSlots(appointmentBean, shopBeanUQ.getShopName());
            List<LocalTime> availableSlots = appointmentBean.getAvailableSlots();
            cbTimeSlot.getItems().clear();
            for (LocalTime availableSlot : availableSlots) {
                cbTimeSlot.getItems().add(availableSlot);
            }
            cbTimeSlot.setDisable(availableSlots.isEmpty());
        } catch (RecordNotFoundException |WrongInputDataException exception) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), exception.getMessage());
            alert.showAndWait();
        } catch (DBConnectionException dbe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sqe){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        } catch (IOException ioe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getIoErrorTitle(), ExceptionText.getIoErrorMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void showAvailableServices() {
        try {
            bookAppointmentController.getAvailableServices(appointmentBean, shopBeanUQ.getShopName());
            for(int i = 0; i< appointmentBean.getAvailableServices().size(); i++){
                String service = appointmentBean.getAvailableServices().get(i);
                cbServices.getItems().add(service);
            }
            cbServices.setDisable(appointmentBean.getAvailableServices().isEmpty());
        } catch (RecordNotFoundException e) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), e.getMessage());
            alert.showAndWait();
        } catch (DBConnectionException dbe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sqlE){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        } catch (IOException ioe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getIoErrorTitle(), ExceptionText.getIoErrorMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private boolean checkPromotion(){
        try {
            if(!Objects.equals(tfPromotionCode.getText(), "")){
                appointmentBean.setPromotionCode(tfPromotionCode.getText());
                appointmentBean.setCustomer(customerBeanFirstUI.getcEmail());
                appointmentBean.setServiceName(cbServices.getValue());
                appointmentBean.setShopName(shopBeanUQ.getShopName());
                bookAppointmentController.checkPromotion(appointmentBean);
                tfPromotionCode.setDisable(true);
                checkPromotion.setDisable(true);
                lblPromotionApplied.setVisible(true);
                return true;
            } else {
                return false;
            }
        } catch (RecordNotFoundException |WrongInputDataException e) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), e.getMessage());
            alert.showAndWait();
            return false;
        } catch(DBConnectionException dbe){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
            return false;
        } catch (SQLException sqe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
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
        btnGetDir.setOnMouseClicked(mouseEvent -> getDirections());
        Button btnAddToFav = new Button("Add Shop To Favourites");
        btnAddToFav.setPrefHeight(55);
        btnAddToFav.setOnMouseClicked(mouseEvent -> addToFavourites());
        hCont.getChildren().addAll(btnGetDir, btnAddToFav);
        cont.getChildren().addAll(l, l1, hCont);
        bpInBookApp.getChildren().clear();
        bpInBookApp.setCenter(cont);
    }

    private void getDirections(){
        Facade.getInstance().decorateView(ViewLayout1.GMAPS);
        GetLocationDirectionsGoogleMapsView1 view = (GetLocationDirectionsGoogleMapsView1) Facade.getInstance().getViewMap().get(ViewLayout1.GMAPS);
        GetLocationDirectionsGoogleMapsViewController1 viewController = (GetLocationDirectionsGoogleMapsViewController1) view.getLoadedViewController1(ViewLayout1.GMAPS);
        bookAppointmentController.getShopDirections(viewController, shopBeanUQ);
    }

    private void addToFavourites() {
        try {
            if(bookAppointmentController.addShopToFavourites(customerBeanFirstUI.getcEmail(), shopBeanUQ.getShopName() )){
                TopBarCustomerView1 view = (TopBarCustomerView1) Facade.getInstance().getViewMap().get(ViewLayout1.TOPBARCUSTOMER);
                TopBarCustomerViewController viewController = (TopBarCustomerViewController) view.getLoadedViewController1(ViewLayout1.TOPBARCUSTOMER);
                viewController.goFav();
            }
        } catch (DuplicatedRecordException e) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), e.getMessage());
            alert.showAndWait();
        } catch (DBConnectionException dbe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sqe){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        }
    }

    public void fillView(CustomerBeanInterface customerBeanFirstUI, ShopBeanInterface shopBeanUQ){
        this.customerBeanFirstUI = customerBeanFirstUI;
        this.shopBeanUQ = shopBeanUQ;
        lblTitleShopName.setText(shopBeanUQ.getShopName());
        showAvailableTimeSlots();
        showAvailableServices();
    }

}
