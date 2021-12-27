package cutit.cutit.logic.model;

public class Service {

    private String serviceName;
    private Float price;
    private String shopName; //non so se ci va nel model(ok nel db, ma forse non nel model)

    public Service(String serviceName, Float price, String shopName){
         setServiceName(serviceName);
         setPrice(price);
         setShopname(shopName);
    }

    public String getServiceName(){
        return serviceName;
    }

    public void setServiceName(String serviceName){
        this.serviceName = serviceName;
    }

    public Float getPrice(){
        return price;
    }

    public void setPrice(Float price){
        this.price = price;
    }

    public String getShopName(){
        return shopName;
    }

    public void setShopname(String shopName){
        this.shopName = shopName;
    }

}
