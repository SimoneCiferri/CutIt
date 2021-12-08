package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class TopBarView extends Decorator {

    public TopBarView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.TOPBAR);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
