package cutit.bean;

import java.time.LocalDate;
import java.util.List;

public class ManagePromotionBean {
    private String promotionCode;
    private Integer promOffValue;
    private String promShopName;

    private LocalDate promExpireDate;
    private String promServiceName;

    private List<String> serviceList;
    private List<PromData> promotionsList;

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

    public List<PromData> getPromotionsList() {
        return promotionsList;
    }

    public void setPromotionsList(List<PromData> promotionsList) {
        this.promotionsList = promotionsList;
    }

    public static class PromData{

        String serviceCode;
        Integer offV;
        String serviceName;
        LocalDate expire;

        public String getServiceCode() {
            return serviceCode;
        }

        public void setServiceCode(String serviceCode) {
            this.serviceCode = serviceCode;
        }

        public Integer getOffV() {
            return offV;
        }

        public void setOffV(Integer offV) {
            this.offV = offV;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public LocalDate getExpire() {
            return expire;
        }

        public void setExpire(LocalDate expire) {
            this.expire = expire;
        }
    }
}
