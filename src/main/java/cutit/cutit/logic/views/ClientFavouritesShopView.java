package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;

public class ClientFavouritesShopView extends Decorator {

    public ClientFavouritesShopView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.FAVSHOP);
            System.out.println("favvv");
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
