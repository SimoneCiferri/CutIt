package cutit.cutit.logic.bean;

import cutit.cutit.logic.model.Service;

import java.util.List;

public class ManageServiceBean {

    private String serviceName;
    private Float servicePrice;
    private String serviceShopName;
    private List<Service> servicesList;

    public List<Service> getServicesList() {
        return servicesList;
    }

    public void setServicesList(List<Service> servicesList) {
        this.servicesList = servicesList;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Float getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Float servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServiceShopName() {
        return serviceShopName;
    }

    public void setServiceShopName(String serviceShopName) {
        this.serviceShopName = serviceShopName;
    }

    public String getServiceName(Integer i){
        return servicesList.get(i).getServiceName();
    }

    public Float getServicePrice(Integer i){
        return servicesList.get(i).getPrice();
    }

}
