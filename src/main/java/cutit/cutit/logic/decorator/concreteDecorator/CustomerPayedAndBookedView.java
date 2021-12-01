package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class CustomerPayedAndBookedView extends Decorator {

    public CustomerPayedAndBookedView(ViewComponent view){

        super(view);
        try {
                super.loadXML(ViewLayout.CUSTOMERPAYEDANDBOOKED);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
