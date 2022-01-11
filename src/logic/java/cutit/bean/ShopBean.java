package cutit.bean;

import java.io.File;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface ShopBean {

    String getShopName();

    void setShopName(String shopName);

    String getShopPIVA();

    void setShopPIVA(String shopPIVA);

    String getAddress();

    void setAddress(String address);

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);

    String getEmployee();

    void setEmployee(String employee);

    String getShopDescription();

    void setShopDescription(String shopDescription);

    LocalTime getOpenTime();

    void setOpenTime(LocalTime openTime);

    LocalTime getCloseTime();

    void setCloseTime(LocalTime closeTime);

    Map<Integer, Boolean> getOpenDays();

    void setOpenDays(Map<Integer, Boolean> openDays);

    List<File> getImages();

    void setImages(List<File> images);

    List<String> getPromotions();

    void setPromotions(List<String> promotions);

    List<String> getServices();

    void setServices(List<String> services);

    List<String> getAllAppointments();

    void setAllAppointments(List<String> allAppointments);

}
