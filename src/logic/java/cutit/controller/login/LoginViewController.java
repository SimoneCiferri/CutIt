package cutit.controller.login;

import cutit.bean.*;
import cutit.bean.firstui.CustomerBeanFirstUI;
import cutit.bean.firstui.HairdresserBeanFirstUI;
import cutit.bean.firstui.ShopBeanUQ;
import cutit.bean.firstui.UserBeanFirstUI;
import cutit.controller.topbarviewcontrollers.TopBarCustomerViewController;
import cutit.controller.topbarviewcontrollers.TopBarHairdresserViewController;
import cutit.decorator.ViewLayout;
import cutit.decorator.concreteDecorator.TopBarCustomerView;
import cutit.decorator.concreteDecorator.TopBarHairdresserView;
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
        userBeanFirstUI = new UserBeanFirstUI();
        customerBeanFirstUI = new CustomerBeanFirstUI();
        hairdresserBeanFirstUI = new HairdresserBeanFirstUI();
        shopBeanFirstUI = new ShopBeanUQ();
        loginController = new LoginController();
        System.out.println("CONTROLLER GRAFICO LOGINVIEWCONTROLLER");
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
                        Facade.getInstance().decorateView(ViewLayout.TOPBARCUSTOMER);
                        TopBarCustomerView view = (TopBarCustomerView) Facade.getInstance().getViewMap().get(ViewLayout.TOPBARCUSTOMER);
                        TopBarCustomerViewController viewController = (TopBarCustomerViewController) view.getLoadedViewController(ViewLayout.TOPBARCUSTOMER);
                        viewController.startBean(this.customerBeanFirstUI);
                    }else{
                        loginController.getHairdresserAndShop(userBeanFirstUI, hairdresserBeanFirstUI, shopBeanFirstUI);
                        Facade.getInstance().decorateView(ViewLayout.TOPBARHAIRDRESSER);
                        TopBarHairdresserView view = (TopBarHairdresserView) Facade.getInstance().getViewMap().get(ViewLayout.TOPBARHAIRDRESSER);
                        TopBarHairdresserViewController viewController = (TopBarHairdresserViewController) view.getLoadedViewController(ViewLayout.TOPBARHAIRDRESSER);
                        viewController.startBean(hairdresserBeanFirstUI, shopBeanFirstUI);
                    }
                }
            } catch (Exception e) {
                LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
                AlertFactory.getInstance().generateAlert(Alert.AlertType.WARNING, "Login Error!", "Please check your internet connection", "If the problem persist try again later.");
            }
        }
    }

    @FXML
    public boolean goSignUp() {
        Facade.getInstance().decorateView(ViewLayout.SIGNUP);
        return true;
    }

}
