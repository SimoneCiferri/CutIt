package cutit.controller.getlocationdirections;

import cutit.bean.ShopBeanInterface;

public class GetLocationDirectionsController {

    public void getDirection(GetLocationDirectionsGoogleMapsViewControllerInterface viewController, ShopBeanInterface bean){
        viewController.setAddress(bean.getShopAddress());
    }

}
