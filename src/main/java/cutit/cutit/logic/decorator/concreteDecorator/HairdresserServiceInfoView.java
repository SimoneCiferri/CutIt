package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class HairdresserServiceInfoView extends Decorator {

    public HairdresserServiceInfoView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.HAIRDRESSERDELETESERVICE);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
