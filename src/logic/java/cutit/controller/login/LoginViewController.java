package cutit.controller.login;

import cutit.bean.*;
import cutit.bean.CustomerBeanUQ;
import cutit.bean.HairdresserBeanUQ;
import cutit.bean.ShopBeanUQ;
import cutit.bean.UserBeanUQ;
import cutit.controller.topbarviewcontrollers.TopBarCustomerViewController;
import cutit.controller.topbarviewcontrollers.TopBarHairdresserViewController;
import cutit.decorator.ViewLayout1;
import cutit.decorator.concrete_decorator.TopBarCustomerView1;
import cutit.decorator.concrete_decorator.TopBarHairdresserView1;
import cutit.facade.Facade;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Objects;


public class LoginViewController {

    private LoginController loginController;
    private UserBean userBeanFirstUI;
    private CustomerBean customerBeanFirstUI;
    private HairdresserBean hairdresserBeanFirstUI;
    private ShopBean shopBeanFirstUI;

    @FXML
    TextField tfUsername;

    @FXML
    PasswordField pfPassword;

    @FXML
    public void initialize(){
        userBeanFirstUI = new UserBeanUQ();
        customerBeanFirstUI = new CustomerBeanUQ();
        hairdresserBeanFirstUI = new HairdresserBeanUQ();
        shopBeanFirstUI = new ShopBeanUQ();
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
                        viewController.startBean(hairdresserBeanFirstUI, shopBeanFirstUI);
                    }
                }
            } catch (Exception e) {
                LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
                Alert alert = AlertFactory.getInstance().generateAlert(Alert.AlertType.WARNING, "Login Error!", "Please check your internet connection", "If the problem persist try again later.");
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
