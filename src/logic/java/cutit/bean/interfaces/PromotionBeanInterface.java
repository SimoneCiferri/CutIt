package cutit.bean.interfaces;

import java.time.LocalDate;

public interface PromotionBeanInterface {

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
