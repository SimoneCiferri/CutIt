package cutit.decorator.concrete_view_component;

import cutit.decorator.ViewComponent;
import cutit.decorator.ViewLayout;
import cutit.facade.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class StartView implements ViewComponent {

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
    public void loadXML(ViewLayout layout) throws IOException, NullPointerException {
        if(!loadedView.containsKey(layout)){
            FXMLLoader loader = new FXMLLoader(ViewLayout.getPath(layout));
            Pane primaryLayout = loader.load();
            Object controller = loader.getController();
            loadedViewContorllerHM.put(layout, controller);

            if(layout == ViewLayout.START){
                Image image = new Image(Objects.requireNonNull(Client.class.getResource("/cutit/cutit/files/backgr.jpg"), "Resource files may be deleted or corrupted. If the problem persist try reinstalling the application.").toString());
                BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                primaryLayout.setBackground(new Background(back));
                setPrLayout(primaryLayout);
            } else {
                loadedView.put(layout, primaryLayout);
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

    public Map<ViewLayout, Pane> getLoaded(){
        return loadedView;
    }
}
