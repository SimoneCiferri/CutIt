package cutit.cutit.logic.controller;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;

public class HairdresserPromotionInfoViewController {

    @FXML
    public boolean goBackToPromH(){
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERPROMOTIONS);
        return true;
    }

}
