package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class HairdresserPromotionInfoView extends Decorator {

    public HairdresserPromotionInfoView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.HAIRDRESSERPROMINFO);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
