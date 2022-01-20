package cutit.model;

import java.io.File;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Shop {

    private String shopName;
    private String pIVA;
    private String address;
    private String phoneNumber;
    private String employee;
    private String description;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Map<Integer, Boolean> openDays;
    private List<File> images;
    private List<Promotion> promotions;
    private List<Service> services;
    private List<Appointment> allAppointments;

    public Shop(String shopName, String piva){
        setShopName(shopName);
        setpIVA(piva);
    }

    public String getpIVA() {
        return pIVA;
    }

    public void setpIVA(String pIVA) {
        this.pIVA = pIVA;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<File> getImages() {
        return images;
    }

    public void setImages(List<File> images) {
        this.images = images;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Appointment> getAllAppointments() {
        return allAppointments;
    }

    public void setAllAppointments(List<Appointment> allAppointments) {
        this.allAppointments = allAppointments;
    }

}
