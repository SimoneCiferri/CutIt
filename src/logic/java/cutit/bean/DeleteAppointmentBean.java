package cutit.bean;

import java.time.LocalDateTime;
import java.util.List;

public interface DeleteAppointmentBean {

    LocalDateTime getStartTime();

    void setStartTime(LocalDateTime startTime);

    String getShopName();

    void setShopName(String shopName);

    List<AppointmentBean> getAllBookedAppointments();

    void setAllBookedAppointments(List<AppointmentBean> allBookedAppointments);

}
