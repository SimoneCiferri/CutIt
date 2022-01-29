package cutit.bean.interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentBeanInterface {

    LocalDateTime getStartTime();

    void setStartTime(LocalDateTime startTime);

    LocalDateTime getEndTime();

    void setEndTime(LocalDateTime endTime);

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
