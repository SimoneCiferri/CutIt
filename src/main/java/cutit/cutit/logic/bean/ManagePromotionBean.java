package cutit.cutit.logic.bean;

import cutit.cutit.logic.model.Promotion;
import cutit.cutit.logic.model.Service;

import java.time.LocalDateTime;
import java.util.List;

public class ManagePromotionBean {

    private int promotionCode;
    private String promShopName;
    private String promotionNAme;
    private Integer promOffValue;
    private LocalDateTime promExpireDate;
    private String promServiceName;

    private List<Service> serviceList;

    private List<Promotion> promotionsList;

    public List<Promotion> getPromotionsList() {
        return promotionsList;
    }

    public void setPromotionsList(List<Promotion> promotionsList) {
        this.promotionsList = promotionsList;
    }

    public String getPromotionName(int i){
        return promotionsList.get(i).getPromName();
    }

    public Integer getPromotionOffValue(int i){
        return promotionsList.get(i).getOffValue();
    }

    public String getPromotionServiceName(int i){
        return promotionsList.get(i).getServiceName();
    }

    public String getExpireData(int i){
        return promotionsList.get(i).getExpireDate().toString();
    }

    public int getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(int promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getPromShopName() {
        return promShopName;
    }

    public void setPromShopName(String promShopName) {
        this.promShopName = promShopName;
    }

    public String getPromotionNAme() {
        return promotionNAme;
    }

    public void setPromotionNAme(String promotionNAme) {
        this.promotionNAme = promotionNAme;
    }

    public Integer getPromOffValue() {
        return promOffValue;
    }

    public void setPromOffValue(Integer promOffValue) {
        this.promOffValue = promOffValue;
    }

    public LocalDateTime getPromExpireDate() {
        return promExpireDate;
    }

    public void setPromExpireDate(LocalDateTime promExpireDate) {
        this.promExpireDate = promExpireDate;
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

}
