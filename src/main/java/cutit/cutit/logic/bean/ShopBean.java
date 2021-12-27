package cutit.cutit.logic.bean;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ShopBean {

    private String shopName;
    private String shopPIVA;
    private String latitude;
    private String longitude;
    private String phoneNumber;
    private String employee;
    private String shopDescription;
    private String openTime;
    private String closeTime;
    private Map<String, Boolean> openDays;
    private List<File> images;
    private List<String> promotions;
    private List<String> services;
    private List<String> allAppointments;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopPIVA() {
        return shopPIVA;
    }

    public void setShopPIVA(String shopPIVA) {
        this.shopPIVA = shopPIVA;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getShopDescription() {
        return shopDescription;
    }

    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public Map<String, Boolean> getOpenDays() {
        return openDays;
    }

    public void setOpenDays(Map<String, Boolean> openDays) {
        this.openDays = openDays;
    }

    public List<File> getImages() {
        return images;
    }

    public void setImages(List<File> images) {
        this.images = images;
    }

    public List<String> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<String> promotions) {
        this.promotions = promotions;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    public List<String> getAllAppointments() {
        return allAppointments;
    }

    public void setAllAppointments(List<String> allAppointments) {
        this.allAppointments = allAppointments;
    }
}
