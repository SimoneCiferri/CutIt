package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class ClientAppointmentInfoView extends Decorator {

    public ClientAppointmentInfoView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.CLIENTAPPINFO);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
