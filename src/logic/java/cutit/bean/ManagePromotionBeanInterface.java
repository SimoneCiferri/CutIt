package cutit.bean;

import java.time.LocalDate;
import java.util.List;

public interface ManagePromotionBeanInterface {

    String getPromotionCode();

    void setPromotionCode(String promotionCode);

    Integer getPromOffValue();

    void setPromOffValue(Integer promOffValue);

    String getPromShopName();

    void setPromShopName(String promShopName);

    LocalDate getPromExpireDate();

    void setPromExpireDate(LocalDate promExpireDate);

    String getPromServiceName();

    void setPromServiceName(String promServiceName);

    List<String> getServiceList();

    void setServiceList(List<String> serviceList);

    List<ManagePromotionBeanInterface> getPromotionsBeanList();

    void setPromotionsBeanList(List<ManagePromotionBeanInterface> promotionsBeanList);
}
