package cutit.bean;

import java.time.LocalDateTime;
import java.util.List;

public interface DeleteAppointmentBeanInterface {

    LocalDateTime getStartTime();

    void setStartTime(LocalDateTime startTime);

    String getShopName();

    void setShopName(String shopName);

    List<AppointmentBean> getAllBookedAppointments();

    void setAllBookedAppointments(List<AppointmentBean> allBookedAppointments);

}
