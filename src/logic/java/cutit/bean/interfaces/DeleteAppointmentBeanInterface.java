package cutit.bean.interfaces;

import cutit.bean.interfaces.AppointmentBeanInterface;

import java.time.LocalDateTime;
import java.util.List;

public interface DeleteAppointmentBeanInterface {

    LocalDateTime getStartTime();

    void setStartTime(LocalDateTime startTime);

    String getShopName();

    void setShopName(String shopName);

    List<AppointmentBeanInterface> getAllBookedAppointments();

    void setAllBookedAppointments(List<AppointmentBeanInterface> allBookedAppointments);

}
