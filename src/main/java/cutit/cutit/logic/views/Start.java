package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class Start implements ViewComponent {

    private Pane prLayout;
    private Map<ViewLayout, Pane> loadedView = new EnumMap<>(ViewLayout.class);
    private Map<ViewLayout, Object> loadedViewContorllerHM = new EnumMap<>(ViewLayout.class);

    @Override
    public Pane getPrLayout() {
        return prLayout;
    }

    @Override
    public void setPrLayout(Pane prLayout) {
        this.prLayout = prLayout;
    }

    @Override
    public void loadXML(ViewLayout layout) throws IOException {
        if(!loadedView.containsKey(layout)){
            FXMLLoader loader = new FXMLLoader(ViewLayout.getPath(layout));
            Pane prLayout = loader.load();
            Object controller = loader.getController();
            loadedViewContorllerHM.put(layout, controller);

            if(layout == ViewLayout.START || layout == ViewLayout.SARTCLIENT || layout == ViewLayout.STARTHAIRDRESSER){
                Image image = new Image(Client.class.getResource("/cutit/cutit/files/backgr.jpg").toString());
                BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                prLayout.setBackground(new Background(back));
                setPrLayout(prLayout);
            } else {
                loadedView.put(layout, prLayout);
            }
        }
    }

    @Override
    public Pane getLoadedView(ViewLayout layout) {
        if(loadedView.containsKey(layout)){
            return loadedView.get(layout);
        }
        return null;
    }

    @Override
    public Object getLoadedViewController(ViewLayout layout) {
        return loadedViewContorllerHM.get(layout);
    }
}
