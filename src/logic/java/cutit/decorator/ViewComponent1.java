package cutit.decorator;

import javafx.scene.layout.Pane;
import java.io.IOException;

public interface ViewComponent1 {

    Pane getPrLayout1();

    void setPrLayout1(Pane prLayout);

    void loadXML1(ViewLayout1 layout) throws IOException;

    Pane getLoadedView1(ViewLayout1 layout);

    Object getLoadedViewController1(ViewLayout1 layout);

}
