package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class SignUpView extends Decorator {

    public SignUpView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.SIGNUP);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
