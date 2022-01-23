package cutit.bean;

import java.io.File;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface ShopBeanInterface {

    String getShopName();

    void setShopName(String shopName);

    String getShopPIVA();

    void setShopPIVA(String shopPIVA);

    String getShopAddress();

    void setShopAddress(String shopAddress);

    String getShopPhoneNumber();

    void setShopPhoneNumber(String shopPhoneNumber);

    String getShopEmployee();

    void setShopEmployee(String shopEmployee);

    String getShopDescription();

    void setShopDescription(String shopDescription);

    LocalTime getShopOpenTime();

    void setShopOpenTime(LocalTime shopOpenTime);

    LocalTime getShopCloseTime();

    void setShopCloseTime(LocalTime shopCloseTime);

    Map<Integer, Boolean> getShopOpenDays();

    void setShopOpenDays(Map<Integer, Boolean> shopOpenDays);

    List<File> getImages();

    void setImages(List<File> images);

    List<String> getPromotions();

    void setPromotions(List<String> promotions);

    List<String> getServices();

    void setServices(List<String> services);

    List<String> getAllAppointments();

    void setAllAppointments(List<String> allAppointments);

}
