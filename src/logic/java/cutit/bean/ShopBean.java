package cutit.bean;

import cutit.bean.interfaces.ShopBeanInterface;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopBean implements ShopBeanInterface {

    private String shopName;
    private String shopPIVA;
    private String shopAddress;
    private String shopPhoneNumber;
    private String shopEmployee;
    private String shopDescription;
    private LocalTime shopOpenTime;
    private LocalTime shopCloseTime;
    private Map<Integer, Boolean> shopOpenDays;
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

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopPhoneNumber() {
        return shopPhoneNumber;
    }

    public void setShopPhoneNumber(String shopPhoneNumber) {
        this.shopPhoneNumber = shopPhoneNumber;
    }

    public String getShopEmployee() {
        return shopEmployee;
    }

    public void setShopEmployee(String shopEmployee) {
        this.shopEmployee = shopEmployee;
    }

    public String getShopDescription() {
        return shopDescription;
    }

    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription;
    }

    public LocalTime getShopOpenTime() {
        return shopOpenTime;
    }

    public void setShopOpenTime(LocalTime shopOpenTime) {
        this.shopOpenTime = shopOpenTime;
    }

    public LocalTime getShopCloseTime() {
        return shopCloseTime;
    }

    public void setShopCloseTime(LocalTime shopCloseTime) {
        this.shopCloseTime = shopCloseTime;
    }

    public Map<Integer, Boolean> getShopOpenDays() {
        return shopOpenDays;
    }

    public void setShopOpenDays(Map<Integer, Boolean> shopOpenDays) {
        this.shopOpenDays = shopOpenDays;
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
