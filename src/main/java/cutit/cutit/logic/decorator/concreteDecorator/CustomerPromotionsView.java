package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.exception.ExceptionHandler;

import java.io.IOException;

public class CustomerPromotionsView extends Decorator {

    public CustomerPromotionsView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.CUSTOMERPROMOTIONS);
        } catch (IOException e){
            ExceptionHandler.getInstance().handleException(e);
        }

    }

}