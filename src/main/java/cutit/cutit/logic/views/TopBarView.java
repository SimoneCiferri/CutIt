package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class TopBarView extends Decorator {


    public TopBarView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.TOPBAR);
            System.out.println("tb");
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
