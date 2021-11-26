package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class HairdresserAddPromotionView extends Decorator {

    public HairdresserAddPromotionView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.HAIRDRESSERADDPROM);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
