package cutit.bean;

import java.util.List;
import java.util.Map;

public interface ManageServiceBean {

    List<String> getAllServicesList();

    void setAllServicesList(List<String> allServicesList);

    String getServiceName();

    void setServiceName(String serviceName);

    Float getServicePrice();

    void setServicePrice(Float servicePrice);

    String getServiceShopName();

    void setServiceShopName(String serviceShopName);

    Map<String, Float> getServiceList();

    void setServiceList(Map<String, Float> serviceList);

}
