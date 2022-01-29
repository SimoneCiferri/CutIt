package cutit.decorator.decorator.concrete_view_component;

import cutit.decorator.decorator.ViewComponent1;
import cutit.decorator.decorator.ViewLayout1;
import cutit.facade.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class StartView1 implements ViewComponent1 {

    private Pane prLayout;
    private Map<ViewLayout1, Pane> loadedView = new EnumMap<>(ViewLayout1.class);
    private Map<ViewLayout1, Object> loadedViewControllerHM = new EnumMap<>(ViewLayout1.class);

    @Override
    public Pane getPrLayout1() {
        return prLayout;
    }

    @Override
    public void setPrLayout1(Pane prLayout) {
        this.prLayout = prLayout;
    }

    @Override
    public void loadXML1(ViewLayout1 layout) throws IOException, NullPointerException {
        if(!loadedView.containsKey(layout) || layout == ViewLayout1.CUSTOMERBOOKAPPOINTMENT || layout == ViewLayout1.SHOPINFO || layout == ViewLayout1.GMAPS){
            FXMLLoader loader = new FXMLLoader(ViewLayout1.getPath(layout));
            Pane primaryLayout = loader.load();
            Object controller = loader.getController();
            loadedViewControllerHM.put(layout, controller);

            if(layout == ViewLayout1.START){
                Image image = new Image(Objects.requireNonNull(Client.class.getResource("/cutit/cutit/files/backgr.jpg"), "Resource files may be deleted or corrupted. If the problem persist try reinstalling the application.").toString());
                BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                primaryLayout.setBackground(new Background(back));
                setPrLayout1(primaryLayout);
            } else {
                loadedView.put(layout, primaryLayout);
            }
        }
    }

    @Override
    public Pane getLoadedView1(ViewLayout1 layout) {
        if(loadedView.containsKey(layout)){
            return loadedView.get(layout);
        }
        return null;
    }

    @Override
    public Object getLoadedViewController1(ViewLayout1 layout) {
        return loadedViewControllerHM.get(layout);
    }

    public Map<ViewLayout1, Pane> getLoaded(){
        return loadedView;
    }
}
