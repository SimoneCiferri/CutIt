package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class HairdresserAppointmentsView extends Decorator {

    public HairdresserAppointmentsView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.HAIRDRESSERAPPOINTMENTS);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
