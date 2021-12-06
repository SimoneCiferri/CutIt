package cutit.cutit.logic.controller.navigationViewController;

import cutit.cutit.logic.bean.CustomerBean;
import cutit.cutit.logic.controller.applController.LoginController;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;

public class SignUpViewController {

    private CustomerBean customerBean;
    private LoginController loginController;

    @FXML
    public void initialize(){
        customerBean = new CustomerBean();
        loginController = new LoginController();
    }

    @FXML
    public boolean trySignUp(){
        if(loginController.signUp(customerBean)){
            Facade.getInstance().decorateView(ViewLayout.LOGIN);
        }
        return true;
    }

}
