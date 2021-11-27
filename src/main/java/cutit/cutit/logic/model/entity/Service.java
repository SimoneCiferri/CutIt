package cutit.cutit.logic.model.entity;

public class Service {

    private String serviceName;
    private Float price;
    private String description;

    public Service(String serviceName, Float price, String description){
         setServiceName(serviceName);
         setPrice(price);
         setDescription(description);
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

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

}
