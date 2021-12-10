package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.exception.ExceptionHandler;

import java.io.IOException;

public class HairdresserServicesView extends Decorator {

    public HairdresserServicesView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.HAIRDRESSERSERVICES);
        } catch (Exception e){
            ExceptionHandler.getInstance().handleException(e);
        }

    }

}
