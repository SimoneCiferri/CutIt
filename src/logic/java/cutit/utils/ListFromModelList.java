package cutit.utils;

import cutit.model.Promotion;
import cutit.model.Service;

import java.util.ArrayList;
import java.util.List;

public class ListFromModelList {

    private ListFromModelList(){}

    public static List<String> getStringListFromServices(List<Service> services) {
        List<String> servList = new ArrayList<>();
        if(!services.isEmpty()){
            for(int i = 0; i<services.size(); i++){
                String p = services.get(i).getServiceName();
                servList.add(p);
            }
        }
        return servList;
    }

    public static List<String> getStringListFromPromotions(List<Promotion> promotions) {
        List<String> promList = new ArrayList<>();
        if(!promotions.isEmpty()){
            for (Promotion promotion : promotions) {
                String p = promotion.getCode();
                promList.add(p);
            }
        }
        return promList;
    }

}
