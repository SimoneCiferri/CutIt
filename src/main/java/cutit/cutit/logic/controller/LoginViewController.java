package cutit.cutit.logic.controller;

import cutit.cutit.logic.bean.DeleteAppointmentBean;
import cutit.cutit.logic.controller.navigationViewController.TopBarHairdresserViewController;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.decorator.concreteDecorator.TopBarHairdresserView;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;


public class LoginViewController {

    @FXML
    public boolean tryLogin() {
        Facade.getInstance().decorateView(ViewLayout.TOPBARCLIENT);
        Facade.getInstance().decorateView(ViewLayout.HOME);
        //passa la bean al controller della topBar
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
        TopBarHairdresserView view = (TopBarHairdresserView) Facade.getInstance().getViewMap().get(ViewLayout.TOPBARHAIRDRESSER);
        TopBarHairdresserViewController viewController = (TopBarHairdresserViewController) view.getLoadedViewController(ViewLayout.TOPBARHAIRDRESSER);
        viewController.startBean(new DeleteAppointmentBean());
        //passa la bean al controller della topBar così può fillare la view. La bean adesso viene creata a caso
        return true;
    }

    @FXML
    public boolean backUnlogged(){
        Facade.getInstance().decorateView(ViewLayout.TOPBAR);
        Facade.getInstance().decorateView(ViewLayout.HOME);
        //passa la bean al controller della topBar
        return true;

    }

}
