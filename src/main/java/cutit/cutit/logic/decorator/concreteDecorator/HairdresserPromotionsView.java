package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class HairdresserPromotionsView extends Decorator {

    public HairdresserPromotionsView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.HAIRDRESSERPROMOTIONS);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
