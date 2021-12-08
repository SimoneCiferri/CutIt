package cutit.cutit.logic.controller.navigationViewController;

import cutit.cutit.logic.controller.applController.LoginController;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;

public class SignUpViewController {

    private LoginController loginController;

    @FXML
    public void initialize(){
        loginController = new LoginController();
        System.out.println("CONTROLLER GRAFICO SIGNUPVIEWCONTROLLER");
    }

    @FXML
    public boolean trySignUpCustomer(){
        if(loginController.signUpCustomer()){
            Facade.getInstance().decorateView(ViewLayout.LOGIN);
        }
        return true;
    }

    @FXML
    public boolean trySignUpHair(){
        if(loginController.signUpHair()){
            Facade.getInstance().decorateView(ViewLayout.LOGIN);
        }
        return true;
    }

}
