package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class ClientPromotionView extends Decorator {

    public ClientPromotionView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.PROMOTIONCLIENT);
            System.out.println("prommm");
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
