package cutit.cutit.logic.control;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;


public class LoginViewController {

    @FXML
    public boolean tryLogin() {
        Facade.getInstance().decorateView(ViewLayout.TOPBARCLIENT);
        Facade.getInstance().decorateView(ViewLayout.HOME);
        return true;
    }

    @FXML
    public boolean goSignUp() {
        Facade.getInstance().decorateView(ViewLayout.SIGNUP);
        return true;
    }

    @FXML
    public boolean hairLogin() {
        Facade.getInstance().decorateView(ViewLayout.TOPBARHAIRDRESSER);
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERAPPOINTMENTS);
        return true;
    }

}
