package cutit.cutit.logic.decorator;

import javafx.scene.layout.Pane;
import java.io.IOException;

public interface ViewComponent {

    Pane getPrLayout();

    void setPrLayout(Pane prLayout);

    void loadXML(ViewLayout layout) throws IOException;

    Pane getLoadedView(ViewLayout layout);

    Object getLoadedViewController(ViewLayout layout);

}
