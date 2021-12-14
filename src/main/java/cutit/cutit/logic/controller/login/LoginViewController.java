package cutit.cutit.logic.controller.login;

import cutit.cutit.logic.bean.CustomerBean;
import cutit.cutit.logic.bean.DeleteAppointmentBean;
import cutit.cutit.logic.bean.UserBean;
import cutit.cutit.logic.controller.topbarviewcontrollers.TopBarCustomerViewController;
import cutit.cutit.logic.controller.topbarviewcontrollers.TopBarHairdresserViewController;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.decorator.concreteDecorator.TopBarCustomerView;
import cutit.cutit.logic.decorator.concreteDecorator.TopBarHairdresserView;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginViewController {

    private UserBean userBean;
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
        userBean.setUsername(tfUsername.getText());
        userBean.setPasswd(pfPassword.getText());
        if(loginController.login(this.userBean)){
            Facade.getInstance().decorateView(ViewLayout.TOPBARCUSTOMER);
            TopBarCustomerView view = (TopBarCustomerView) Facade.getInstance().getViewMap().get(ViewLayout.TOPBARCUSTOMER);
            TopBarCustomerViewController viewController = (TopBarCustomerViewController) view.getLoadedViewController(ViewLayout.TOPBARCUSTOMER);
            viewController.startBean(new CustomerBean());
            //passa la bean al controller della topBar
        }
    }

    @FXML
    public boolean goSignUp() {
        Facade.getInstance().decorateView(ViewLayout.SIGNUP);
        return true;
    }

    @FXML
    public boolean hairLogin() {
        Facade.getInstance().decorateView(ViewLayout.TOPBARHAIRDRESSER);
        TopBarHairdresserView view = (TopBarHairdresserView) Facade.getInstance().getViewMap().get(ViewLayout.TOPBARHAIRDRESSER);
        TopBarHairdresserViewController viewController = (TopBarHairdresserViewController) view.getLoadedViewController(ViewLayout.TOPBARHAIRDRESSER);
        viewController.startBean(new DeleteAppointmentBean());
        //passa la bean al controller della topBar così può fillare la view. La bean adesso viene creata a caso!
        return true;
    }

}
