package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class HairdresserServiceInfoView extends Decorator {

    public HairdresserServiceInfoView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.HAIRDRESSERSERVICEINFO);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
