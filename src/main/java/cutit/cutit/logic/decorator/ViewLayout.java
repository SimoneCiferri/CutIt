package cutit.cutit.logic.decorator;

import java.net.URL;

public enum ViewLayout {
    START("/cutit/cutit/views/start.fxml"), TOPBAR("/cutit/cutit/views/topbar.fxml"),TOPBARCLIENT("/cutit/cutit/views/topbarclient.fxml"), TOPBARHAIRDRESSER("/cutit/cutit/views/topbarhairdresser.fxml"),
    HOME("/cutit/cutit/views/home.fxml"), UNLOGGEDPROMOTIONS("/cutit/cutit/views/unloggedpromotions.fxml"), LOGIN("/cutit/cutit/views/login.fxml"),
    HAIRDRESSERAPPOINTMENTS("/cutit/cutit/views/hairdresserappointments.fxml"), SIGNUP("/cutit/cutit/views/signup.fxml"),
    FAVSHOP("/cutit/cutit/views/clientfavouritescs.fxml"), APPCL("/cutit/cutit/views/clientappointments.fxml"), PROMOTIONCLIENT("/cutit/cutit/views/clientpromotions.fxml"),
    HAIRDRESSERPROMOTIONS("/cutit/cutit/views/hairdresserpromotions.fxml"), HAIRDRESSERSERVICES("/cutit/cutit/views/hairdresserservices.fxml"),
    HAIRDRESSERSHOP("/cutit/cutit/views/hairdressershop.fxml"), SHOPINFO("/cutit/cutit/views/shopinfo.fxml"), CLIENTRATESHOP("/cutit/cutit/views/clientrateshop.fxml"),
    CLIENTBOOKAPPOINTMENT("/cutit/cutit/views/clientbookappointment.fxml"), CLIENTBOOKAPPFORM("/cutit/cutit/views/clientbookappform.fxml"),
    HAIRDRESSERAPPINFO("/cutit/cutit/views/hairdresserappointmentinfo.fxml"), HAIRDRESSERPROMINFO("/cutit/cutit/views/hairdresserpromotioninfo.fxml"),
    CLIENTAPPINFO("/cutit/cutit/views/clientappointmentinfo.fxml"), HAIRDRESSERSERVICEINFO("/cutit/cutit/views/hairdresserserviceinfo.fxml"),
    CLIENTPROMOTIONINFO("/cutit/cutit/views/clientpromotioninfo.fxml"), HAIRDRESSERADDPROM("/cutit/cutit/views/hairdresseraddpromotion.fxml"),
    HAIRDRESSERADDSERVICE("/cutit/cutit/views/hairdresseraddservice.fxml");

    private String res;

    private ViewLayout(String res){
        this.res = res;
    }

    public static URL getPath(ViewLayout layout){
        return ViewLayout.class.getResource(layout.res);
    }

}
