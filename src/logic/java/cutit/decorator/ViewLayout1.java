package cutit.decorator;

import java.net.URL;

public enum ViewLayout1 {
    START("/cutit/cutit/fxml/start.fxml"), TOPBARCUSTOMER("/cutit/cutit/fxml/topbarcustomer.fxml"), TOPBARHAIRDRESSER("/cutit/cutit/fxml/topbarhairdresser.fxml"),
    HOME("/cutit/cutit/fxml/home.fxml"), LOGIN("/cutit/cutit/fxml/login.fxml"), TOPBAR("/cutit/cutit/fxml/topbar.fxml"),
    HAIRDRESSERAPPOINTMENTS("/cutit/cutit/fxml/hairdresserdeletebookedappointments.fxml"), SIGNUP("/cutit/cutit/fxml/signup.fxml"),
    FAVSHOP("/cutit/cutit/fxml/clientfavouritescs.fxml"), CUSTOMERAPPOINTMENTS("/cutit/cutit/fxml/customerappointments.fxml"),
    CUSTOMERPROMOTIONS("/cutit/cutit/fxml/customerpromotions.fxml"), HAIRDRESSERPROMOTIONS("/cutit/cutit/fxml/hairdressermanagepromotions.fxml"),
    HAIRDRESSERSERVICES("/cutit/cutit/fxml/hairdressermanageservices.fxml"),
    SHOPINFO("/cutit/cutit/fxml/shopinfo.fxml"), CUSTOMERRATESHOP("/cutit/cutit/fxml/clientrateshop.fxml"),
    CLIENTBOOKAPPOINTMENT("/cutit/cutit/fxml/customerbookappointment.fxml"),
    CUSTOMERAPPINFO("/cutit/cutit/fxml/customerappointmentinfo.fxml"),
    CUSTOMERPROMOTIONINFO("/cutit/cutit/fxml/customerpromotioninfo.fxml"),GMAPS("/cutit/cutit/fxml/gmaps.fxml"), HAIRDRESSERMANAGESHOP("/cutit/cutit/fxml/hairdressermanageshop.fxml"), PAYONLINEPAYPAL("/cutit/cutit/fxml/customerpayonlinepaypal.fxml");

    private String res;

    ViewLayout1(String res){
        this.res = res;
    }

    public static URL getPath(ViewLayout1 layout){
        return ViewLayout1.class.getResource(layout.res);
    }

}
