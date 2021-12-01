package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class ClientRateShopView extends Decorator {

    public ClientRateShopView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.CUSTOMERRATESHOP);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
