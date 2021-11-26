package cutit.cutit.logic.control;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;

public class HairdresserAddServiceViewController {

    @FXML
    public boolean goServH(){
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERSERVICES);
        return true;
    }

}
