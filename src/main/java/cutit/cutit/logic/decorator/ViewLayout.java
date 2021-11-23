package cutit.cutit.logic.decorator;

import java.net.URL;

public enum ViewLayout {
    START("/cutit/cutit/views/start.fxml"), SARTCLIENT("/cutit/cutit/views/startclient.fxml"), STARTHAIRDRESSER("/cutit/cutit/views/starthairdresser.fxml"),
    HOME("/cutit/cutit/views/home.fxml"), UNLOGGEDPROMOTIONS("/cutit/cutit/views/unloggedpromotions.fxml");

    private String res;

    private ViewLayout(String res){
        this.res = res;
    }

    public static URL getPath(ViewLayout layout){
        return ViewLayout.class.getResource(layout.res);
    }

}
