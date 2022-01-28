package cutit.controller.bookappointment.secondui;

import cutit.bean.AppointmentBean;
import cutit.bean.AppointmentBeanInterface;
import cutit.bean.CustomerBean;
import cutit.bean.ShopBeanInterface;
import cutit.controller.bookappointment.BookAppointmentController;
import cutit.exception.DBConnectionException;
import cutit.exception.ExceptionText;
import cutit.exception.RecordNotFoundException;
import cutit.exception.WrongInputDataException;
import cutit.factory.AlertFactory;
import cutit.utils.TextFieldCheck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CustomerBookAppointmentViewController2 {

    private CustomerBean customerBeanSecondUI;
    private ShopBeanInterface shopBean;
    private AppointmentBeanInterface appointmentBeanSecondUI;
    private BookAppointmentController bookAppointmentController;

    @FXML
    private TextField tfDate;

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
                setSpinner(availableSlots);
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

    private void setSpinner(List<LocalTime> availableSlots){
        ObservableList<LocalTime> list = FXCollections.observableArrayList(availableSlots);
        SpinnerValueFactory<LocalTime> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(list);
        timeSpinner.setValueFactory(valueFactory);
    }

    public void fillView(CustomerBean customerBeanFirstUI, ShopBeanInterface shopBean){
        this.customerBeanSecondUI = customerBeanFirstUI;
        this.shopBean = shopBean;
    }
}
