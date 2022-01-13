package cutit.bean.firstui;

import cutit.bean.ShopBean;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopBeanUQ implements ShopBean {

    private String shopName;
    private String shopPIVA;
    private String address;
    private String phoneNumber;
    private String employee;
    private String shopDescription;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Map<Integer, Boolean> openDays;
    private List<File> images = new ArrayList<>();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    public Map<Integer, Boolean> getOpenDays() {
        return openDays;
    }

    public void setOpenDays(Map<Integer, Boolean> openDays) {
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
