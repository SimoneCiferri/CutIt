package cutit.cutit.logic.controller.getlocationdirections;


import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class GetLocationDirectionsGoogleMapsViewController implements Initializable, MapComponentInitializedListener{

    private GoogleMap map;

    @FXML
    private GoogleMapView mapView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized() {
        LatLong latLong = new LatLong(41.9102415, 12.3959136);
        MapOptions mapOptions = new MapOptions();
        mapOptions.center(latLong)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);
        map = mapView.createMap(mapOptions);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLong);
        Marker marker = new Marker(markerOptions);
        map.addMarker(marker);
/*
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>ROMA</h2>Location di Roma<br>");
        InfoWindow infoWindow = new InfoWindow(infoWindowOptions);
        infoWindow.open(map, marker);

 */
    }
}
