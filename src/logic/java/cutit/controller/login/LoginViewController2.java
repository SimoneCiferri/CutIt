package cutit.controller.login;

import cutit.bean.*;
import cutit.bean.ShopBeanUQ;
import cutit.controller.leftbarviewcontrollers.LeftBarCustomerViewController;
import cutit.controller.leftbarviewcontrollers.LeftBarHairdresserViewController;
import cutit.decorator.ViewLayout2;
import cutit.decorator.concrete_decorator2.LeftBarCustomerView;
import cutit.decorator.concrete_decorator2.LeftBarHairdresserView;
import cutit.facade.Facade2;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Objects;

public class LoginViewController2 {

    private LoginController loginController;
    private UserBean userBeanSecondUI;
    private CustomerBean customerBeanSecondUI;
    private HairdresserBean hairdresserBeanSecondUI;
    private ShopBean shopBeanSecondUI;

    @FXML
    private TextField tfUsernameLogin, tfNameAndSurnameCustomer, tfBirthDateCustomer, tfEmailCustomer, tfNameAndSurnameHairdresser, tfEmailHairdresser,
            tfPivaHairdresser, tfShopNameHairdresser;

    @FXML
    private PasswordField pfPasswordLogin, pfConfirmPasswordCustomer, pfPasswordCustomer, pfPasswordHairdresser, pfConfirmPasswordHairdresser;

    @FXML
    private ChoiceBox<?> cbGenderCustomer;

    @FXML
    public void initialize(){
        System.out.println(" Starting ---> LoginViewController2");
        userBeanSecondUI = new UserBeanUQ();
        customerBeanSecondUI = new CustomerBeanUQ();
        hairdresserBeanSecondUI = new HairdresserBeanUQ();
        shopBeanSecondUI = new ShopBeanUQ();
        loginController = new LoginController();
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
                        viewController.startBean(hairdresserBeanSecondUI, shopBeanSecondUI);
                    }
                }
            } catch (Exception e) {
                Alert alert = AlertFactory.getInstance().generateAlert(Alert.AlertType.WARNING, "Login Error!", "Please check your internet connection", "If the problem persist try again later.");
                alert.showAndWait();
            }
        }
    }

    private boolean credentialNotNull(String username, String psswd){
        return (!Objects.equals(username, "") && !Objects.equals(psswd, ""));
    }

    @FXML
    public boolean signUpCustomer() {
        return true;
    }

    @FXML
    public boolean signUpHairdresser() {
        return true;
    }

}
