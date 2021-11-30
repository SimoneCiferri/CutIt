package cutit.cutit.logic.decorator;

import java.net.URL;

public enum ViewLayout {
    START("/cutit/cutit/fxml/start.fxml"), TOPBAR("/cutit/cutit/fxml/topbar.fxml"),TOPBARCLIENT("/cutit/cutit/fxml/topbarclient.fxml"), TOPBARHAIRDRESSER("/cutit/cutit/fxml/topbarhairdresser.fxml"),
    HOME("/cutit/cutit/fxml/home.fxml"), UNLOGGEDPROMOTIONS("/cutit/cutit/fxml/unloggedpromotions.fxml"), LOGIN("/cutit/cutit/fxml/login.fxml"),
    HAIRDRESSERAPPOINTMENTS("/cutit/cutit/fxml/hairdresserappointments.fxml"), SIGNUP("/cutit/cutit/fxml/signup.fxml"),
    FAVSHOP("/cutit/cutit/fxml/clientfavouritescs.fxml"), APPCL("/cutit/cutit/fxml/clientappointments.fxml"), PROMOTIONCLIENT("/cutit/cutit/fxml/clientpromotions.fxml"),
    HAIRDRESSERPROMOTIONS("/cutit/cutit/fxml/hairdresserpromotions.fxml"), HAIRDRESSERSERVICES("/cutit/cutit/fxml/hairdresserservices.fxml"),
    HAIRDRESSERMANAGESHOPPAGE("/cutit/cutit/fxml/hairdressermanageshoppage.fxml"), SHOPINFO("/cutit/cutit/fxml/shopinfo.fxml"), CLIENTRATESHOP("/cutit/cutit/fxml/clientrateshop.fxml"),
    CLIENTBOOKAPPOINTMENT("/cutit/cutit/fxml/clientbookappointment.fxml"), CLIENTBOOKAPPFORM("/cutit/cutit/fxml/clientbookappform.fxml"),
    HAIRDRESSERDELETEBOOKEDAPP("/cutit/cutit/fxml/hairdresserdeleteappointment.fxml"), HAIRDRESSERPROMINFO("/cutit/cutit/fxml/hairdresserpromotioninfo.fxml"),
    CLIENTAPPINFO("/cutit/cutit/fxml/clientappointmentinfo.fxml"), HAIRDRESSERSERVICEINFO("/cutit/cutit/fxml/hairdresserserviceinfo.fxml"),
    CLIENTPROMOTIONINFO("/cutit/cutit/fxml/clientpromotioninfo.fxml"), HAIRDRESSERADDPROM("/cutit/cutit/fxml/hairdresseraddpromotion.fxml"),
    HAIRDRESSERADDSERVICE("/cutit/cutit/fxml/hairdresseraddservice.fxml");

    private String res;

    private ViewLayout(String res){
        this.res = res;
    }

    public static URL getPath(ViewLayout layout){
        return ViewLayout.class.getResource(layout.res);
    }

}
