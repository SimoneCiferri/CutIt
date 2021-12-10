package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.exception.ExceptionHandler;

import java.io.IOException;

public class HairdresserShopView extends Decorator {

    public HairdresserShopView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.HAIRDRESSERMANAGESHOPPAGE);
        } catch (Exception e){
            ExceptionHandler.getInstance().handleException(e);
        }

    }

}
