package cutit.decorator.decorator1;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Decorator implements ViewComponent1 {

    private ViewComponent1 view;
    private BorderPane prLayout;

    protected Decorator(ViewComponent1 view){
        this.view = view;
    }

    @Override
    public Pane getPrLayout1() {
        return this.view.getPrLayout1();
    }

    @Override
    public void setPrLayout1(Pane prLayout) {
        this.prLayout = (BorderPane) prLayout;
    }

    @Override
    public void loadXML1(ViewLayout1 layout) throws IOException {
        this.view.loadXML1(layout);
        setPrLayout1(getPrLayout1());
        if( layout == ViewLayout1.TOPBAR || layout == ViewLayout1.TOPBARCUSTOMER || layout == ViewLayout1.TOPBARHAIRDRESSER){
            this.prLayout.setTop(getLoadedView1(layout));
        } else {
            this.prLayout.setCenter(getLoadedView1(layout));
        }
    }

    @Override
    public Pane getLoadedView1(ViewLayout1 layout) {
        return this.view.getLoadedView1(layout);
    }

    @Override
    public Object getLoadedViewController1(ViewLayout1 layout) {
        return this.view.getLoadedViewController1(layout);
    }
}
