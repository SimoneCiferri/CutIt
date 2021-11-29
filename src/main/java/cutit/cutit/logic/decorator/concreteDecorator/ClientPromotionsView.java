package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class ClientPromotionsView extends Decorator {

    public ClientPromotionsView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.PROMOTIONCLIENT);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
