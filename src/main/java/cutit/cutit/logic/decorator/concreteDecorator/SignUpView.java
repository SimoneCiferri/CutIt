package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.exception.ExceptionHandler;

import java.io.IOException;

public class SignUpView extends Decorator {

    public SignUpView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.SIGNUP);
        } catch (Exception e){
            ExceptionHandler.getInstance().handleException(e);
        }

    }

}
