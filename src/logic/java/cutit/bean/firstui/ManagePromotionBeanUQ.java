package cutit.bean.firstui;

import cutit.bean.ManagePromotionBean;

import java.time.LocalDate;
import java.util.List;

public class ManagePromotionBeanUQ implements ManagePromotionBean{
    private String promotionCode;
    private Integer promOffValue;
    private String promShopName;

    private LocalDate promExpireDate;
    private String promServiceName;

    private List<String> serviceList;
    private List<ManagePromotionBean> promotionsBeanList;

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public Integer getPromOffValue() {
        return promOffValue;
    }

    public void setPromOffValue(Integer promOffValue) {
        this.promOffValue = promOffValue;
    }

    public String getPromShopName() {
        return promShopName;
    }

    public void setPromShopName(String promShopName) {
        this.promShopName = promShopName;
    }

    public LocalDate getPromExpireDate() {
        return promExpireDate;
    }

    public void setPromExpireDate(LocalDate promExpireDate) {
        this.promExpireDate = promExpireDate;
    }

    public String getPromServiceName() {
        return promServiceName;
    }

    public void setPromServiceName(String promServiceName) {
        this.promServiceName = promServiceName;
    }

    public List<String> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<String> serviceList) {
        this.serviceList = serviceList;
    }

    public List<ManagePromotionBean> getPromotionsBeanList() {
        return promotionsBeanList;
    }

    public void setPromotionsBeanList(List<ManagePromotionBean> promotionsBeanList) {
        this.promotionsBeanList = promotionsBeanList;
    }
}
