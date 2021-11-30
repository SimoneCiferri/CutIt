package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class HairdresserShopView extends Decorator {

    public HairdresserShopView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.HAIRDRESSERMANAGESHOPPAGE);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
