package cutit.bean;

import cutit.bean.interfaces.AppointmentBeanInterface;
import cutit.bean.interfaces.DeleteAppointmentBeanInterface;

import java.time.LocalDateTime;
import java.util.List;

public class DeleteAppointmentBean implements DeleteAppointmentBeanInterface {

    private LocalDateTime startTime;
    private String shopName;
    private List<AppointmentBeanInterface> allBookedAppointments;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<AppointmentBeanInterface> getAllBookedAppointments() {
        return allBookedAppointments;
    }

    public void setAllBookedAppointments(List<AppointmentBeanInterface> allBookedAppointments) {
        this.allBookedAppointments = allBookedAppointments;
    }
}
