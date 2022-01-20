package cutit.decorator.concrete_decorator;

import cutit.decorator.Decorator;
import cutit.decorator.ViewComponent1;
import cutit.decorator.ViewLayout1;

import java.io.IOException;

public class CustomerPayOnlinePayPalView1 extends Decorator {

    public CustomerPayOnlinePayPalView1(ViewComponent1 view) {
        super(view);
        try {
            super.loadXML1(ViewLayout1.PAYONLINEPAYPAL);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}