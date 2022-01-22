package cutit.bean.firstui;

import cutit.bean.AppointmentBean;
import cutit.bean.DeleteAppointmentBean;

import java.time.LocalDateTime;
import java.util.List;

public class DeleteAppointmentBeanFirstUI implements DeleteAppointmentBean {

    private LocalDateTime startTime;
    private String shopName;
    private List<AppointmentBean> allBookedAppointments;

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

    public List<AppointmentBean> getAllBookedAppointments() {
        return allBookedAppointments;
    }

    public void setAllBookedAppointments(List<AppointmentBean> allBookedAppointments) {
        this.allBookedAppointments = allBookedAppointments;
    }
}
