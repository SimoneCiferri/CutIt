package cutit.decorator.concreteDecorator;

import cutit.decorator.Decorator;
import cutit.decorator.ViewComponent;
import cutit.decorator.ViewLayout;

import java.io.IOException;

public class CustomerPayOnlinePayPalView extends Decorator {

    public CustomerPayOnlinePayPalView(ViewComponent view) {
        super(view);
        try {
            super.loadXML(ViewLayout.PAYONLINEPAYPAL);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}