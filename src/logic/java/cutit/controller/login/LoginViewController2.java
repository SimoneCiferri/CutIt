package cutit.controller.login;

import cutit.bean.*;
import cutit.bean.ShopBean;
import cutit.controller.leftbarviewcontrollers.LeftBarCustomerViewController;
import cutit.controller.leftbarviewcontrollers.LeftBarHairdresserViewController;
import cutit.decorator.ViewLayout1;
import cutit.decorator.ViewLayout2;
import cutit.decorator.concrete_decorator2.LeftBarCustomerView;
import cutit.decorator.concrete_decorator2.LeftBarHairdresserView;
import cutit.exception.*;
import cutit.facade.Facade;
import cutit.facade.Facade2;
import cutit.factory.AlertFactory;
import cutit.utils.TextFieldCheck;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class LoginViewController2 {

    private LoginController loginController;
    private UserBean userBeanSecondUI;
    private CustomerBean customerBeanSecondUI;
    private HairdresserBean hairdresserBeanSecondUI;
    private ShopBeanInterface shopBeanSecondUI;

    @FXML
    private TextField tfUsernameLogin;

    @FXML
    private TextField tfNameAndSurnameCustomer;

    @FXML
    private TextField tfBirthDateCustomer;

    @FXML
    private TextField tfEmailCustomer;

    @FXML
    private TextField tfNameAndSurnameHairdresser;

    @FXML
    private TextField tfEmailHairdresser;

    @FXML
    private TextField tfPivaHairdresser;

    @FXML
    private TextField tfShopNameHairdresser;

    @FXML
    private PasswordField pfPasswordLogin;

    @FXML
    private PasswordField pfConfirmPasswordCustomer;

    @FXML
    private PasswordField pfPasswordCustomer;

    @FXML
    private PasswordField pfPasswordHairdresser;

    @FXML
    private PasswordField pfConfirmPasswordHairdresser;

    @FXML
    private ChoiceBox<String> cbGenderCustomer;

    @FXML
    public void initialize(){
        userBeanSecondUI = new UserBeanUQ();
        customerBeanSecondUI = new CustomerBeanUQ();
        hairdresserBeanSecondUI = new HairdresserBeanUQ();
        shopBeanSecondUI = new ShopBean();
        loginController = new LoginController();
        cbGenderCustomer.getItems().addAll("Female", "Male", "Other");
    }

    @FXML
    public void login() {
        if(credentialNotNull(tfUsernameLogin.getText(), pfPasswordLogin.getText())){
            userBeanSecondUI.setUsername(tfUsernameLogin.getText());
            userBeanSecondUI.setPasswd(pfPasswordLogin.getText());
            try {
                if(loginController.login(this.userBeanSecondUI)){
                    if (this.userBeanSecondUI.getRole() == 0) {
                        loginController.getCustomer(userBeanSecondUI, customerBeanSecondUI);
                        Facade2.getInstance().decorateView2(ViewLayout2.LEFTBARCUSTOMER);
                        LeftBarCustomerView view = (LeftBarCustomerView) Facade2.getInstance().getViewMap().get(ViewLayout2.LEFTBARCUSTOMER);
                        LeftBarCustomerViewController viewController = (LeftBarCustomerViewController) view.getLoadedViewController2(ViewLayout2.LEFTBARCUSTOMER);
                        viewController.startBean(this.customerBeanSecondUI);
                    }else{
                        loginController.getHairdresserAndShop(userBeanSecondUI, hairdresserBeanSecondUI, shopBeanSecondUI);
                        Facade2.getInstance().decorateView2(ViewLayout2.LEFTBARHAIRDRESSER);
                        LeftBarHairdresserView view = (LeftBarHairdresserView) Facade2.getInstance().getViewMap().get(ViewLayout2.LEFTBARHAIRDRESSER);
                        LeftBarHairdresserViewController viewController = (LeftBarHairdresserViewController) view.getLoadedViewController2(ViewLayout2.LEFTBARHAIRDRESSER);
                        viewController.startBean(shopBeanSecondUI);
                    }
                }
            } catch (RecordNotFoundException | WrongCredentialsException e) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), e.getMessage());
                alert.showAndWait();
            } catch(DBConnectionException dbe){
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
                alert.showAndWait();
            } catch (SQLException sqle) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
                alert.showAndWait();
            } catch (IOException ioe) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getIoErrorTitle(), ExceptionText.getIoErrorMessage());
                alert.showAndWait();
            }
        }
    }

    private boolean credentialNotNull(String username, String psswd){
        return (!Objects.equals(username, "") && !Objects.equals(psswd, ""));
    }

    @FXML
    public void signUpCustomer() {
        try{
            List<String> listToCheck = new ArrayList<>();
            listToCheck.add(tfNameAndSurnameCustomer.getText());
            listToCheck.add(tfEmailCustomer.getText());
            listToCheck.add(cbGenderCustomer.getValue());
            listToCheck.add(pfPasswordCustomer.getText());
            listToCheck.add(tfBirthDateCustomer.getText());
            if(!TextFieldCheck.isSomethingNull(listToCheck)
                    && checkNameAndSurname(tfNameAndSurnameCustomer.getText())
                    && TextFieldCheck.isEmailAddress(tfEmailCustomer.getText())
                    && TextFieldCheck.isDateFormat(tfBirthDateCustomer.getText(), "Expire date is not correct. Please follow the syntax yyyy-MM-dd")
                    && TextFieldCheck.checkSamePassword(pfPasswordCustomer, pfConfirmPasswordCustomer)){
                customerBeanSecondUI.setcName(getName(tfNameAndSurnameCustomer.getText()));
                customerBeanSecondUI.setcSurname(getSurname(tfNameAndSurnameCustomer.getText()));
                customerBeanSecondUI.setcBirthDate(LocalDate.parse(tfBirthDateCustomer.getText()));
                customerBeanSecondUI.setcGender(cbGenderCustomer.getValue());
                customerBeanSecondUI.setcEmail(tfEmailCustomer.getText());
                customerBeanSecondUI.setcPassword(pfPasswordCustomer.getText());
                if(loginController.signUpCustomer(this.customerBeanSecondUI)){
                    clearData();
                }
            }
        } catch (DuplicatedRecordException | WrongInputDataException e) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), e.getMessage());
            alert.showAndWait();
        } catch(DBConnectionException dbe){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sqle) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        }
    }

    private void clearData() {
        tfNameAndSurnameCustomer.setText("");
        tfEmailCustomer.setText("");
        pfPasswordCustomer.setText("");
        pfConfirmPasswordCustomer.setText("");
        tfBirthDateCustomer.setText("");
        tfNameAndSurnameHairdresser.setText("");
        tfEmailHairdresser.setText("");
        tfPivaHairdresser.setText("");
        tfShopNameHairdresser.setText("");
        pfPasswordHairdresser.setText("");
        pfConfirmPasswordHairdresser.setText("");
    }

    private boolean checkNameAndSurname(String nameAndSurname) {
        StringTokenizer st = new StringTokenizer(nameAndSurname, "-");
        if(st.countTokens() != 2){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), "Invalid Name or Surname. Please follow the syntax ' Name-Surname '");
            alert.showAndWait();
        }
        return st.countTokens() == 2;
    }

    private String getName(String nameAndSurname){
        StringTokenizer st = new StringTokenizer(nameAndSurname, "-");
        return st.nextToken();
    }

    private String getSurname(String nameAndSurname){
        StringTokenizer st = new StringTokenizer(nameAndSurname, "-");
        st.nextToken();
        return st.nextToken();
    }

    @FXML
    public void signUpHairdresser() {
        try{
            List<String> listToCheck = new ArrayList<>();
            listToCheck.add(tfNameAndSurnameHairdresser.getText());
            listToCheck.add(tfEmailHairdresser.getText());
            listToCheck.add(tfPivaHairdresser.getText());
            listToCheck.add(tfShopNameHairdresser.getText());
            listToCheck.add(pfPasswordHairdresser.getText());
            if(!TextFieldCheck.isSomethingNull(listToCheck)
                    && checkNameAndSurname(tfNameAndSurnameHairdresser.getText())
                    && TextFieldCheck.isEmailAddress(tfEmailHairdresser.getText())
                    && TextFieldCheck.isPiva(tfPivaHairdresser.getText())
                    && TextFieldCheck.checkSamePassword(pfPasswordHairdresser, pfConfirmPasswordHairdresser)){
                hairdresserBeanSecondUI.sethName(getName(tfNameAndSurnameHairdresser.getText()));
                hairdresserBeanSecondUI.sethSurname(getSurname(tfNameAndSurnameHairdresser.getText()));
                hairdresserBeanSecondUI.sethEmail(tfEmailHairdresser.getText());
                hairdresserBeanSecondUI.setpIVA(tfPivaHairdresser.getText());
                hairdresserBeanSecondUI.setShopName(tfShopNameHairdresser.getText());
                hairdresserBeanSecondUI.sethPassword(pfPasswordHairdresser.getText());
                if(loginController.signUpHair(hairdresserBeanSecondUI)){
                    clearData();
                }
            }
        } catch (DuplicatedRecordException e) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), e.getMessage());
            alert.showAndWait();
        } catch(DBConnectionException dbe){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sqle) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        }
    }

}
