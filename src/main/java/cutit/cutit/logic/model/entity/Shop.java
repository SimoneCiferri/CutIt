package cutit.cutit.logic.model.entity;

import javax.crypto.spec.OAEPParameterSpec;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Shop {

    private String shopName = "";
    private String address = "";
    private String description = "";
    private LocalTime openTime;
    private LocalTime closeTime;
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
