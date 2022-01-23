package cutit.bean;

import cutit.bean.ManageServiceBeanInterface;

import java.util.List;
import java.util.Map;

public class ManageServiceBean implements ManageServiceBeanInterface {

    private String serviceName;
    private Float servicePrice;
    private String serviceShopName;

    private Map<String, Float> serviceList;
    private List<String> allServicesList;

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

    public List<String> getAllServicesList() {
        return allServicesList;
    }

    public void setAllServicesList(List<String> allServicesList) {
        this.allServicesList = allServicesList;
    }
}
