package cutit.cutit.logic.decorator;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public interface ViewComponent {

    public Pane getPrLayout();

    public void setPrLayout(Pane prLayout);

    public void loadXML(ViewLayout layout) throws IOException;

    public Pane getLoadedView(ViewLayout layout);

    public Object getLoadedViewController(ViewLayout layout);

}
