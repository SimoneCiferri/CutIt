package cutit.cutit.logic.model;

import java.io.File;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop {

    private String shopName;
    private String pIVA;
    private String latitude;
    private String longitude;
    private String phoneNumber;
    private String employee;
    private String description;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Map<String, Boolean> openDays = new HashMap<String, Boolean>();
    private List<File> images;
    private List<Promotion> promotions;
    private List<Service> services;
    private List<Appointment> allAppointments;

    public Shop(String shopName, String piva){
        setShopName(shopName);
        setpIVA(piva);
    }

    public Shop(String shopName, String piva, String latitude, String longitude, String phoneNumber, String employee, String description, LocalTime openTime, LocalTime closeTime){
        setShopName(shopName);
        setpIVA(piva);
        setLatitude(latitude);
        setLongitude(longitude);
        setPhoneNumber(phoneNumber);
        setEmployee(employee);
        setDescription(description);
        setOpenTime(openTime);
        setCloseTime(closeTime);
    }

    public Shop(String shopName, String piva, String latitude, String longitude, String phoneNumber, String employee, String description, LocalTime openTime, LocalTime closeTime, Map<String, Boolean> openDays, List<File> images){
        setShopName(shopName);
        setpIVA(piva);
        setLatitude(latitude);
        setLongitude(longitude);
        setPhoneNumber(phoneNumber);
        setEmployee(employee);
        setDescription(description);
        setOpenDays(openDays);
        setOpenTime(openTime);
        setCloseTime(closeTime);
        setImages(images);
    }

    public Shop(String shopName, String piva, String latitude, String longitude, String phoneNumber, String employee, String description, LocalTime openTime, LocalTime closeTime, Map<String, Boolean> openDays, List<File> images, List<Promotion> promotions, List<Service> services, List<Appointment> allAppointments){
        setShopName(shopName);
        setpIVA(piva);
        setLatitude(latitude);
        setLongitude(longitude);
        setPhoneNumber(phoneNumber);
        setEmployee(employee);
        setDescription(description);
        setOpenDays(openDays);
        setOpenTime(openTime);
        setCloseTime(closeTime);
        setImages(images);
        setPromotions(promotions);
        setServices(services);
        setAllAppointments(allAppointments);
    }

    public String getpIVA() {
        return pIVA;
    }

    public void setpIVA(String pIVA) {
        this.pIVA = pIVA;
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

    public Map<String, Boolean> getOpenDays() {
        return openDays;
    }

    public void setOpenDays(Map<String, Boolean> openDays) {
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
