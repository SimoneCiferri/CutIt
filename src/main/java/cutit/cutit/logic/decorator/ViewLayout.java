package cutit.cutit.logic.decorator;

import java.net.URL;

public enum ViewLayout {
    START("/cutit/cutit/fxml/start.fxml"), TOPBARCUSTOMER("/cutit/cutit/fxml/topbarcustomer.fxml"), TOPBARHAIRDRESSER("/cutit/cutit/fxml/topbarhairdresser.fxml"),
    HOME("/cutit/cutit/fxml/home.fxml"), LOGIN("/cutit/cutit/fxml/login.fxml"), TOPBAR("/cutit/cutit/fxml/topbar.fxml"),
    HAIRDRESSERAPPOINTMENTS("/cutit/cutit/fxml/hairdresserdeletebookedappointments.fxml"), SIGNUP("/cutit/cutit/fxml/signup.fxml"),
    FAVSHOP("/cutit/cutit/fxml/clientfavouritescs.fxml"), CUSTOMERAPPOINTMENTS("/cutit/cutit/fxml/clientappointments.fxml"),
    CUSTOMERPROMOTIONS("/cutit/cutit/fxml/clientpromotions.fxml"), HAIRDRESSERPROMOTIONS("/cutit/cutit/fxml/hairdressermanagepromotions.fxml"),
    HAIRDRESSERSERVICES("/cutit/cutit/fxml/hairdressermanageservices.fxml"),
    SHOPINFO("/cutit/cutit/fxml/shopinfo.fxml"), CUSTOMERRATESHOP("/cutit/cutit/fxml/clientrateshop.fxml"),
    CLIENTBOOKAPPOINTMENT("/cutit/cutit/fxml/customerbookappointment.fxml"),
    CLIENTAPPINFO("/cutit/cutit/fxml/clientappointmentinfo.fxml"),
    CLIENTPROMOTIONINFO("/cutit/cutit/fxml/clientpromotioninfo.fxml"),GMAPS("/cutit/cutit/fxml/gmaps.fxml"), HAIRDRESSERMANAGESHOP("/cutit/cutit/fxml/hairdressermanageshop.fxml");

    private String res;

    ViewLayout(String res){
        this.res = res;
    }

    public static URL getPath(ViewLayout layout){
        return ViewLayout.class.getResource(layout.res);
    }

}
