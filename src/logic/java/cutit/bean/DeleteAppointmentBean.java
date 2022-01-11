package cutit.bean;

import java.util.List;

public interface DeleteAppointmentBean {

    String getStartTime();

    void setStartTime(String startTime);

    String getShopName();

    void setShopName(String shopName);

    List<String> getAllAppointments();

    void setAllAppointments(List<String> allAppointments);

}
