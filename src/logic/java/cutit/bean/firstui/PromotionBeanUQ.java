package cutit.bean.firstui;

import cutit.bean.PromotionBean;

import java.time.LocalDate;

public class PromotionBeanUQ implements PromotionBean {

    private String promotionCode;
    private Integer offValue;
    private LocalDate expireDate;
    private String serviceName;
    private String shopName;


    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public Integer getOffValue() {
        return offValue;
    }

    public void setOffValue(Integer offValue) {
        this.offValue = offValue;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

}
