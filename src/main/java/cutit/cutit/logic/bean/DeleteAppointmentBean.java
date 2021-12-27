package cutit.cutit.logic.bean;

import java.time.LocalDateTime;
import java.util.List;

public class DeleteAppointmentBean {

    private String startTime;
    private String shopName;
    private List<String> allAppointments;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<String> getAllAppointments() {
        return allAppointments;
    }

    public void setAllAppointments(List<String> allAppointments) {
        this.allAppointments = allAppointments;
    }
}
