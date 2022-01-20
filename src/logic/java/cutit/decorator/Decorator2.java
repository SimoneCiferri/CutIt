package cutit.decorator;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Decorator2 implements ViewComponent2{

    private ViewComponent2 view;
    private BorderPane prLayout;

    protected Decorator2(ViewComponent2 view){
        this.view = view;
    }

    @Override
    public Pane getPrLayout2() {
        return this.view.getPrLayout2();
    }

    @Override
    public void setPrLayout2(Pane prLayout) {
        this.prLayout = (BorderPane) prLayout;
    }

    @Override
    public void loadXML2(ViewLayout2 layout) throws IOException {
        this.view.loadXML2(layout);
        setPrLayout2(getPrLayout2());
        if( layout == ViewLayout2.LEFTBAR || layout == ViewLayout2.LEFTBARCUSTOMER || layout == ViewLayout2.LEFTBARHAIRDRESSER){
            this.prLayout.setLeft(getLoadedView2(layout));
        } else {
            this.prLayout.setCenter(getLoadedView2(layout));
        }
    }

    @Override
    public Pane getLoadedView2(ViewLayout2 layout) {
        return this.view.getLoadedView2(layout);
    }

    @Override
    public Object getLoadedViewController2(ViewLayout2 layout) {
        return this.view.getLoadedViewController2(layout);
    }

}
