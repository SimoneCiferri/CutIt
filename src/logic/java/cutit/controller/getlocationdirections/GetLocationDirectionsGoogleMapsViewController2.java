package cutit.controller.getlocationdirections;


import com.dlsc.gmapsfx.GoogleMapView;
import com.dlsc.gmapsfx.MapComponentInitializedListener;
import com.dlsc.gmapsfx.javascript.object.*;
import com.dlsc.gmapsfx.service.directions.DirectionStatus;
import com.dlsc.gmapsfx.service.directions.DirectionsResult;
import com.dlsc.gmapsfx.service.directions.DirectionsService;
import com.dlsc.gmapsfx.service.directions.DirectionsServiceCallback;
import com.dlsc.gmapsfx.service.geocoding.GeocoderStatus;
import com.dlsc.gmapsfx.service.geocoding.GeocodingService;
import cutit.factory.AlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class GetLocationDirectionsGoogleMapsViewController2 implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback, GetLocationDirectionsGoogleMapsViewControllerInterface {

    protected GoogleMap map;
    protected URL url;
    protected ResourceBundle rb;
    protected String address;

    @FXML
    protected GoogleMapView mapView;


    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {
        //Method not implemented
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.url = url;
        this.rb = rb;
    }

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();
        LatLong latLong = new LatLong(41.853910, 12.624290);
        options.center(latLong)
                .zoomControl(true)
                .zoom(12)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        map = mapView.createMap(options);
    }

    public void setAddress(String address){
        this.address = address;
        mapView.addMapInitializedListener(this);
    }

}
