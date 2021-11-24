package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class ClientAppointmentsView extends Decorator {

    public ClientAppointmentsView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.APPCL);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
