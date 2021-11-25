package cutit.cutit.logic.control;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;

public class HairdresserAppointmentInfoViewController {

    public boolean goBackToAppH(){
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERAPPOINTMENTS);
        return true;
    }

}
