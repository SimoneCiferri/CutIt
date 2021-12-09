package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.exception.ExceptionHandler;

import java.io.IOException;

public class ClientBookAppointmentView extends Decorator {

    public ClientBookAppointmentView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.CLIENTBOOKAPPOINTMENT);
        } catch (IOException e){
            ExceptionHandler.getInstance().handleException(e);
        }

    }

}
