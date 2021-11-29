package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class HairdresserAddServiceView extends Decorator {

    public HairdresserAddServiceView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.HAIRDRESSERADDSERVICE);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
