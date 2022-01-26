package cutit.controller.login;

import cutit.bean.*;
import cutit.bean.ShopBean;
import cutit.controller.leftbarviewcontrollers.LeftBarCustomerViewController;
import cutit.controller.leftbarviewcontrollers.LeftBarHairdresserViewController;
import cutit.decorator.ViewLayout2;
import cutit.decorator.concrete_decorator2.LeftBarCustomerView;
import cutit.decorator.concrete_decorator2.LeftBarHairdresserView;
import cutit.facade.Facade2;
import cutit.factory.AlertFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Objects;

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
    private ChoiceBox<?> cbGenderCustomer;

    @FXML
    public void initialize(){
        userBeanSecondUI = new UserBeanUQ();
        customerBeanSecondUI = new CustomerBeanUQ();
        hairdresserBeanSecondUI = new HairdresserBeanUQ();
        shopBeanSecondUI = new ShopBean();
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
                        viewController.startBean(shopBeanSecondUI);
                    }
                }
            } catch (Exception e) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, "Login Error!", "Please check your internet connection", "If the problem persist try again later.");
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
