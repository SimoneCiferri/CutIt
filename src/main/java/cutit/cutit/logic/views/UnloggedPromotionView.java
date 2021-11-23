package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class UnloggedPromotionView extends Decorator {

    public UnloggedPromotionView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.UNLOGGEDPROMOTIONS);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
