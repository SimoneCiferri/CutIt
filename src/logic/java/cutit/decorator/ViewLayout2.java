package cutit.decorator;

import java.net.URL;

public enum ViewLayout2 {
    START2("/cutit/cutit/fxml/fxml2/start2.fxml"), LOGIN2("/cutit/cutit/fxml/fxml2/login2.fxml"), LEFTBAR("/cutit/cutit/fxml/fxml2/leftbar.fxml"),
    LEFTBARCUSTOMER("/cutit/cutit/fxml/fxml2/leftbarcustomer.fxml"), LEFTBARHAIRDRESSER("/cutit/cutit/fxml/fxml2/leftbarhairdresser.fxml"),
    HOME2("/cutit/cutit/fxml/fxml2/home2.fxml"), CUSTOMERAPPOINTMENTS("/cutit/cutit/fxml/fxml2/customerappointments2.fxml"),
    CUSTOMERBOOKAPPOINTMENT("/cutit/cutit/fxml/fxml2/customerbookappointment2.fxml"),CUSTOMERFAVOURITESSHOP("/cutit/cutit/fxml/fxml2/clientfavouritescs2.fxml"),
    CUSTOMERPROMOTIONINFO("/cutit/cutit/fxml/fxml2/customerpromotioninfo2.fxml"),CUSTOMERPROMOTION("/cutit/cutit/fxml/fxml2/customerpromotions2.fxml"),
    CUSTOMERRATESHOP("/cutit/cutit/fxml/fxml2/customerrateshop2.fxml"),HAIRDRESSERDELETEBOOKEDAPPOINTMENTS("/cutit/cutit/fxml/fxml2/hairdresserdeletebookedappointments2.fxml"),
    HAIRDRESSERMANAGEPROMOTIONS("/cutit/cutit/fxml/fxml2/hairdressermanagepromotions2.fxml"),HAIRDRESSERMANAGESERVICES("/cutit/cutit/fxml/fxml2/hairdressermanageservices2.fxml"),
    HAIRDRESSERMANAGESHOPPAGE("/cutit/cutit/fxml/fxml2/hairdressermanageshop2.fxml"),SHOPINFOVIEW2("/cutit/cutit/fxml/fxml2/shopinfo2.fxml");

    private String res;

    ViewLayout2(String res){
        this.res = res;
    }

    public static URL getPath(ViewLayout2 layout){
        return ViewLayout2.class.getResource(layout.res);
    }

}
