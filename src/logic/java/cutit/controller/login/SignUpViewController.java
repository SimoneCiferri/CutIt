package cutit.controller.login;

import cutit.bean.CustomerBean;
import cutit.bean.HairdresserBean;
import cutit.bean.CustomerBeanUQ;
import cutit.bean.HairdresserBeanUQ;
import cutit.decorator.ViewLayout1;
import cutit.exception.DBConnectionException;
import cutit.exception.DuplicatedRecordException;
import cutit.exception.RecordNotFoundException;
import cutit.exception.WrongInputDataException;
import cutit.facade.Facade;
import cutit.factory.AlertFactory;
import cutit.utils.TextFieldCheck;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SignUpViewController {

    private CustomerBean customerBeanFirstUI;
    private HairdresserBean hairdresserBeanFirstUI;
    private LoginController loginController;
    private static final String CONNECTION_ERROR_TITLE = "Connection error";
    private static final String WARNING_TITLE = "Warning";
    private static final String CONNECTION_ERROR_MESSAGE = "Please check your internet connection. If problem persists try to restart the application.";
    private static final String SQL_ERROR_MESSAGE = "Please check your internet connection. If problem persists contact us at cutitapp@support.com.";

    @FXML
    private TextField tfCustomerName;

    @FXML
    private TextField tfCustomerSurname;

    @FXML
    private TextField tfCustomerEmail;

    @FXML
    private TextField tfHairdresserName;

    @FXML
    private TextField tfHairdresserSurname;

    @FXML
    private TextField tfHairdresserEmail;

    @FXML
    private TextField tfHairdresserShopName;

    @FXML
    private TextField tfHairdresserPIVA;

    @FXML
    private PasswordField pfCustomerPassword;

    @FXML
    private PasswordField pfCustomerConfirmPassword;

    @FXML
    private PasswordField pfHairdresserPassword;

    @FXML
    private PasswordField pfHairdresserConfirmPassword;

    @FXML
    private ChoiceBox<String> cbCustomerGender;

    @FXML
    private DatePicker dpCustomerBirthdate;

    @FXML
    public void initialize(){
        customerBeanFirstUI = new CustomerBeanUQ();
        hairdresserBeanFirstUI = new HairdresserBeanUQ();
        loginController = new LoginController();
        cbCustomerGender.getItems().addAll("Female", "Male", "Other");
    }

    @FXML
    public void trySignUpCustomer(){
        try{
            if(!checkIfNull(tfCustomerName.getText(), tfCustomerSurname.getText(), cbCustomerGender.getValue(), tfCustomerEmail.getText(), pfCustomerPassword.getText())
                    && TextFieldCheck.isEmailAddress(tfCustomerEmail.getText())
                    && checkSamePasswd(pfCustomerPassword, pfCustomerConfirmPassword)){
                customerBeanFirstUI.setcName(tfCustomerName.getText());
                customerBeanFirstUI.setcSurname(tfCustomerSurname.getText());
                customerBeanFirstUI.setcBirthDate(dpCustomerBirthdate.getValue());
                customerBeanFirstUI.setcGender(cbCustomerGender.getValue());
                customerBeanFirstUI.setcEmail(tfCustomerEmail.getText());
                customerBeanFirstUI.setcPassword(pfCustomerPassword.getText());
                if(loginController.signUpCustomer(this.customerBeanFirstUI)){
                    Facade.getInstance().decorateView(ViewLayout1.LOGIN);
                }
            }
        } catch (DuplicatedRecordException | WrongInputDataException e) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, WARNING_TITLE, e.getMessage());
            alert.showAndWait();
        } catch(DBConnectionException dbe){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, CONNECTION_ERROR_MESSAGE);
            alert.showAndWait();
        } catch (SQLException sqle) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, SQL_ERROR_MESSAGE);
            alert.showAndWait();
        }
    }

    @FXML
    public void trySignUpHair(){
        try{
            if(!checkIfNull(tfHairdresserName.getText(), tfHairdresserSurname.getText(), tfHairdresserEmail.getText(), tfHairdresserPIVA.getText(), tfHairdresserShopName.getText())
                    && TextFieldCheck.isEmailAddress(tfHairdresserEmail.getText())
                    && TextFieldCheck.isPiva(tfHairdresserPIVA.getText())
                    && checkSamePasswd(pfHairdresserPassword, pfHairdresserConfirmPassword)){
                hairdresserBeanFirstUI.sethName(tfHairdresserName.getText());
                hairdresserBeanFirstUI.sethSurname(tfHairdresserSurname.getText());
                hairdresserBeanFirstUI.sethEmail(tfHairdresserEmail.getText());
                hairdresserBeanFirstUI.setpIVA(tfHairdresserPIVA.getText());
                hairdresserBeanFirstUI.setShopName(tfHairdresserShopName.getText());
                hairdresserBeanFirstUI.sethPassword(pfHairdresserPassword.getText());
                if(loginController.signUpHair(hairdresserBeanFirstUI)){
                    Facade.getInstance().decorateView(ViewLayout1.LOGIN);
                }
            }
        } catch (DuplicatedRecordException e) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, WARNING_TITLE, e.getMessage());
            alert.showAndWait();
        } catch(DBConnectionException dbe){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, CONNECTION_ERROR_MESSAGE);
            alert.showAndWait();
        } catch (SQLException sqle) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, SQL_ERROR_MESSAGE);
            alert.showAndWait();
        }
    }

    private boolean checkSamePasswd(PasswordField p1, PasswordField p2) {
        if(!Objects.equals(p1.getText(), "") && !Objects.equals(p2.getText(), "")){
            return Objects.equals(p1.getText(), p2.getText());
        }
        return false;
    }

    private Boolean checkIfNull(String s1, String s2, String s3, String s4, String s5){
        List<String> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        return TextFieldCheck.isSomethingNull(list);
    }

    @FXML
    private void goBack(){
            Facade.getInstance().decorateView(ViewLayout1.LOGIN);
    }

}
