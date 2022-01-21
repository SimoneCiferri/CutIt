package cutit.decorator;

import java.net.URL;

public enum ViewLayout1 {
    START("/cutit/cutit/fxml/fxml1/start.fxml"), TOPBARCUSTOMER("/cutit/cutit/fxml/fxml1/topbarcustomer.fxml"), TOPBARHAIRDRESSER("/cutit/cutit/fxml/fxml1/topbarhairdresser.fxml"),
    HOME("/cutit/cutit/fxml/fxml1/home.fxml"), LOGIN("/cutit/cutit/fxml/fxml1/login.fxml"), TOPBAR("/cutit/cutit/fxml/fxml1/topbar.fxml"),
    HAIRDRESSERAPPOINTMENTS("/cutit/cutit/fxml/fxml1/hairdresserdeletebookedappointments.fxml"), SIGNUP("/cutit/cutit/fxml/fxml1/signup.fxml"),
    FAVSHOP("/cutit/cutit/fxml/fxml1/clientfavouritescs.fxml"), CUSTOMERAPPOINTMENTS("/cutit/cutit/fxml/fxml1/customerappointments.fxml"),
    CUSTOMERPROMOTIONS("/cutit/cutit/fxml/fxml1/customerpromotions.fxml"), HAIRDRESSERPROMOTIONS("/cutit/cutit/fxml/fxml1/hairdressermanagepromotions.fxml"),
    HAIRDRESSERSERVICES("/cutit/cutit/fxml/fxml1/hairdressermanageservices.fxml"),
    SHOPINFO("/cutit/cutit/fxml/fxml1/shopinfo.fxml"), CUSTOMERRATESHOP("/cutit/cutit/fxml/fxml1/clientrateshop.fxml"),
    CUSTOMERBOOKAPPOINTMENT("/cutit/cutit/fxml/fxml1/customerbookappointment.fxml"),
    CUSTOMERAPPINFO("/cutit/cutit/fxml/fxml1/customerappointmentinfo.fxml"),
    CUSTOMERPROMOTIONINFO("/cutit/cutit/fxml/fxml1/customerpromotioninfo.fxml"),GMAPS("/cutit/cutit/fxml/fxml1/gmaps.fxml"), HAIRDRESSERMANAGESHOP("/cutit/cutit/fxml/fxml1/hairdressermanageshop.fxml"), PAYONLINEPAYPAL("/cutit/cutit/fxml/fxml1/customerpayonlinepaypal.fxml");

    private String res;

    ViewLayout1(String res){
        this.res = res;
    }

    public static URL getPath(ViewLayout1 layout){
        return ViewLayout1.class.getResource(layout.res);
    }

}
