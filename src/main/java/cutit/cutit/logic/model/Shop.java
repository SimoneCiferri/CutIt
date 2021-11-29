package cutit.cutit.logic.model;

import javax.crypto.spec.OAEPParameterSpec;
import javax.security.auth.Destroyable;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Shop {

    private String shopName;
    private String address;
    private String description;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Map<String, Boolean> openDays = new HashMap<String, Boolean>();

    public Shop(String shopName, String address, String description, Map<String, Boolean> openDays){
        setShopName(shopName);
        setAddress(address);
        setDescription(description);
        setOpenDays(openDays);
    }

    public String getShopName(){
        return shopName;
    }

    public void setShopName(String shopName){
        this.shopName = shopName;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

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

}
