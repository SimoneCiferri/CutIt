package cutit.bean;

import cutit.bean.interfaces.AppointmentBeanInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class AppointmentBean implements AppointmentBeanInterface {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String customer;
    private String promotionCode;
    private String serviceName;
    private String shopName;
    private LocalDate selectedDay;
    private List<LocalTime> availableSlots;
    private List<String> availableServices;


    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Override
    public List<LocalTime> getAvailableSlots() {
        return availableSlots;
    }

    @Override
    public void setAvailableSlots(List<LocalTime> availableSlots) {
        this.availableSlots = availableSlots;
    }

    @Override
    public LocalDate getSelectedDay() {
        return selectedDay;
    }

    @Override
    public void setSelectedDay(LocalDate selectedDay) {
        this.selectedDay = selectedDay;
    }

    @Override
    public List<String> getAvailableServices() {
        return availableServices;
    }

    @Override
    public void setAvailableServices(List<String> servicesList) {
        this.availableServices = servicesList;
    }

}
