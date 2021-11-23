package cutit.cutit.logic.decorator;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public interface ViewComponent {

    public BorderPane getPrLayout();

    public void setPrLayout(BorderPane prLayout);

    public void loadXML() throws IOException;

    public Pane getLoadedView();

    public Object getLoadedViewController();

}
