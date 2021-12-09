package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.exception.ExceptionHandler;

import java.io.IOException;

public class TopBarView extends Decorator {

    public TopBarView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.TOPBAR);
        } catch (IOException e){
            ExceptionHandler.getInstance().handleException(e);
        }

    }

}
