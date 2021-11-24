package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class TopBarClientView extends Decorator {

    public TopBarClientView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.TOPBARCLIENT);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
