package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class ShopInfoView extends Decorator {

    public ShopInfoView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.SHOPINFO);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
