package cutit.controller.getlocationdirections;

import com.dlsc.gmapsfx.GoogleMapView;
import com.dlsc.gmapsfx.MapComponentInitializedListener;
import com.dlsc.gmapsfx.javascript.object.*;
import com.dlsc.gmapsfx.service.directions.DirectionStatus;
import com.dlsc.gmapsfx.service.directions.DirectionsResult;
import com.dlsc.gmapsfx.service.directions.DirectionsServiceCallback;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class GetLocationDirectionsGoogleMapsViewController2 implements Initializable, MapComponentInitializedListener, GetLocationDirectionsGoogleMapsViewControllerInterface {

    protected GoogleMap map;
    protected URL url;
    protected ResourceBundle rb;
    protected String address;

    @FXML
    protected GoogleMapView mapView2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.url = url;
        this.rb = rb;
    }

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();
        LatLong latLong = new LatLong(41.853911, 12.624291);
        options.center(latLong)
                .zoomControl(true)
                .zoom(12)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.HYBRID);
        map = mapView2.createMap(options);
    }

    public void setAddress(String address){
        this.address = address;
        mapView2.addMapInitializedListener(this);
    }
}
