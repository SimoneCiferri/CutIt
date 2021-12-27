package cutit.cutit.logic.bean;

import cutit.cutit.logic.model.Promotion;
import cutit.cutit.logic.model.Service;

import java.time.LocalDateTime;
import java.util.List;

public class ManagePromotionBean {
    private String promotionCode;
    private Integer promOffValue;
    private String promShopName;

    private String promExpireDate;
    private String promServiceName;

    private List<Service> serviceList;
    private List<Promotion> promotionsList;

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getPromotionCodeI(int i){
        return promotionsList.get(i).getCode();
    }

    public Integer getPromOffValue() {
        return promOffValue;
    }

    public void setPromOffValue(Integer promOffValue) {
        this.promOffValue = promOffValue;
    }

    public Integer getPromotionOffValue(int i){
        return promotionsList.get(i).getOffValue();
    }

    public String getPromShopName() {
        return promShopName;
    }

    public void setPromShopName(String promShopName) {
        this.promShopName = promShopName;
    }

    public String getPromExpireDate() {
        return promExpireDate;
    }

    public void setPromExpireDate(String promExpireDate) {
        this.promExpireDate = promExpireDate;
    }

    public String getPromExpireDataI(int i){
        return promotionsList.get(i).getExpireDate().toString();
    }

    public String getPromServiceName() {
        return promServiceName;
    }

    public void setPromServiceName(String promServiceName) {
        this.promServiceName = promServiceName;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public String getServiceName(int i) {
        return serviceList.get(i).getServiceName();
    }

    public List<Promotion> getPromotionsList() {
        return promotionsList;
    }

    public void setPromotionsList(List<Promotion> promotionsList) {
        this.promotionsList = promotionsList;
    }

    public String getPromotionServiceNameI(int i){
        return promotionsList.get(i).getService().getServiceName();
    }

}
