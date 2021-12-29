package cutit.cutit.logic.bean;

import cutit.cutit.logic.model.Service;

import java.util.List;
import java.util.Map;

public class ManageServiceBean {

    private String serviceName;
    private Float servicePrice;
    private String serviceShopName;

    private List<String> servicesList;
    private Map<String, Float> serviceList;

    public List<String> getServicesList() {
        return servicesList;
    }

    public void setServicesList(List<String> servicesList) {
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

    public Map<String, Float> getServiceList() {
        return serviceList;
    }

    public void setServiceList(Map<String, Float> serviceList) {
        this.serviceList = serviceList;
    }
}
