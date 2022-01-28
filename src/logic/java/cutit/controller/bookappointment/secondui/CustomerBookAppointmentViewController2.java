package cutit.controller.bookappointment.secondui;

import cutit.bean.AppointmentBean;
import cutit.bean.AppointmentBeanInterface;
import cutit.bean.CustomerBean;
import cutit.bean.ShopBeanInterface;
import cutit.controller.bookappointment.BookAppointmentController;
import cutit.decorator.ViewLayout2;
import cutit.decorator.concrete_decorator2.CustomerAppointmentsView2;
import cutit.decorator.concrete_decorator2.CustomerBookAppointmentView2;
import cutit.decorator.concrete_decorator2.HomeView2;
import cutit.exception.*;
import cutit.facade.Facade2;
import cutit.factory.AlertFactory;
import cutit.utils.TextFieldCheck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import javax.swing.text.View;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerBookAppointmentViewController2 {

    private CustomerBean customerBeanSecondUI;
    private ShopBeanInterface shopBean;
    private AppointmentBeanInterface appointmentBeanSecondUI;
    private BookAppointmentController bookAppointmentController;

    @FXML
    private TextField tfDate;

    @FXML
    private TextField tfPromotionCode;

    @FXML
    private Spinner<LocalTime> timeSpinner;

    @FXML
    private Spinner<String> serviceSpinner;

    @FXML
    public void initialize(){
        appointmentBeanSecondUI = new AppointmentBean();
        bookAppointmentController = new BookAppointmentController();
        tfDate.setOnKeyTyped(event -> {
            String selectedDay = tfDate.getText();
            if(dateEntered(selectedDay)){
                getAvailableTimeSlots();
            }
        });
    }

    private boolean dateEntered(String selectedDay){
        return selectedDay.length() == 10;
    }

    private void getAvailableTimeSlots() {
        if(TextFieldCheck.isDateFormat(tfDate.getText(), "Date is not correct. Please follow the syntax yyyy-MM-dd")){
            appointmentBeanSecondUI.setSelectedDay(LocalDate.parse(tfDate.getText()));
            try {
                bookAppointmentController.getAvailableSlots(appointmentBeanSecondUI, shopBean.getShopName());
                List<LocalTime> availableSlots = appointmentBeanSecondUI.getAvailableSlots();
                setTimeSpinner(availableSlots);
                timeSpinner.setDisable(availableSlots.isEmpty());
            } catch (RecordNotFoundException |WrongInputDataException ex) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), ex.getMessage());
                alert.showAndWait();
            } catch (DBConnectionException dbExc) {
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

    }

    private void setTimeSpinner(List<LocalTime> availableSlots){
        ObservableList<LocalTime> list = FXCollections.observableArrayList(availableSlots);
        SpinnerValueFactory<LocalTime> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(list);
        timeSpinner.setValueFactory(valueFactory);
    }

    private void getServices() {
        try {
            bookAppointmentController.getAvailableServices(appointmentBeanSecondUI, shopBean.getShopName());
            List<String> allServices = appointmentBeanSecondUI.getAvailableServices();
            setServiceSpinner(allServices);
            serviceSpinner.setDisable(allServices.isEmpty());
        } catch (RecordNotFoundException rnfException) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), rnfException.getMessage());
            alert.showAndWait();
        } catch (DBConnectionException dExc) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException se){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        } catch (IOException ioe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getIoErrorTitle(), ExceptionText.getIoErrorMessage());
            alert.showAndWait();
        }
    }

    private void setServiceSpinner(List<String> allServices) {
        ObservableList<String> list = FXCollections.observableArrayList(allServices);
        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(list);
        serviceSpinner.setValueFactory(valueFactory);
    }

    @FXML
    private void payAndBook(){
        try {
            if(checkAllInput()){
                bookAppointmentController.bookAppointment(appointmentBeanSecondUI);
                showAppointments();
            }
        } catch (DBConnectionException dbExc) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sqe){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        } catch (RecordNotFoundException | PaymentException | DuplicatedRecordException exception) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), exception.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getIoErrorTitle(), ExceptionText.getIoErrorMessage());
            alert.showAndWait();
        }
        //ricorda di controllare open e close time solo date intere
    }

    private boolean checkAllInput() throws SQLException, DBConnectionException {
        List<String> allInputs = new ArrayList<>();
        allInputs.add(tfDate.getText());
        allInputs.add(timeSpinner.getValue().toString());
        allInputs.add(serviceSpinner.getValue());
        if(!TextFieldCheck.isSomethingNull(allInputs)){
            appointmentBeanSecondUI.setStartTime(getStartTime());
            appointmentBeanSecondUI.setCustomer(customerBeanSecondUI.getcEmail());
            appointmentBeanSecondUI.setServiceName(serviceSpinner.getValue());
            appointmentBeanSecondUI.setShopName(shopBean.getShopName());
            try {
                verifyPromotion();
                return true;
            } catch (RecordNotFoundException | WrongInputDataException e) {
                appointmentBeanSecondUI.setPromotionCode(null);
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), e.getMessage());
                alert.showAndWait();
                return false;
            }
        } else {
            return false;
        }
    }

    private LocalDateTime getStartTime() {
        String dateAndTime = tfDate.getText() + "T" + timeSpinner.getValue().toString();
        return LocalDateTime.parse(dateAndTime);
    }

    private void verifyPromotion() throws RecordNotFoundException, SQLException, DBConnectionException, WrongInputDataException {
        if(!Objects.equals(tfPromotionCode.getText(), "")){
            appointmentBeanSecondUI.setPromotionCode(tfPromotionCode.getText());
            bookAppointmentController.checkPromotion(appointmentBeanSecondUI);
        } else {
            appointmentBeanSecondUI.setPromotionCode(null);
        }
    }

    private void showAppointments() {
        Facade2.getInstance().decorateView2(ViewLayout2.CUSTOMERAPPOINTMENTS);
        CustomerAppointmentsView2 view = (CustomerAppointmentsView2) Facade2.getInstance().getViewMap().get(ViewLayout2.CUSTOMERAPPOINTMENTS);
        CustomerAppointmentsViewController2 viewController = (CustomerAppointmentsViewController2) view.getLoadedViewController2(ViewLayout2.CUSTOMERAPPOINTMENTS);
        viewController.fillView(customerBeanSecondUI);
    }

    @FXML
    private void backHome(){
        Facade2.getInstance().decorateView2(ViewLayout2.HOME2);
        HomeView2 view = (HomeView2) Facade2.getInstance().getViewMap().get(ViewLayout2.HOME2);
        HomeViewController2 viewController = (HomeViewController2) view.getLoadedViewController2(ViewLayout2.HOME2);
        viewController.fillView(customerBeanSecondUI);
    }


    public void fillView(CustomerBean customerBeanFirstUI, ShopBeanInterface shopBean){
        this.customerBeanSecondUI = customerBeanFirstUI;
        this.shopBean = shopBean;
        getServices();
    }
}
