package cutit.cutit.logic.model;

import javax.crypto.spec.OAEPParameterSpec;
import javax.security.auth.Destroyable;
import java.security.PublicKey;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop {

    private String pIVA;
    private String address;
    private String phoneNumber;
    private String employee;
    private String location;
    private String description;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Map<String, Boolean> openDays = new HashMap<String, Boolean>();
    private String shopName;
    private List<String> images;
    private List<Promotion> promotions;
    private List<Service> services;
    private List<Appointment> allAppointments;

    public Shop(String shopName, String piva){
        setShopName(shopName);
        setPIVA(piva);
    }

    public Shop(String shopName, String address, String phoneNumber, String description, Map<String, Boolean> openDays){
        setShopName(shopName);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setDescription(description);
        setOpenDays(openDays);
    }

    public String getShopName(){
        return shopName;
    }

    public void setShopName(String shopName){
        this.shopName = shopName;
    }

    public String getAddress(){return address;}

    public void setAddress(String address){
        this.address = address;
    }

    public String getPhoneNumber() { return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    //mancano i metodi per l'orario di apertura e l'orario di chiusura

    public Map<String, Boolean> getOpenDays(){
        return openDays;
    }

    public void setOpenDays(Map<String, Boolean> openDays){
        this.openDays = openDays;
    }

    public String getPIVA() {
        return pIVA;
    }

    public void setPIVA(String PIVA) {
        this.pIVA = pIVA;
    }

}
