package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class StartClient implements ViewComponent {
    @Override
    public Pane getPrLayout() {
        return null;
    }

    @Override
    public void setPrLayout(Pane prLayout) {

    }

    @Override
    public void loadXML(ViewLayout layout) throws IOException {

    }

    @Override
    public Pane getLoadedView(ViewLayout layout) {
        return null;
    }

    @Override
    public Object getLoadedViewController(ViewLayout layout) {
        return null;
    }
}
