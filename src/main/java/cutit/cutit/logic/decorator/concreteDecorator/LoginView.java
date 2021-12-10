package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.exception.ExceptionHandler;

import java.io.IOException;

public class LoginView extends Decorator {

    public LoginView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.LOGIN);
        } catch (Exception e){
            ExceptionHandler.getInstance().handleException(e);
        }

    }

}
