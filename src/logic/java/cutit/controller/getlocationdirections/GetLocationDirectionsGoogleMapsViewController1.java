package cutit.controller.getlocationdirections;


import com.dlsc.gmapsfx.GoogleMapView;
import com.dlsc.gmapsfx.MapComponentInitializedListener;
import com.dlsc.gmapsfx.javascript.object.*;
import com.dlsc.gmapsfx.service.directions.*;
import com.dlsc.gmapsfx.service.geocoding.*;
import cutit.factory.AlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class GetLocationDirectionsGoogleMapsViewController1 implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback, GetLocationDirectionsGoogleMapsViewControllerInterface {

    protected GoogleMap map;
    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;
    protected URL url;
    protected ResourceBundle rb;
    protected String address;

    protected GeocodingService geocodingService;

    @FXML
    protected GoogleMapView mapView;


    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {

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

        geocodingService = new GeocodingService();
        MarkerOptions markerOptions = new MarkerOptions();
        System.out.println("Geocoding on " + address);
        geocodingService.geocode(address, (geocodingResults, geocoderStatus) -> {
            LatLong latLong1;
            if( geocoderStatus == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, "Warning", "No matching address found");
                alert.showAndWait();
                return;
            } else if( geocodingResults.length > 1 ) {
                System.out.println("1 risultato");
                latLong1 = new LatLong(geocodingResults[0].getGeometry().getLocation().getLatitude(), geocodingResults[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong1 = new LatLong(geocodingResults[0].getGeometry().getLocation().getLatitude(), geocodingResults[0].getGeometry().getLocation().getLongitude());
                System.out.println("Più risultati");
            }
            map.setCenter(latLong1);
            markerOptions.position(latLong);
            Marker marker = new Marker(markerOptions);
            map.addMarker(marker);
        });

        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
    }

    public void setAddress(String address){
        this.address = address;
        mapView.addMapInitializedListener(this);
    }




}
