package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class HomeView  extends Decorator {

    public HomeView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.HOME);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
