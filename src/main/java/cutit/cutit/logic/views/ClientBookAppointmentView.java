package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class ClientBookAppointmentView extends Decorator {

    public ClientBookAppointmentView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.CLIENTBOOKAPPOINTMENT);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
