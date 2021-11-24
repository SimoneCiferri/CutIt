package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class ClientRateShopView extends Decorator {

    public ClientRateShopView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.CLIENTRATESHOP);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
