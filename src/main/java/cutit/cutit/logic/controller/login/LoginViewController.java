package cutit.cutit.logic.controller.login;

import cutit.cutit.logic.bean.CustomerBean;
import cutit.cutit.logic.bean.DeleteAppointmentBean;
import cutit.cutit.logic.bean.HairdresserBean;
import cutit.cutit.logic.bean.UserBean;
import cutit.cutit.logic.controller.topbarviewcontrollers.TopBarCustomerViewController;
import cutit.cutit.logic.controller.topbarviewcontrollers.TopBarHairdresserViewController;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.decorator.concreteDecorator.TopBarCustomerView;
import cutit.cutit.logic.decorator.concreteDecorator.TopBarHairdresserView;
import cutit.cutit.logic.facade.Facade;
import cutit.cutit.logic.factory.AlertFactory;
import cutit.cutit.logic.log.LogWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Objects;


public class LoginViewController {

    private UserBean userBean;
    private CustomerBean customerBean;
    private HairdresserBean hairdresserBean;
    private LoginController loginController;

    @FXML
    TextField tfUsername;

    @FXML
    PasswordField pfPassword;

    @FXML
    public void initialize(){
        userBean = new UserBean();
        loginController = new LoginController();
        System.out.println("CONTROLLER GRAFICO LOGINVIEWCONTROLLER");
    }

    @FXML
    public void tryLogin() {
        if(!Objects.equals(tfUsername.getText(), "") && !Objects.equals(pfPassword.getText(), "")){
            userBean.setUsername(tfUsername.getText());
            userBean.setPasswd(pfPassword.getText());
            try {
                this.userBean = loginController.login(this.userBean);
                if (this.userBean.getRole() == 0) {
                    Facade.getInstance().decorateView(ViewLayout.TOPBARCUSTOMER);
                    TopBarCustomerView view = (TopBarCustomerView) Facade.getInstance().getViewMap().get(ViewLayout.TOPBARCUSTOMER);
                    TopBarCustomerViewController viewController = (TopBarCustomerViewController) view.getLoadedViewController(ViewLayout.TOPBARCUSTOMER);
                    //setta il customer bean
                    viewController.startBean(this.userBean);
                }else{
                    this.hairdresserBean = loginController.getHairdresser(userBean);
                    Facade.getInstance().decorateView(ViewLayout.TOPBARHAIRDRESSER);
                    TopBarHairdresserView view = (TopBarHairdresserView) Facade.getInstance().getViewMap().get(ViewLayout.TOPBARHAIRDRESSER);
                    TopBarHairdresserViewController viewController = (TopBarHairdresserViewController) view.getLoadedViewController(ViewLayout.TOPBARHAIRDRESSER);
                    viewController.startBean(this.hairdresserBean);
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
