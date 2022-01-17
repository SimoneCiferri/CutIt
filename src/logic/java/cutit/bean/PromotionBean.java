package cutit.bean;

import java.time.LocalDate;

public interface PromotionBean {

    String getPromotionCode();

    void setPromotionCode(String promotionCode);

    Integer getOffValue();

    void setOffValue(Integer offValue);

    LocalDate getExpireDate();

    void setExpireDate(LocalDate expireDate);

    String getServiceName();

    void setServiceName(String serviceName);

    String getShopName();

    void setShopName(String shopName);

}
