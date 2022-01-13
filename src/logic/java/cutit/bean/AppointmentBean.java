package cutit.bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentBean {

    String getStartTime();

    void setStartTime(String startTime);

    String getEndTime();

    void setEndTime(String endTime);

    String getCustomer();

    void setCustomer(String customer);

    String getPromotionCode();

    void setPromotionCode(String promotionCode);

    String getServiceName();

    void setServiceName(String serviceName);

    String getShopName();

    void setShopName(String shopName);

    List<LocalTime> getAvailableSlots();

    void setAvailableSlots(List<LocalTime> availableSlots);

    LocalDate getSelectedDay();

    void setSelectedDay(LocalDate selectedDay);

    List<String> getAvailableServices();

    void setAvailableServices(List<String> servicesList);

}
