package cutit.decorator;

import java.net.URL;

public enum ViewLayout2 {
    START2("/cutit/cutit/fxml/fxml2/start2.fxml"), LOGIN2("/cutit/cutit/fxml/fxml2/login2.fxml"), LEFTBAR("/cutit/cutit/fxml/fxml2/leftbar.fxml");

    private String res;

    ViewLayout2(String res){
        this.res = res;
    }

    public static URL getPath(ViewLayout2 layout){
        return ViewLayout2.class.getResource(layout.res);
    }

}