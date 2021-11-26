package cutit.cutit.logic.decorator;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Decorator implements ViewComponent{

    private ViewComponent view;
    private BorderPane prLayout;

    protected Decorator(ViewComponent view){
        this.view = view;
    }

    @Override
    public Pane getPrLayout() {
        return this.view.getPrLayout();
    }

    @Override
    public void setPrLayout(Pane prLayout) {
        this.prLayout = (BorderPane) prLayout;
    }

    @Override
    public void loadXML(ViewLayout layout) throws IOException {
        this.view.loadXML(layout);
        setPrLayout(getPrLayout());
        if(layout == ViewLayout.TOPBAR || layout == ViewLayout.TOPBARCLIENT || layout == ViewLayout.TOPBARHAIRDRESSER){
            this.prLayout.setTop(getLoadedView(layout));
        } else {
            this.prLayout.setCenter(getLoadedView(layout));
        }
    }

    @Override
    public Pane getLoadedView(ViewLayout layout) {
        return this.view.getLoadedView(layout);
    }

    @Override
    public Object getLoadedViewController(ViewLayout layout) {
        return this.view.getLoadedViewController(layout);
    }
}
