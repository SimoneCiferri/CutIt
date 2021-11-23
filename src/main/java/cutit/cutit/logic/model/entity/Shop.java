package cutit.cutit.logic.model.entity;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.HashMap;
import java.util.Map;

public class Shop {

    private String openTime = "08:00";
    private String closeTime = "20:00";
    private Map<String, Boolean> openDays = new HashMap<String, Boolean>();

    private void setOpenDays(){
        openDays.put("Mon", true);
        openDays.put("Tue", true);
        openDays.put("Wed", true);
        openDays.put("Thu", true);
        openDays.put("Fri", true);
        openDays.put("Sat", true);
        openDays.put("Sun", true);
    }

}
