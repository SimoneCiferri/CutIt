package cutit.controller.getlocationdirections;

public class GetLocationDirectionsController {

    public void getDirection(GetLocationDirectionsGoogleMapsViewControllerInterface viewController, String address){
        viewController.setAddress(address);
    }

}
