package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class HairdresserAppointmentInfoView extends Decorator {

    public HairdresserAppointmentInfoView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.HAIRDRESSERAPPINFO);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
