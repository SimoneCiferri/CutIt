package cutit.controller.login;

import cutit.bean.*;
import cutit.bean.CustomerBeanUQ;
import cutit.bean.HairdresserBeanUQ;
import cutit.bean.ShopBean;
import cutit.bean.UserBeanUQ;
import cutit.controller.topbarviewcontrollers.TopBarCustomerViewController;
import cutit.controller.topbarviewcontrollers.TopBarHairdresserViewController;
import cutit.decorator.ViewLayout1;
import cutit.decorator.concrete_decorator.TopBarCustomerView1;
import cutit.decorator.concrete_decorator.TopBarHairdresserView1;
import cutit.exception.DBConnectionException;
import cutit.exception.ExceptionText;
import cutit.exception.RecordNotFoundException;
import cutit.exception.WrongCredentialsException;
import cutit.facade.Facade;
import cutit.factory.AlertFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;


public class LoginViewController {

    private LoginController loginController;
    private UserBean userBeanFirstUI;
    private CustomerBean customerBeanFirstUI;
    private HairdresserBean hairdresserBeanFirstUI;
    private ShopBeanInterface shopBeanFirstUI;


    @FXML
    TextField tfUsername;

    @FXML
    PasswordField pfPassword;

    @FXML
    public void initialize(){
        userBeanFirstUI = new UserBeanUQ();
        customerBeanFirstUI = new CustomerBeanUQ();
        hairdresserBeanFirstUI = new HairdresserBeanUQ();
        shopBeanFirstUI = new ShopBean();
        loginController = new LoginController();
    }

    @FXML
    public void tryLogin() {
        if(loginNotNull(tfUsername.getText(), pfPassword.getText())){
            userBeanFirstUI.setUsername(tfUsername.getText());
            userBeanFirstUI.setPasswd(pfPassword.getText());
            try {
                if(loginController.login(this.userBeanFirstUI)){
                    if (this.userBeanFirstUI.getRole() == 0) {
                        loginController.getCustomer(userBeanFirstUI, customerBeanFirstUI);
                        Facade.getInstance().decorateView(ViewLayout1.TOPBARCUSTOMER);
                        TopBarCustomerView1 view = (TopBarCustomerView1) Facade.getInstance().getViewMap().get(ViewLayout1.TOPBARCUSTOMER);
                        TopBarCustomerViewController viewController = (TopBarCustomerViewController) view.getLoadedViewController1(ViewLayout1.TOPBARCUSTOMER);
                        viewController.startBean(this.customerBeanFirstUI);
                    }else{
                        loginController.getHairdresserAndShop(userBeanFirstUI, hairdresserBeanFirstUI, shopBeanFirstUI);
                        Facade.getInstance().decorateView(ViewLayout1.TOPBARHAIRDRESSER);
                        TopBarHairdresserView1 view = (TopBarHairdresserView1) Facade.getInstance().getViewMap().get(ViewLayout1.TOPBARHAIRDRESSER);
                        TopBarHairdresserViewController viewController = (TopBarHairdresserViewController) view.getLoadedViewController1(ViewLayout1.TOPBARHAIRDRESSER);
                        viewController.startBean(shopBeanFirstUI);
                    }
                }
            } catch (RecordNotFoundException | WrongCredentialsException e) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), e.getMessage());
                alert.showAndWait();
            } catch(DBConnectionException dbe){
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
                alert.showAndWait();
            } catch (SQLException sql) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
                alert.showAndWait();
            } catch (IOException ie) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getIoErrorTitle(), ExceptionText.getIoErrorMessage());
                alert.showAndWait();
            }
        }
    }

    private boolean loginNotNull(String username, String psswd){
        return (!Objects.equals(username, "") && !Objects.equals(psswd, ""));
    }

    @FXML
    public boolean goSignUp() {
        Facade.getInstance().decorateView(ViewLayout1.SIGNUP);
        return true;
    }

}
