package cutit.decorator;

import javafx.scene.layout.Pane;
import java.io.IOException;

public interface ViewComponent2 {

    Pane getPrLayout2();

    void setPrLayout2(Pane prLayout);

    void loadXML2(ViewLayout2 layout) throws IOException;

    Pane getLoadedView2(ViewLayout2 layout);

    Object getLoadedViewController2(ViewLayout2 layout);

}
