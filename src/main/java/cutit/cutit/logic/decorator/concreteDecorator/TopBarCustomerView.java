package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class TopBarCustomerView extends Decorator {

    public TopBarCustomerView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.TOPBARCUSTOMER);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
