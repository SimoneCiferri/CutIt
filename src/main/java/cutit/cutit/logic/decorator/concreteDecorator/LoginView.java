package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class LoginView extends Decorator {

    public LoginView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.LOGIN);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
