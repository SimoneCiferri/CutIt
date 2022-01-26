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
import cutit.exception.RecordNotFoundException;
import cutit.exception.WrongCredentialsException;
import cutit.facade.Facade;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
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
    private static final String CONNECTION_ERROR_TITLE = "Connection error";
    private static final String WARNING_TITLE = "Warning";
    private static final String IO_ERROR_TITLE = "Error";
    private static final String CONNECTION_ERROR_MESSAGE = "Please check your internet connection. If problem persists try to restart the application.";
    private static final String SQL_ERROR_MESSAGE = "Please check your internet connection. If problem persists contact us at cutitapp@support.com.";
    private static final String IO_ERROR_MESSAGE = "Impossible to load some files. If problem persists try again later or contact us at cutitapp@support.com";


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
        if(!Objects.equals(tfUsername.getText(), "") && !Objects.equals(pfPassword.getText(), "")){
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
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, WARNING_TITLE, e.getMessage());
                alert.showAndWait();
            } catch(DBConnectionException dbe){
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, CONNECTION_ERROR_MESSAGE);
                alert.showAndWait();
            } catch (SQLException sqle) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, SQL_ERROR_MESSAGE);
                alert.showAndWait();
            } catch (IOException ioe) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, IO_ERROR_TITLE, IO_ERROR_MESSAGE);
                alert.showAndWait();
            }
        }
    }

    @FXML
    public boolean goSignUp() {
        Facade.getInstance().decorateView(ViewLayout1.SIGNUP);
        return true;
    }

}
