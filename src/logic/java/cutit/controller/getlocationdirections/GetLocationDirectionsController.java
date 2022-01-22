package cutit.controller.getlocationdirections;

import cutit.bean.ShopBean;

public class GetLocationDirectionsController {

    public void getDirection(GetLocationDirectionsGoogleMapsViewControllerInterface viewController, ShopBean bean){
        viewController.setAddress(bean.getShopAddress());
    }

}
