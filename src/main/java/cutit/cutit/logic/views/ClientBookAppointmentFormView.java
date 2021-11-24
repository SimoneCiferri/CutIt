package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class ClientBookAppointmentFormView extends Decorator {

    public ClientBookAppointmentFormView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.CLIENTBOOKAPPFORM);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
