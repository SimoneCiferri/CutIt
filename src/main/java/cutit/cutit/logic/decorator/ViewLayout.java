package cutit.cutit.logic.decorator;

import java.net.URL;

public enum ViewLayout {
    START("/cutit/cutit/views/start.fxml"), TOPBAR("/cutit/cutit/views/topbar.fxml"),TOPBARCLIENT("/cutit/cutit/views/topbarclient.fxml"), TOPBARHAIRDRESSER("/cutit/cutit/views/topbarhairdresser.fxml"),
    HOME("/cutit/cutit/views/home.fxml"), UNLOGGEDPROMOTIONS("/cutit/cutit/views/unloggedpromotions.fxml"), LOGIN("/cutit/cutit/views/login.fxml"),
    HAIRDRESSERAPPOINTMENTS("/cutit/cutit/views/hairdresserappointments.fxml"), SIGNUP("/cutit/cutit/views/signup.fxml"),
    FAVSHOP("/cutit/cutit/views/clientfavouritescs.fxml"), APPCL("/cutit/cutit/views/clientappointments.fxml"), PROMOTIONCLIENT("/cutit/cutit/views/clientpromotions.fxml"),
    HAIRDRESSERPROMOTIONS("/cutit/cutit/views/hairdresserpromotions.fxml"), HAIRDRESSERSERVICES("/cutit/cutit/views/hairdresserservices.fxml"),
    HAIRDRESSERSHOP("/cutit/cutit/views/hairdressershop.fxml");

    private String res;

    private ViewLayout(String res){
        this.res = res;
    }

    public static URL getPath(ViewLayout layout){
        return ViewLayout.class.getResource(layout.res);
    }

}
