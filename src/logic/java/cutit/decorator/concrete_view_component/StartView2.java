package cutit.decorator.concrete_view_component;

import cutit.decorator.ViewComponent2;
import cutit.decorator.ViewLayout2;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class StartView2 implements ViewComponent2 {

    private Pane prLayout;
    private Map<ViewLayout2, Pane> loadedView = new EnumMap<>(ViewLayout2.class);
    private Map<ViewLayout2, Object> loadedViewContorllerHM = new EnumMap<>(ViewLayout2.class);

    @Override
    public Pane getPrLayout2() {
        return prLayout;
    }

    @Override
    public void setPrLayout2(Pane prLayout) {
        this.prLayout = prLayout;
    }

    @Override
    public void loadXML2(ViewLayout2 layout) throws IOException, NullPointerException {
        if(!loadedView.containsKey(layout)){
            FXMLLoader loader = new FXMLLoader(ViewLayout2.getPath(layout));
            Pane primaryLayout = loader.load();
            Object controller = loader.getController();
            loadedViewContorllerHM.put(layout, controller);

            if(layout == ViewLayout2.START2){
                Image image = new Image(Objects.requireNonNull(ViewLayout2.class.getResource("/cutit/cutit/files/orangeback2.jpg"), "Resource files may be deleted or corrupted. If the problem persist try reinstalling the application.").toString());
                BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                primaryLayout.setBackground(new Background(back));
                setPrLayout2(primaryLayout);
            } else {
                loadedView.put(layout, primaryLayout);
            }
        }
    }

    @Override
    public Pane getLoadedView2(ViewLayout2 layout) {
        if(loadedView.containsKey(layout)){
            return loadedView.get(layout);
        }
        return null;
    }

    public Object getLoadedViewController2(ViewLayout2 layout) {
        return loadedViewContorllerHM.get(layout);
    }

    public Map<ViewLayout2, Pane> getLoaded(){
        return loadedView;
    }

}
