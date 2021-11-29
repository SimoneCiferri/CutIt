package cutit.cutit.logic.controller;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;

public class HairdresserServiceInfoViewController {

    @FXML
    public boolean goBackToServices(){
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERSERVICES);
        return true;
    }

}
