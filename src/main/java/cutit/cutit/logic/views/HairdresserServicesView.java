package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class HairdresserServicesView extends Decorator {

    public HairdresserServicesView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.HAIRDRESSERSERVICES);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
