package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class TopBarHairdresserView extends Decorator {

    public TopBarHairdresserView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.TOPBARHAIRDRESSER);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
