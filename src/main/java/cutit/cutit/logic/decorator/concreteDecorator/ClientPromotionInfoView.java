package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class ClientPromotionInfoView extends Decorator {

    public ClientPromotionInfoView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.CLIENTPROMOTIONINFO);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}