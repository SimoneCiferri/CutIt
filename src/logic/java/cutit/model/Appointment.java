package cutit.model;

import java.time.LocalDateTime;

public class Appointment {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Customer customer;
    private Promotion promotion;
    private Service service;
    private Shop shop;

    public Appointment(LocalDateTime startTime, LocalDateTime endTime, Customer customer, Promotion promotion, Service service, Shop shop){
        setStartTime(startTime);
        setEndTime(endTime);
        setCustomer(customer);
        setPromotion(promotion);
        setService(service);
        setShop(shop);
    }

    public Appointment(LocalDateTime startTime, LocalDateTime endTime, Customer customer, Service service, Shop shop){
        setStartTime(startTime);
        setEndTime(endTime);
        setCustomer(customer);
        setService(service);
        setShop(shop);
    }


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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

}
