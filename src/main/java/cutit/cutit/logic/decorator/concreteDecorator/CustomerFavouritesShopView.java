package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class CustomerFavouritesShopView extends Decorator {

    public CustomerFavouritesShopView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.FAVSHOP);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
