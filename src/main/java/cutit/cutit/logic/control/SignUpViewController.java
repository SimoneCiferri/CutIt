package cutit.cutit.logic.control;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;

public class SignUpViewController {

    public boolean trySignUp(){
        Facade.getInstance().decorateView(ViewLayout.HOME);
        Facade.getInstance().decorateView(ViewLayout.TOPBAR);
        return true;
    }

}
